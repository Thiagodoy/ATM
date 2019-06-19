
package br.com.agencialove.tpa.workflow;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.PrintException;

import br.com.agencialove.tpa.hardware.HardwareServiceImpl;
import br.com.agencialove.tpa.hardware.HardwareServiceMock;
import br.com.agencialove.tpa.hardware.IHardwareCallback;
import br.com.agencialove.tpa.hardware.IHardwareService;
import br.com.agencialove.tpa.hardware.IPrinterService;
import br.com.agencialove.tpa.hardware.ITEFService;
import br.com.agencialove.tpa.hardware.PrinterServiceImpl;
import br.com.agencialove.tpa.hardware.PrinterServiceMock;
import br.com.agencialove.tpa.hardware.TEFServiceImpl;
import br.com.agencialove.tpa.hardware.TEFServiceMock;
import br.com.agencialove.tpa.hardware.arduino.Debug;
import br.com.agencialove.tpa.hardware.arduino.Error;
import br.com.agencialove.tpa.hardware.arduino.Warning;
import br.com.agencialove.tpa.hardware.arduino.Working;
import br.com.agencialove.tpa.model.OverAttemptsType;
import br.com.agencialove.tpa.view.IController;
import br.com.agencialove.tpa.view.OverAttemptsController;
import br.com.agencialove.tpa.view.StartController;
import br.com.agencialove.tpa.view.Windows;
import br.com.agencialove.tpa.webservices.IWebService;
import br.com.agencialove.tpa.webservices.WebServiceImpl;
import br.com.agencialove.tpa.webservices.WebServiceMock;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Session{

	public static final String SENDER_ADDRESS = "SENDER_ADDRESS"; //$NON-NLS-1$
	public static final String RECEIVER_ADDRESS = "RECEIVER_ADDRESS"; //$NON-NLS-1$
	public static final String ADDITIONAL_SERVICES = "ADDITIONAL_SERVICES"; //$NON-NLS-1$
	public static final String MEASURES = "MEASURES"; //$NON-NLS-1$
	public static final String SELECTED_SERVICE = "SELECTED_SERVICE"; //$NON-NLS-1$
	public static final String AVAILABLE_SERVICES = "SELECTED_SERVICE_PRE_POST"; //$NON-NLS-1$
	public static final String SELECTED_PACKAGE = "SELECTED_PACKAGE"; //$NON-NLS-1$
	public static final String LABEL_INFO = "LABEL_INFO"; //$NON-NLS-1$
	public static final String SESSION_TYPE = "SESSION_TYPE"; //$NON-NLS-1$
	public static final String INPUT_PACKAGE = "INPUT_PACKAGE"; //$NON-NLS-1$
	public static final String PRE_POST = "PRE_POST"; //$NON-NLS-1$
	public static final String ID_PLP = "ID_PLP"; //$NON-NLS-1$

	public static final int MAX_BARCODE_ATTEMPS = 10;
	private static final long ERROR_SCREEN_DELAY = 10000;
	private static final long OVERATTEMPTS_SCREEN_DELAY = 10000;
	private static final long ABORT_SCREEN_DELAY = 10000;

	private static IHardwareService hardwareService;
	private static ITEFService tefService;
	private static IPrinterService printerService;
	private static IWebService webService;
	private static IDBService dbService;

	private static Stage primaryStage;
	private static Map<String,Object> SESSION = new HashMap<>();
	private static Timer timer = new Timer();


	public Session(final Stage pPrimaryStage) {
		Session.primaryStage = pPrimaryStage;
		Session.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		//Session.primaryStage.setFullScreen(true);
	}

	public static Map<String,Object> getSession() {
		if(Session.SESSION == null)
			Session.SESSION = new HashMap<>();
		return Session.SESSION;
	}

	public static IHardwareService getHardwareService() {
		if(Session.hardwareService == null)
			Session.hardwareService = new HardwareServiceImpl(new HardwareCallbackImpl());
		return Session.hardwareService;
	}

	public static ITEFService getTEFService() {
		if(Session.tefService == null)
			Session.tefService = new TEFServiceImpl();

		return Session.tefService;
	}

	public static IPrinterService getPrinterService() {
		if(Session.printerService == null)
			Session.printerService = new PrinterServiceImpl();

		return Session.printerService;
	}

	public static void reset() {
		for(final Windows w : Windows.values()) {
			final IController controller = (IController) w.getScene().getUserData();
			controller.clear();
		}

		Session.SESSION = new HashMap<>();

		Session.primaryStage.setScene(Windows.START.getScene());

	}

	public static void error(){

		final IPrinterService service = Session.getPrinterService();
		try {
			service.printTicket((String)Session.SESSION.get(Session.LABEL_INFO));
		} catch (final PrintException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erro na rotina de erro ao tentar imprimir o ticket de pré postagem", e);
		}

		Session.primaryStage.setScene(Windows.ERROR.getScene());
		try {
			Session.hardwareService.alarmError();
		} catch (final IOException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erro na rotina de erro ao tentar ligar o alarme vermelho",e);
		}

		Session.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(()->{
					Session.reset();
				});
			}
		}, Session.ERROR_SCREEN_DELAY);

		Logger.getAnonymousLogger().log(Level.SEVERE, "O sistema entrou na tela de erro!");
	}

	public static void overAttempts(final OverAttemptsType type){

		final IPrinterService service = Session.getPrinterService();
		try {
			service.printTicket((String)Session.SESSION.get(Session.LABEL_INFO));
		} catch (final PrintException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erro na rotina de erro ao tentar imprimir o ticket de pré postagem", e);
		}

		final Scene scene = Windows.OVER_ATTEMPTS.getScene();
		final OverAttemptsController controller = (OverAttemptsController) scene.getUserData();
		switch (type) {
		case BARCODE:
			controller.setText("Não foi possível reconhecer o código de barras. Retire o recibo impresso e dirija-se ao caixa.");
			break;
		case PAYMENT:
			controller.setText("Limite de tentativas de pagamento excedido. Retire o recibo impresso e dirija-se ao caixa.");
		}
		Session.primaryStage.setScene(Windows.OVER_ATTEMPTS.getScene());

		Session.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(()->{
					Session.reset();
				});
			}
		}, Session.OVERATTEMPTS_SCREEN_DELAY);
	}

	public static void abort(){
		final IPrinterService service = Session.getPrinterService();
		try {
			service.printTicket((String)Session.SESSION.get(Session.LABEL_INFO));
		} catch (final PrintException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Erro na rotina de erro ao tentar imprimir o ticket de pré postagem", e);
		}

		Session.primaryStage.setScene(Windows.REMOVE_STICK.getScene());

		Session.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(()->{
					Session.reset();
				});
			}
		}, Session.ABORT_SCREEN_DELAY);
	}

	public static void setAllMocks() {
		Session.hardwareService = new HardwareServiceMock();
		Session.tefService = new TEFServiceMock(Session.primaryStage);
		Session.printerService = new PrinterServiceMock();
		Session.webService = new WebServiceMock();
		Session.dbService = Session.getDBService();
	}

	public static void setSomeMocks() {
		Session.hardwareService = new HardwareServiceMock();
		Session.tefService = new TEFServiceMock(Session.primaryStage);
		Session.printerService = new PrinterServiceMock();
		//Session.webService = new WebServiceMock();
		//Session.printerService = Session.getPrinterService();
		Session.webService = Session.getWebService();
		Session.dbService = Session.getDBService();
	}

	public static void setAllImpl() {
		Session.hardwareService = Session.getHardwareService();
		Session.tefService = Session.getTEFService();
		Session.printerService = Session.getPrinterService();
		Session.webService = Session.getWebService();
		Session.dbService = Session.getDBService();
	}

	public static IWebService getWebService() {
		if(Session.webService == null)
			Session.webService = new WebServiceImpl();

		return Session.webService;
	}

	public static void setWebService(final IWebService webService) {
		Session.webService = webService;
	}

	public static IDBService getDBService() {
		if(Session.dbService == null)
			try {
				Session.dbService = new DBServiceImpl("jdbc:hsqldb:file:store");
			}catch (final SQLException e) {
				Logger.getAnonymousLogger().log(Level.SEVERE, "Erro no banco de dados", e);
				Session.error();
			}
		return Session.dbService;
	}

	private static class HardwareCallbackImpl implements IHardwareCallback{

		@Override
		public void callbackException(final Exception e){
			Session.error();
		}

		@Override
		public void callbackWarning(final Warning warning) {
			try {
				Session.getHardwareService().alarmWarning();
			} catch (final IOException e) {
				Logger.getAnonymousLogger().log(Level.WARNING, "Erro ao ligar a lâmpada amarela! ",e);
			}
		}

		@Override
		public void callbackErro(final Error error) {
			Logger.getAnonymousLogger().log(Level.WARNING, "[[Callback - Erro]]  " + error.getMessage());
		}

		@Override
		public void callbackDebug(final Debug debug) {
			Logger.getAnonymousLogger().log(Level.WARNING, "[[Callback - Debug]]  " + debug.getMessage());
		}

		@Override
		public void callbackWorking(final Working working) {
			Logger.getAnonymousLogger().log(Level.WARNING, "[[Callback - Working]]  " + working.getMessage());
		}

	}

	//TODO pensar outro lugar para colocar esse método getTotal
	public static String getTotal(final String vService, final String vOwnsHand, final String vDeliveryNotice, final String vValueDeclaration) {
		double ret = 0;
		ret += Double.parseDouble(vService.replace(',', '.'));
		ret += Double.parseDouble(vOwnsHand.replace(',', '.'));
		ret += Double.parseDouble(vValueDeclaration.replace(',', '.'));

		return Double.toString(ret).replace('.', ',');
	}

	public static void setScene(final Scene scene) {
		Session.primaryStage.setScene(scene);
		//Session.primaryStage.setFullScreen(true);
		
		if(scene.getUserData() instanceof StartController) {
			StartController controller = (StartController) scene.getUserData();
			controller.activeRest();
		}
	}

}
