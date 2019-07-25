package br.com.agencialove.tpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import br.com.agencialove.tpa.dao.AgenciaDao;
import br.com.agencialove.tpa.model.Agencia;
import br.com.agencialove.tpa.view.Windows;
import br.com.agencialove.tpa.workflow.Session;
import javafx.scene.Node;
import javafx.stage.Stage;

class MainFlowPostagemIncompleta extends ApplicationTest {
	@SuppressWarnings("static-access")
	@Override
	public void start(final Stage stage) {

		Agencia agencia = new Agencia("1", "AC São Bernardo do Campo", "34028316312423", "00024525", "JPS",
				"ATM JPS COMPLETA", "0074290380", "12345678911", "12", "1", "2018-10-09", "21:29:21", "C", "12");
		AgenciaDao.save(agencia);

		Session s = new Session(stage);
		s.setAllMocks();

		stage.setScene(Windows.START.getScene());
		stage.show();
	}

	@Test
	public void testContainsButtons() throws InterruptedException, TimeoutException {

		this.clickOn("#btnPost");
		this.sleep(500);
		this.clickOn("#btnYes");
		this.sleep(500);
		this.clickOn("#txtCep").write("08130020");
		this.clickOn("#btnSearch");
		this.sleep(500);
		final Node rowReceiver = this.lookup(".table-row-cell").nth(0).query();
		this.clickOn(rowReceiver);
		this.sleep(500);
		this.clickOn("#btnNext");
		
		/// Destinatario
		this.clickOn("#txtNome").write("THiago");
		this.clickOn("#txtNumero").write("123");
		this.clickOn("#txtCPF").write("34053285885");
		this.clickOn("#txtCelular").eraseText(1).write("81985109450");
		this.clickOn("#txtEmail").write("laishele@robiel.com.br");
		this.sleep(1000);
		this.clickOn("#btnNext");
		this.sleep(1000);
		this.clickOn("#btnNext");

		
		/// Remetente		
		this.clickOn("#txtCep").write("14806005");
		this.clickOn("#btnSearch");
		this.sleep(500);
		final Node rowReceiver1 = this.lookup(".table-row-cell").nth(0).query();
		this.clickOn(rowReceiver1);
		this.sleep(1000);
		this.clickOn("#btnNext");

		this.clickOn("#txtNome").write("Felipe");
		this.clickOn("#txtNumero").write("123");		
		this.clickOn("#txtCPF").eraseText(11).write("340.536.858-85");
		this.clickOn("#txtCelular").eraseText(1).write("81985109450");
		this.clickOn("#txtEmail").write("maria@aichele.com.br");
		this.sleep(1000);
		this.clickOn("#btnNext");

		this.sleep(1000);
		this.clickOn("#btnNext");

		// Seleciona os servicos adicionais		
		this.sleep(300);
		this.clickOn("#panelMaoPropria");
		this.sleep(500);
		this.clickOn("#btnNext");
		
		this.sleep(1000);
		
		// Definição da embalagem
		this.clickOn("#btnPackageG");
		this.clickOn("#btnMeasures");
		this.sleep(300);
		this.clickOn("#btnNext");
		assertEquals(this.lookup("#btnNext").queryButton().isDisable(), true);
		this.sleep(10000);
		this.clickOn("#panelSedex");
		this.sleep(300);
		this.clickOn("#btnNext");
		this.sleep(2000);
		this.clickOn("#btnYes");

		this.sleep(2000);

		// Checkout
		this.clickOn("#btnNext");
		this.sleep(1000);

		// Pagamento
		this.clickOn("#btnCancelar");
		this.sleep(20000);
		
		this.sleep(1000);
		

		// start
//		this.clickOn("#btnPost");
//		//testConfirmation
//		this.clickOn("#btnYes");
//		//searchZip
//		this.clickOn("#txtCep").write("08130020");
//		this.clickOn("#btnSearch");
//		final Node rowReceiver = this.lookup(".table-row-cell").nth(0).query();
//		this.clickOn(rowReceiver);
//		this.clickOn("#btnNext");
//		//fullfillAddress
//		this.clickOn("#txtNumero").write("126");
//		this.clickOn("#txtNome").write("destinatario 1");
//		this.clickOn("#txtCPF").write("00401183394");
//		this.clickOn("#txtCelular").write("48999007007");
//		this.clickOn("#btnNext");
//		//confirmAddress
//		this.clickOn("#btnNext");
//		//searchZip
//		this.clickOn("#txtCep").write("07040040");
//		this.clickOn("#btnSearch");
//		final Node rowSender = this.lookup(".table-row-cell").nth(0).query();
//		this.clickOn(rowSender);
//		this.clickOn("#btnNext");
//		//fullfillAddress
//		this.clickOn("#txtNumero").write("123");
//		this.clickOn("#txtNome").write("remetente 1");
//		this.clickOn("#txtCPF").write("00401183394");
//		this.clickOn("#txtCelular").write("48999999999");
//		this.clickOn("#btnNext");
//		//confirmAddress
//		this.clickOn("#btnNext");
//		//additionalServices1
//		this.clickOn("#chkOwnHand");
//		this.clickOn("#chkNotice");
//		this.clickOn("#chkValueDeclaration");
//		this.clickOn("#btnNext");
//		//additionalServices2
//		this.clickOn("#txtValue").write("400,00");
//		this.clickOn("#txtContentDeclaration").write("Lorem ipsum dolor sit amet.");
//		this.clickOn("#btnNext");
//		//measures
//		this.clickOn("#btnPackageG");
//		this.clickOn("#btnMeasures");
//		this.clickOn("#btnNext");
//		//selectService
//		this.clickOn("#04014");
//		this.clickOn("#btnNext");
//		//nfeChoose
//		this.clickOn("#btnYes");
//		//chesckout
//		this.clickOn("#btnNext");
//		//payment
//		this.clickOn("#btnYes");
//		WaitForAsyncUtils.waitFor(30, TimeUnit.SECONDS,() -> MainFlowTest.this.lookup("#imgInstruction").query().isVisible());
//		this.sleep(20000);
//		//openBox
//		this.clickOn("#btnNext");
//		//testSurvey
//		this.clickOn("#btnNext");
		// satisfactionSurvey
		// finish
	}

}
