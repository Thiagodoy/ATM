package br.com.agencialove.tpa.workflow;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TestEmail extends Application {

	@BeforeClass
	public static void initJFX() {
		Application.launch(TestEmail.class, new String[0]);
	}

	@Test
	public void testValidEmails(){
		Platform.runLater(()->{
			final TextField[] validEmails = new TextField[] {
					new TextField("email@example.com")
					,new TextField("firstname.lastname@example.com")
					,new TextField("email@subdomain.example.com")
					,new TextField("firstname+lastname@example.com")
					,new TextField("email@123.123.123.123")
					,new TextField("email@[123.123.123.123]")
					,new TextField("\"email\"@example.com")
					,new TextField("1234567890@example.com")
					,new TextField("email@example-one.com")
					,new TextField("_______@example.com")
					,new TextField("email@example.name")
					,new TextField("email@example.museum")
					,new TextField("email@example.co.jp")
					,new TextField("firstname-lastname@example.com")};

			final Validator validator = new Validator();
			for(final TextField email : validEmails) {
				if(validator.validateEmail(email, true) == null)
					System.out.println("[OK] " + email);
				else
					System.err.println("[FAIL]" + email + " " + validator.get(email));
			}

			Assertions.assertTrue(validator.isEmpty());

			final TextField[] inValidEmails = new TextField[] {
					new TextField("plainaddress")
					,new TextField("#@%^%#$@#$@#.com")
					,new TextField("@example.com")
					,new TextField("Joe Smith <email@example.com>")
					,new TextField("email.example.com")
					,new TextField("email@example@example.com")
					,new TextField(".email@example.com")
					,new TextField("email.@example.com")
					,new TextField("email..email@example.com")
					,new TextField("あいうえお@example.com")
					,new TextField("email@example.com (Joe Smith)")
					,new TextField("email@example")
					,new TextField("email@-example.com")
					,new TextField("email@example.web")
					,new TextField("email@111.222.333.44444")
					,new TextField("email@example..com")
					,new TextField("Abc..123@example.com")
			};
			for(final TextField email : inValidEmails) {
				if(validator.validateEmail(email, true) != null)
					System.err.println("[FAIL] " + email);
				else
					System.out.println("[OK]" + email + validator.get(email));
			}

			Assertions.assertEquals(validator.size(), inValidEmails.length);
		});
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
	}
}