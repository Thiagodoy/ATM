package br.com.agencialove.tpa.hardware;

import java.io.IOException;

import br.com.agencialove.tpa.hardware.arduino.CollectBoxState;
import br.com.agencialove.tpa.hardware.arduino.Dispenser;
import br.com.agencialove.tpa.hardware.arduino.DispenserState;
import br.com.agencialove.tpa.model.PackageMeasures;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;;

public class HardwareServiceMock implements IHardwareService {

	private int counterD1 = 0;
	private int counterD2 = 0;
	private int counterD3 = 0;
	private int counterD4 = 0;
	private String volumeMeasure;

	public HardwareServiceMock() {
	}

	@Override
	public DispenserState getDispenserState(final Dispenser d) throws IOException {
		return DispenserState.IDLE;
	}

	@Override
	public DispenserState dispensePack(final Dispenser dispenser) throws IOException {

		switch (dispenser) {
		case A:
			-- this.counterD1;
			break;
		case B:
			-- this.counterD2;
			break;
		case C:
			-- this.counterD3;
			break;
		case D:
			-- this.counterD4;
			break;
		default:
			throw new IOException("Dispenser inválido");
		}

		Platform.runLater(()->{
			final Alert alert = new Alert(AlertType.WARNING);
			final Label label = new Label("Pacote dispensado no dispenser: " + dispenser.getId());

			final GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);

			alert.getDialogPane().setExpandableContent(expContent);
			alert.setHeight(800);

			alert.showAndWait();});
		return DispenserState.IDLE;
	}

	@Override
	public int getInventory(final Dispenser d) throws IOException {
		switch (d.getId()) {
		case 1:
			return this.counterD1;
		case 2:
			return this.counterD2;
		case 3:
			return this.counterD3;
		case 4:
			return this.counterD4;
		}
		return 0;
	}

	@Override
	public CollectBoxState getBoxState(Integer boxId) throws IOException {
		return CollectBoxState.CLOSED;
	}

	@Override
	public CollectBoxState openBox(Integer boxId) throws IOException {
		return CollectBoxState.OPEN_FULL;
	}

	@Override
	public CollectBoxState closeBox(Integer boxId) throws IOException {
		return CollectBoxState.CLOSED;
	}

	@Override
	public void turnOnScale() throws IOException {
		Platform.runLater(()->{
			final Alert alert = new Alert(AlertType.INFORMATION);
			final Label label = new Label("Balança ligada!");

			final GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);

			alert.getDialogPane().setExpandableContent(expContent);
			alert.setHeight(800);

			alert.showAndWait();});
	}


	public void getDimensions(final String dHeight, final String dWidth, final String dDiameter, final String dDepth) {

		//this.setChargeView();

		//final ITEFService tefService = Session.getTEFService();

		//tefService.charge(this.value, this.code, this.description, new PaymentTEFCallback(stage));
	}

	@Override
	public PackageMeasures getMeasures() throws IOException{
		final PackageMeasures ret = new PackageMeasures();
		//final InputPackageController p = InputPackageController.getInstance();
		ret.setHeight("9");
		ret.setWidth("13.5");
		ret.setDepth("18");
		ret.setDiameter("0");
		return ret;
	}
	@Override
	public void alarmWarning() {
		Platform.runLater(()->{
			final Alert alert = new Alert(AlertType.WARNING);
			final Label label = new Label("Lâmpada amarela acesa!");

			final GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);

			alert.getDialogPane().setExpandableContent(expContent);
			alert.setHeight(800);

			alert.showAndWait();});

	}

	@Override
	public void alarmError() {
		Platform.runLater(()->{
			final Alert alert = new Alert(AlertType.ERROR);
			final Label label = new Label("Lâmpada vermelha acesa!");

			final GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);

			alert.getDialogPane().setExpandableContent(expContent);
			alert.setHeight(800);

			alert.showAndWait();});
	}

	@Override
	public void alarmOff() {
		Platform.runLater(()->{
			final Alert alert = new Alert(AlertType.INFORMATION);
			final Label label = new Label("Lâmpadas desligadas!");

			final GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);

			alert.getDialogPane().setExpandableContent(expContent);
			alert.setHeight(800);

			alert.showAndWait();});
	}

	@Override
	public void setHardwareCallback(final IHardwareCallback callback) {
	}

	@Override
	public DispenserState recharge(final Dispenser dispenser, final int quantity) throws IOException {
		switch (dispenser) {
		case A:
			this.counterD1 = quantity;
			break;
		case B:
			this.counterD2 = quantity;
			break;
		case C:
			this.counterD3 = quantity;
			break;
		case D:
			this.counterD4 = quantity;
			break;
		default:
			throw new IOException("Dispenser inválido");
		}

		return DispenserState.IDLE;
	}

	@Override
	public String getVolumeMeasured() {
		return this.volumeMeasure;
	}

	@Override
	public void setVolumeMeasured(final String volumeMeasured) {
		this.volumeMeasure = volumeMeasured;
	}

}

/*
 * EXEMPLO DE REQUISI��O BEM SUCEDIDA
 *
	req.setCepOrigem("01212001");
	req.setCepDestino("71931180");
	req.setPeso("10");
	req.setFormato("1");
	req.setComprimento("18");
	req.setAltura("10");
	req.setLargura("20");
	req.setDiametro("0");
	req.setMaoPropria("N");
	req.setValorDeclarado("0");
	req.setAvisoRecebimento("N");
 */
