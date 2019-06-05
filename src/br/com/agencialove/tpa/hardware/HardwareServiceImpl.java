package br.com.agencialove.tpa.hardware;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.agencialove.tpa.hardware.arduino.ArduinoCommands;
import br.com.agencialove.tpa.hardware.arduino.ArduinoMessage;
import br.com.agencialove.tpa.hardware.arduino.ArduinoProtocol;
import br.com.agencialove.tpa.hardware.arduino.ArduinoProtocol.State;
import br.com.agencialove.tpa.hardware.arduino.ArduinoProtocol.Type;
import br.com.agencialove.tpa.hardware.arduino.ArduinoServiceImpl;
import br.com.agencialove.tpa.hardware.arduino.ArduinoServiceImpl.ArduinoMonitoringThread;
import br.com.agencialove.tpa.hardware.arduino.CollectBoxState;
import br.com.agencialove.tpa.hardware.arduino.Dispenser;
import br.com.agencialove.tpa.hardware.arduino.DispenserState;
import br.com.agencialove.tpa.hardware.arduino.Error;
import br.com.agencialove.tpa.hardware.arduino.IArduinoService;
import br.com.agencialove.tpa.hardware.arduino.Warning;
import br.com.agencialove.tpa.hardware.arduino.Working;
import br.com.agencialove.tpa.hardware.scale.IScalePort;
import br.com.agencialove.tpa.hardware.scale.ScaleServiceImpl;
import br.com.agencialove.tpa.hardware.scale.ScaleServiceImpl.ScaleMonitoringThread;
import br.com.agencialove.tpa.model.PackageMeasures;


public class HardwareServiceImpl implements IHardwareService {
	private static final IArduinoService arduinoService = new ArduinoServiceImpl();
	private static final IScalePort scaleService = new ScaleServiceImpl();
	private static final long TIMEOUT = 10000;
	private IHardwareCallback hardwareCallback;

	private static String lastVolumeMeasured;

	public HardwareServiceImpl(final IHardwareCallback pCallback) {

		this.hardwareCallback = pCallback;

		/*
		 * starts scale monitor thread
		 */

		final ScaleMonitoringThread scaleMonitor = ((ScaleServiceImpl)HardwareServiceImpl.scaleService).new ScaleMonitoringThread();
		scaleMonitor.setHardwareCallback(this.hardwareCallback);
		new Thread(scaleMonitor).start();

		/*
		 * starts arduino monitor thread
		 */

		final ArduinoMonitoringThread arduinoMonitor = ((ArduinoServiceImpl)HardwareServiceImpl.arduinoService).new ArduinoMonitoringThread();
		arduinoMonitor.setHardwareCallback(this.hardwareCallback);
		new Thread(arduinoMonitor).start();
	}

	@Override
	public String getVolumeMeasured() {
		return HardwareServiceImpl.lastVolumeMeasured;
	}

	@Override
	public void setVolumeMeasured(final String volumeMeasured) {
		HardwareServiceImpl.lastVolumeMeasured = volumeMeasured;
	}

