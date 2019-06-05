package br.com.agencialove.tpa;

import br.com.agencialove.tpa.view.ContentDeclarationController;
import br.com.agencialove.tpa.view.Windows;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestIndividualScreens extends Application {

	public static void main(final String[] args) {
		Application.launch(args);
	}

	/*

	ADDITIONAL_SERVICES2("ADITIONAL_SERVICES2_WINDOW","AdditionalServices2.fxml"),
	CONTENT_DECLARATION("CONTENT_DECLARATION_WINDOW","ContentDeclaration.fxml"),
	WEB_BROWSER("WEB_BROWSER_WINDOW","WebBrowser.fxml"), //Web browser screen
	FINISH_POSTING_CONFIRMATION ("FINISH_POSTING_CONFIRMATION_WINDOW","FinishPostingConfirmation.fxml"),
	PRE_POSTING ("PRE_POSTING_WINDOW","PrePosting.fxml"),
	CONFIRM_PRE_POST ("CONFIRM_PRE_POST_WINDOW","ConfirmPrePost.fxml"),
	MEASURES_PRE_POST ("MEASURES_PRE_POST_WINDOW","MeasuresPrePost.fxml"),
	CONFIRM_PRE_POST_REMETENTE ("CONFIRM_PRE_POST__REMETENTE_WINDOW","ConfirmPrePostRemetente.fxml"),
	FULFILL_ADDRESS_PRE_POSTING("FULFILL_ADDRESS_PRE_POSTING_WINDOW", "FullfillAdressPrePosting.fxml"),
	PRE_POSTAGEM_CONFIRMATION_CONTROLLER("PRE_POSTAGEM_CONFIRMATION_CONTROLLER_WINDOW", "PrePostagemConfirmation.fxml"),
	SEARCH("SEARCH_WINDOWS", "Search.fxml"),
	SELECT_PACKAGE("SELECT_PACKAGE_WINDOW","SelectPackage.fxml"),
	REMOVE_PACKAGE("REMOVE_PACKAGE_WINDOW","RemovePackage.fxml"),


	 */

	@Override
	public void start(final Stage primaryStage) throws Exception {

		final Scene scene = Windows.CONTENT_DECLARATION.getScene();
		final ContentDeclarationController controller = (ContentDeclarationController) scene.getUserData();
		controller.clear();

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}