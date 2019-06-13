package br.com.agencialove.tpa.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public enum Windows {
	/* *************
	 * * Main Flow *
	 * *************/
	DESCANSO("DESCANSO","Descanso.fxml"), //Start screen
	START("START_WINDOW","Start.fxml"), //Start screen
	TEST_CONFIRMATION("TEST_CONFIRMATION_WINDOW","TestConfirmation.fxml"), //Device on test confirmation to proceed
	SEARCH_ZIP("SEARCH_ZIP_WINDOW","SearchZip.fxml"), //Search ZipCode
	FULFILL_ADDRESS("FULFILL_ADDRESS_WINDOW","FulfillAddress.fxml"), //Add person data
	CONFIRM_ADDRESS("CONFIRM_ADDRESS_WINDOW","ConfirmAddress.fxml"), //Confirm the person data
	ADDITIONAL_SERVICES1("ADITIONAL_SERVICES1_WINDOW","AdditionalServices1.fxml"),
	MEASURES("MEASURES_WINDOW","Measures.fxml"),
	SELECT_SERVICE("SELECT_SERVICE_WINDOW","SelectService.fxml"),
	NFE_CHOOSE("NFE_CHOOSE_WINDOW","NfeChoose.fxml"),
	CHECKOUT("CHECKOUT_WINDOW", "Checkout.fxml"),
	PAYMENT("PAYMENT_WINDOW","Payment.fxml"),
	PRE_POSTAGEM_CONFIRMATION_CONTROLLER("PRE_POSTAGEM_CONFIRMATION_CONTROLLER_WINDOW", "PrePostagemConfirmation.fxml"),
	REMOVE_STICK("REMOVE_STICK_WINDOW","RemoveStick.fxml"),
	OPEN_BOX("OPEN_BOX_WINDOW", "OpenBox.fxml"),
	TEST_SURVEY("TEST_SURVEY_WINDOW","TestSurvey.fxml"),
	SATISFACTION_SURVEY("SATISFACTION_SURVEY_WINDOW","SatisfactionSurvey.fxml"),
	FINISH("FINISHING_WINDOW","Finish.fxml"),

	//Do not post

	/* *********************
	 * * Alternative Flows *
	 * *********************/
	/* *** Branchs of main Flow *** */
	ADDITIONAL_SERVICES2("ADITIONAL_SERVICES2_WINDOW","AdditionalServices2.fxml"),
	CONTENT_DECLARATION("CONTENT_DECLARATION_WINDOW","ContentDeclaration.fxml"),
	/* *** Consult web *** */
	WEB_BROWSER("WEB_BROWSER_WINDOW","WebBrowser.fxml"), //Web browser screen

	//Conclude pre-posting
	PRE_POSTING ("PRE_POSTING_WINDOW","PrePosting.fxml"),
	CONFIRM_PRE_POST ("CONFIRM_PRE_POST_WINDOW","ConfirmPrePost.fxml"),
	MEASURES_PRE_POST ("MEASURES_PRE_POST_WINDOW","MeasuresPrePost.fxml"),

	//SEARCH("SEARCH_WINDOWS", "Search.fxml"),

	/* *** Buy package flow *** */
	SELECT_PACKAGE("SELECT_PACKAGE_WINDOW","SelectPackage.fxml"),
	REMOVE_PACKAGE("REMOVE_PACKAGE_WINDOW","RemovePackage.fxml"),

	/* *********************
	 * * Exceptional flows *
	 * *********************/
	WRONG_MEASUREMENTS("WRONG_MEASUREMENTS_WINDOW","WrongMeasurements.fxml"),
	OVER_ATTEMPTS("OVER_ATTEMPTS_WINDOW","OverAttempts.fxml"),
	ERROR("INTERNA_ERROR_WINDOW","Error.fxml"),
	//WRONG_MEASUREMENTS("WRONG_MEASUREMENTS_WINDOW","WrongMeasurements.fxml"),

	//Post number wrong
	POSTING_NUMBER_WRONG ("POSTING_NUMBER_WRONG_WINDOW","PostingNumberWrong.fxml");
	//INTERNET_TIME_OUT("INTERNET_TIME_OUT_WINDOW","InternetTimeOut.fxml"),

	/* *********************
	 * * Buy Package flows *
	 * *********************/


	//HARDWARE_FAILURE("HARDWARE_FAILURE_WINDOW","HardwareFailure.fxml"),

	private String label;
	private String fileName;
	private Scene scene;

	private Windows(final String plabel, final String pFileName){
		this.label = plabel;
		this.fileName = pFileName;
		try {
			final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(this.getFileName()));
			this.scene = new Scene(loader.load());
			this.scene.setUserData(loader.getController());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getLabel() {
		return this.label;
	}

	public String getFileName() {
		return this.fileName;
	}

	public Scene getScene() {
		return this.scene;
	}
}