	@Override
	public DispenserState getDispenserState(final Dispenser d) throws IOException {

		ArduinoMessage m = null;
		switch (d) {
		case A:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.STATE_D1); break;
		case B:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.STATE_D2); break;
		case C:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.STATE_D3); break;
		case D:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.STATE_D4); break;
		default:
			throw new IllegalStateException();
		}

		if(m.getProtocol().getState() == State.WARNING)
			this.hardwareCallback.callbackWarning(new Warning(m));
		else if(m.getProtocol().getState() == State.ERROR)
			this.hardwareCallback.callbackErro(new Error(m));

		return m.getProtocol().getDispenserState();
	}

	@Override
	public DispenserState recharge(final Dispenser d, final int quantity) throws IOException {
		ArduinoMessage m = null;
		switch (d) {
		case A:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.RECHARGE_D1,quantity); break;
		case B:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.RECHARGE_D2,quantity); break;
		case C:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.RECHARGE_D3,quantity); break;
		case D:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.RECHARGE_D4,quantity); break;
		default:
			throw new IllegalStateException();
		}

		if(m != null)
			if(m.getProtocol() == ArduinoProtocol.RESP_SUCCESS) {
				switch (d) {
				case A:
					m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.STATE_D1); break;
				case B:
					m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.STATE_D2); break;
				case C:
					m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.STATE_D3); break;
				case D:
					m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.STATE_D4); break;
				default:
					throw new IllegalStateException();
				}
			}else {
				this.hardwareCallback.callbackException(new RuntimeException(m.getProtocol().getMessage()));
			}
		else {
			this.hardwareCallback.callbackException(new RuntimeException("Resposta para comando RECHARGE_D" + d.getId() + " retornou null!"));
		}

		return m.getProtocol().getDispenserState();
	}


	@Override
	public int getInventory(final Dispenser d) throws IOException {
		ArduinoMessage m = null;
		switch (d) {
		case A:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.INVENTORY_D1); break;
		case B:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.INVENTORY_D2); break;
		case C:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.INVENTORY_D3); break;
		case D:
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.INVENTORY_D4); break;
		default:
			throw new IllegalStateException();
		}


		return Integer.parseInt(m.getValue());
	}

	@Override
	public DispenserState dispensePack(final Dispenser d) throws IOException {
		ArduinoMessage m = null;

		final DispenserState ds = this.getDispenserState(d);

		if(ds == DispenserState.IDLE) {
			switch (d) {
			case A:
				m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.DISPENSE_D1); break;
			case B:
				m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.DISPENSE_D2); break;
			case C:
				m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.DISPENSE_D3); break;
			case D:
				m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.DISPENSE_D4); break;
			default:
				throw new IllegalStateException();
			}

			if(m == null)
				throw new IllegalStateException();
			else {
				if(m.getProtocol().getState() == ArduinoProtocol.State.WORKING)
					try {
						Thread.sleep(3000);
					} catch (final InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if(m.getProtocol().getState() == ArduinoProtocol.State.ERROR_BLOCKED)
					return DispenserState.ERROR_BLOCKED;

				return this.getDispenserState(d);
			}
		}else
			return ds;
	}

	@Override
	public CollectBoxState getBoxState() throws IOException {
		final ArduinoMessage m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.STATE_BOX);
		if(m == null) {
			throw new IOException("Não foi possível comunicar com o Arduino para recuperar o estado da gaveta coletora!");

		}
		final CollectBoxState ret = this.messageToBoxState(m);
		if(ret == null)
			throw new IOException("A abertura da gaveta retornou uma resposta incosistente: " +  m.getProtocol().getMessage());
		return ret;
	}

	@Override
	public CollectBoxState openBox() throws IOException {
		ArduinoMessage m = null;

		final long timeout = System.currentTimeMillis() + HardwareServiceImpl.TIMEOUT;
		while(timeout > System.currentTimeMillis()) {
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.OPEN_BOX);

			if(m == null) {
				Thread.yield();
				continue;
			}else
				if(m.getProtocol().getType() == Type.CALLBACK)
					Logger.getAnonymousLogger().log(Level.INFO, "Callback recebido: ", m); //$NON-NLS-1$
			if(m.getProtocol().getType() == Type.RESP)
				break;
		}

		if(m == null)
			throw new IOException("Não foi possível comunicar com o Arduino para abrir a gaveta coletora");

		CollectBoxState ret = this.messageToBoxState(m);
		if(ret == null)
			throw new IOException("A abertura da gaveta retornou uma resposta incosistente: " +  m.getProtocol().getMessage());

		while(ret == CollectBoxState.WORKING_OPENING) {
			Thread.yield();
			m = HardwareServiceImpl.arduinoService.getResponse();
			if(m != null) {
				ret = this.messageToBoxState(m);
				this.hardwareCallback.callbackWorking(new Working(m));
			}
		}

		return ret;
	}

	@Override
	public CollectBoxState closeBox() throws IOException {
		ArduinoMessage m = null;

		final long timeout = System.currentTimeMillis() + HardwareServiceImpl.TIMEOUT;
		while(timeout > System.currentTimeMillis()) {
			m = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.OPEN_BOX);

			if(m == null) {
				Thread.yield();
				continue;
			}

		}

		if(m == null)
			throw new IOException("Não foi possível comunicar com o Arduino para abrir a gaveta coletora");

		CollectBoxState ret = this.messageToBoxState(m);
		if(ret == null)
			throw new IOException("A abertura da gaveta retornou uma resposta incosistente: " +  m.getProtocol().getMessage());

		while(ret == CollectBoxState.WORKING_OPENING) {
			Thread.yield();
			m = HardwareServiceImpl.arduinoService.getResponse();
			if(m != null) {
				ret = this.messageToBoxState(m);
				this.hardwareCallback.callbackWorking(new Working(m));
			}
		}

		return ret;
	}

	@Override
	public void turnOnScale() throws IOException {
		HardwareServiceImpl.scaleService.turnOn();
	}


	@Override
	public PackageMeasures getMeasures() throws IOException {
		final PackageMeasures ret = new PackageMeasures();
		ret.setWeight(this.getWeight());

		final String[] measures = HardwareServiceImpl.lastVolumeMeasured.split("x");

		ret.setWidth(measures[0]);
		ret.setHeight(measures[1]);
		ret.setDepth(measures[2]);
		ret.setDiameter(measures[3]);

		return ret;
	}

	private String getWeight() throws IOException {
		final String ret = HardwareServiceImpl.scaleService.getWeight();

		return ret;
	}

	@Override
	public void alarmWarning() throws IOException{
		final ArduinoMessage message = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.ALARM_YELLOW);
		if(ArduinoProtocol.RESP_SUCCESS != message.getProtocol())
			throw new IOException("Erro ao ligar a lâmpada de alarme: " + message.getProtocol().getMessage());
	}

	@Override
	public void alarmError() throws IOException {
		final ArduinoMessage message = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.ALARM_RED);
		if(ArduinoProtocol.RESP_SUCCESS != message.getProtocol())
			throw new IOException("Erro ao ligar a lâmpada de falha: " + message.getProtocol().getMessage());
	}

	@Override
	public void alarmOff() throws IOException {
		final ArduinoMessage message = HardwareServiceImpl.arduinoService.sendCommand(ArduinoCommands.ALARM_OFF);
		if(ArduinoProtocol.RESP_SUCCESS != message.getProtocol())
			throw new IOException("Erro ao ligar a lâmpada de falha: " + message.getProtocol().getMessage());
	}

	@Override
	public void setHardwareCallback(final IHardwareCallback callback) {
		this.hardwareCallback = callback;
	}

	private CollectBoxState messageToBoxState(final ArduinoMessage m) {
		switch (m.getProtocol()) {
		case RESP_BOX_OPEN_FULL: return CollectBoxState.OPEN_FULL;
		case RESP_BOX_OPEN_HALF: return CollectBoxState.OPEN_HALF;
		case RESP_BOX_CLOSED: return CollectBoxState.CLOSED;
		case RESP_BOX_WORKING_OPENING: return CollectBoxState.WORKING_OPENING;
		case RESP_BOX_WORKING_CLOSING: return CollectBoxState.WORKING_CLOSING;
		case RESP_BOX_BLOCKED: return CollectBoxState.BLOCKED;
		default: return null;
		}
	}
}

