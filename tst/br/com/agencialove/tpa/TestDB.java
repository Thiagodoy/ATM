package br.com.agencialove.tpa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.workflow.IDBService;
import br.com.agencialove.tpa.workflow.Session;

public class TestDB {
	public static void main(final String[] args) throws SQLException, FileNotFoundException {
		final IDBService service = Session.getDBService();

		service.createDBIfNoExits();

		final Pack p1 = new Pack("1","Caixa", "18 x 13,5 x 9 cm","R$ 10,00", "A");
		final Pack p2 = new Pack("2","Caixa", "27 x 18 x 9 cm","R$ 20,00", "B");
		final Pack p3 = new Pack("3","Caixa", "27 x 22,5 x 13,5 cm","R$ 30,00", "C");
		final Pack p4 = new Pack("4","Envelope", "18 x 13,5","R$ 15,00", "D");
		service.appendPack(p1, new FileInputStream("res/img/caixa.png"));
		service.appendPack(p2, new FileInputStream("res/img/caixa.png"));
		service.appendPack(p3, new FileInputStream("res/img/caixa.png"));
		service.appendPack(p4, new FileInputStream("res/img/envelope.jpg"));
	}
}
