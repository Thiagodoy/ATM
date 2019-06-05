package br.com.agencialove.tpa;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import br.com.agencialove.tpa.view.Windows;
import br.com.agencialove.tpa.workflow.Session;
import javafx.scene.Node;
import javafx.stage.Stage;

class MainFlowTest extends ApplicationTest {
	@Override public void start(final Stage stage) {
		new Session(stage);
		stage.setScene(Windows.START.getScene());
		stage.show();
	}

	@Test public void testContainsButtons() throws InterruptedException, TimeoutException {
		//start
		this.clickOn("#btnPost");
		//testConfirmation
		this.clickOn("#btnYes");
		//searchZip
		this.clickOn("#txtCep").write("08130020");
		this.clickOn("#btnSearch");
		final Node rowReceiver = this.lookup(".table-row-cell").nth(0).query();
		this.clickOn(rowReceiver);
		this.clickOn("#btnNext");
		//fullfillAddress
		this.clickOn("#txtNumero").write("126");
		this.clickOn("#txtNome").write("destinatario 1");
		this.clickOn("#txtCPF").write("00401183394");
		this.clickOn("#txtCelular").write("48999007007");
		this.clickOn("#btnNext");
		//confirmAddress
		this.clickOn("#btnNext");
		//searchZip
		this.clickOn("#txtCep").write("07040040");
		this.clickOn("#btnSearch");
		final Node rowSender = this.lookup(".table-row-cell").nth(0).query();
		this.clickOn(rowSender);
		this.clickOn("#btnNext");
		//fullfillAddress
		this.clickOn("#txtNumero").write("123");
		this.clickOn("#txtNome").write("remetente 1");
		this.clickOn("#txtCPF").write("00401183394");
		this.clickOn("#txtCelular").write("48999999999");
		this.clickOn("#btnNext");
		//confirmAddress
		this.clickOn("#btnNext");
		//additionalServices1
		this.clickOn("#chkOwnHand");
		this.clickOn("#chkNotice");
		this.clickOn("#chkValueDeclaration");
		this.clickOn("#btnNext");
		//additionalServices2
		this.clickOn("#txtValue").write("400,00");
		this.clickOn("#txtContentDeclaration").write("Lorem ipsum dolor sit amet.");
		this.clickOn("#btnNext");
		//measures
		this.clickOn("#btnPackageG");
		this.clickOn("#btnMeasures");
		this.clickOn("#btnNext");
		//selectService
		this.clickOn("#04014");
		this.clickOn("#btnNext");
		//nfeChoose
		this.clickOn("#btnYes");
		//chesckout
		this.clickOn("#btnNext");
		//payment
		this.clickOn("#btnYes");
		WaitForAsyncUtils.waitFor(30, TimeUnit.SECONDS,() -> MainFlowTest.this.lookup("#imgInstruction").query().isVisible());
		this.sleep(20000);
		//openBox
		this.clickOn("#btnNext");
		//testSurvey
		this.clickOn("#btnNext");
		//satisfactionSurvey
		//finish
	}

}
