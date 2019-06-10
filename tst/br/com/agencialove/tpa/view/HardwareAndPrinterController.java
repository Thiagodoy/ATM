package br.com.agencialove.tpa.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.hardware.IHardwareService;
import br.com.agencialove.tpa.hardware.IPrinterService;
import br.com.agencialove.tpa.hardware.arduino.CollectBoxState;
import br.com.agencialove.tpa.hardware.arduino.Debug;
import br.com.agencialove.tpa.hardware.arduino.Dispenser;
import br.com.agencialove.tpa.hardware.arduino.DispenserState;
import br.com.agencialove.tpa.hardware.arduino.Error;
import br.com.agencialove.tpa.hardware.arduino.Warning;
import br.com.agencialove.tpa.hardware.arduino.Working;
import br.com.agencialove.tpa.model.PackageMeasures;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HardwareAndPrinterController implements IController {


	@FXML private Button btnDispenseD1;
	@FXML private TextField txtRecharge;
	@FXML private Button btnRechargeD1;
	@FXML private Button btnInventoryD1;
	@FXML private Button btnStateD1;
	@FXML private Button btnStateBox;
	@FXML private Button btnOpenBox;
	@FXML private Button btnCloseBox;
	@FXML private Button btnAlarmRed;
	@FXML private Button btnAlarmYellow;
	@FXML private Button btnAlarmOff;
	@FXML private Button btnFullstate;
	@FXML private Button btnKeepAlive;
	@FXML private Button btnGetWeight;
	@FXML private Button btnPrintTicketText;
	@FXML private Button btnPrintTicketPDF;
	@FXML private Button btnPrintLabelPDF;
	@FXML private TextArea txtResult;

	private IHardwareService hardwareService;
	private IPrinterService printerService;

	@Override
	public void clear() {
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {

	}

	@FXML public void btnDispenseD1Action(final ActionEvent event) {
		try {
			final DispenserState state = this.hardwareService.dispensePack(Dispenser.A);
			this.txtResult.appendText(state.toString());
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnRechargeD1Action(final ActionEvent event) {
		try {
			final DispenserState state = this.hardwareService.recharge(Dispenser.A,Integer.valueOf(this.txtRecharge.getText()));
			this.txtResult.appendText(state.toString());
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnInventoryD1Action(final ActionEvent event) {
		try {
			final int quantity = this.hardwareService.getInventory(Dispenser.A);
			this.txtResult.appendText("Recharged: " + Integer.toString(quantity));
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnStateD1Action(final ActionEvent event) {
		try {
			final DispenserState state = this.hardwareService.getDispenserState(Dispenser.A);
			this.txtResult.appendText(state.toString());
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnStateBoxAction(final ActionEvent event) {
		try {
			final CollectBoxState state = this.hardwareService.getBoxState(4);
			this.txtResult.appendText(state.toString());
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnOpenBoxAction(final ActionEvent event) {
		try {
			final CollectBoxState state = this.hardwareService.openBox(4);
			this.txtResult.appendText(state.toString());
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnCloseBoxAction(final ActionEvent event) {
		try {
			final CollectBoxState state = this.hardwareService.closeBox(4);
			this.txtResult.appendText(state.toString());
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnAlarmRedAction(final ActionEvent event) {
		try {
			this.hardwareService.alarmError();
			this.txtResult.appendText("Alarmed red!");
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnAlarmYellowAction(final ActionEvent event) {
		try {
			this.hardwareService.alarmWarning();
			this.txtResult.appendText("Alarmed yellow!");
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnAlarmOffAction(final ActionEvent event) {
		try {
			this.hardwareService.alarmOff();
			this.txtResult.appendText("Alarm off!");
			this.txtResult.appendText("\r\n");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnFullstateAction(final ActionEvent event) {
		this.txtResult.appendText("Not implemented!");
		this.txtResult.appendText("\r\n");
	}

	@FXML public void btnKeepAliveAction(final ActionEvent event) {
		this.txtResult.appendText("Not implemented!");
		this.txtResult.appendText("\r\n");
	}

	@FXML public void btnGetWeightAction(final ActionEvent event) {
		try {
			final PackageMeasures pm = this.hardwareService.getMeasures();
			this.txtResult.appendText("Peso: " + pm.getWeight());
			this.txtResult.appendText("\r\n");
		}catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void btnPrintTicketTextAction(final ActionEvent event) {
		this.txtResult.appendText("Not implemented!");
		this.txtResult.appendText("\r\n");
	}

	@FXML public void btnPrintTicketPDFAction(final ActionEvent event) {
		this.txtResult.appendText("Not implemented!");
		this.txtResult.appendText("\r\n");
	}

	@FXML public void btnPrintLabelPDFAction(final ActionEvent event) {
		this.txtResult.appendText("Not implemented!");
		this.txtResult.appendText("\r\n");
	}

	public TextArea getTxtResult() {
		return this.txtResult;
	}

	public void setTxtResult(final TextArea txtResult) {
		this.txtResult = txtResult;
	}

	public void setHardwareService(final IHardwareService hardwareService) {
		this.hardwareService = hardwareService;
	}

	public void warning(final Warning warning) {
		this.txtResult.appendText("Warning Callback! -- " + warning.getMessage());
		this.txtResult.appendText("\r\n");
	}

	public void error(final Error error) {
		this.txtResult.appendText("Error Callback! -- " + error.getMessage());
		this.txtResult.appendText("\r\n");
	}

	public void debug(final Debug debug) {
		this.txtResult.appendText("Debug Callback -- " + debug.getMessage());
		this.txtResult.appendText("\r\n");
	}

	public void working(final Working working) {
		this.txtResult.appendText("Working Callback -- " + working.getMessage());
		this.txtResult.appendText("\r\n");
	}

	public IPrinterService getPrinterService() {
		return this.printerService;
	}

	public void setPrinterService(final IPrinterService printerService) {
		this.printerService = printerService;
	}
}
