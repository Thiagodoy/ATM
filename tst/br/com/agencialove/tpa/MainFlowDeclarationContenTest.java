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

class MainFlowDeclarationContenTest extends ApplicationTest {
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
		this.clickOn("#txtNome").write("Benício Nelson Ian Nascimento");
		this.clickOn("#txtNumero").write("123");
		this.clickOn("#txtCPF").write("827	26346006");
		this.clickOn("#txtCelular").eraseText(1).write("81985109450");
		this.clickOn("#txtEmail").write("laishele@robiel.com.br");
		this.sleep(1000);
		this.clickOn("#btnNext");
		this.sleep(1000);
		this.clickOn("#btnNext");

		this.clickOn("#txtCep").write("14806005");
		this.clickOn("#btnSearch");
		this.sleep(500);
		final Node rowReceiver1 = this.lookup(".table-row-cell").nth(0).query();
		this.clickOn(rowReceiver1);
		this.sleep(1000);
		this.clickOn("#btnNext");

		this.clickOn("#txtNome").write("Mariah Agatha Luana Lopes");
		this.clickOn("#txtNumero").write("123");
		this.clickOn("#txtCPF").write("34053685885");
		this.clickOn("#txtCelular").eraseText(1).write("81985109450");
		this.clickOn("#txtEmail").write("maria@aichele.com.br");
		this.sleep(1000);
		this.clickOn("#btnNext");

		this.sleep(1000);
		this.clickOn("#btnNext");

		// Seleciona os servicos adicionais
		this.clickOn("#panelSemServicoAdicional");
		this.sleep(300);
		this.clickOn("#panelMaoPropria");
		this.sleep(300);
		this.clickOn("#panelDeclaracaoValor");
		this.sleep(500);
		this.clickOn("#btnNext");

		// Declaração de Conteudo
		this.clickOn("#txtDescription").write("Relogio Rolex");
		this.clickOn("#txtQuantity").write("2");
		this.clickOn("#txtValue").write("20");
		this.clickOn("#btnAdd");
		this.sleep(500);
		this.clickOn("#txtDescription").write("Iphone X");
		this.clickOn("#txtQuantity").write("1");
		this.clickOn("#txtValue").write("200");
		this.clickOn("#btnAdd");
		this.sleep(300);
		this.clickOn("#btnNext");
		this.sleep(1000);
		// Definição da embalagem
		this.clickOn("#btnPackageG");
		this.clickOn("#btnMeasures");
		this.sleep(300);
		this.clickOn("#btnNext");

		assertEquals(this.lookup("#btnNext").queryButton().isDisable(), true);
		this.sleep(300);
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
		this.clickOn("#btnProsseguir");
		this.sleep(10000);
		this.clickOn("#btnNext");
		this.sleep(1000);
		// coleta
		this.clickOn("#btnNext");
		this.sleep(1000);

		// Pesquisa Satisfacao
		this.clickOn("#btnYes");
		this.sleep(1000);
		
		//Quiz
		this.clickOn("#questaoUmFacil");
		this.sleep(500);
		this.clickOn("#questaoDoisIqual");
		this.sleep(500);
		this.clickOn("#questaoTresNao");
		this.sleep(500);
		this.clickOn("#questaoQuatroOtimo");
		this.sleep(500);
		this.clickOn("#txtArea").write("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod \n "
				+ "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation \n "
				+ "ullamco laboris nisi ut aliquip ex ea commodo consequat.");

		
		this.clickOn("#btnNext");
		
		// Print o comprovante
		this.clickOn("#btnPrintReceipt");
		

		this.sleep(5000);

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
