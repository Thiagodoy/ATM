package br.com.agencialove.tpa;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import br.com.agencialove.tpa.workflow.Validator;
import javafx.scene.control.TextField;

public class TestValidator {
	
	public static void main(final String[] args) throws SQLException, FileNotFoundException {
		Validator validator = new Validator();
		
		
		TextField field = new TextField();
		field.setText("12");
		
		System.out.println("msg ->" + validator.validateValue(field, true, 20, 1000));
	}

}
