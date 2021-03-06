package br.com.agencialove.tpa;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import br.com.agencialove.tpa.dao.AgenciaDao;
import br.com.agencialove.tpa.model.Agencia;
import br.com.agencialove.tpa.view.Windows;
import br.com.agencialove.tpa.workflow.Session;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(final String[] args) throws SQLException, FileNotFoundException{
		Session.setAllMocks();	
		

		FolderConfiguration.config();
		JobConfiguration.config();

		//	FolderConfiguration.config();

		//final IDBService dbService = Session.getDBService();

		//dbService.createDBIfNoExits();
//		final Pack p1 = new Pack("1","Caixa", "18 x 13,5 x 9 cm","R$ 10,00", "A");
//		final Pack p2 = new Pack("2","Caixa", "27 x 18 x 9 cm","R$ 20,00", "B");
//		final Pack p3 = new Pack("3","Caixa", "27 x 22,5 x 13,5 cm","R$ 30,00", "C");
//		final Pack p4 = new Pack("4","Envelope", "18 x 13,5","R$ 15,00", "D");
//		dbService.appendPack(p1, new FileInputStream("res/img/caixa.png"));
//		dbService.appendPack(p2, new FileInputStream("res/img/caixa.png"));
//		dbService.appendPack(p3, new FileInputStream("res/img/caixa.png"));
//		dbService.appendPack(p4, new FileInputStream("res/img/envelope.jpg"));

		Agencia agencia = new Agencia("1", "AC São Bernardo do Campo", "34028316312423", "00024525", "JPS", "ATM JPS COMPLETA", "0074290380", "12345678911", "12", "1", "2018-10-09", "21:29:21", "C", "12");
		AgenciaDao.save(agencia);	

		Application.launch(Main.class, args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		new Session(primaryStage);
		Session.setScene(Windows.START.getScene());
		Platform.setImplicitExit(true);
		primaryStage.show();
	}

}
