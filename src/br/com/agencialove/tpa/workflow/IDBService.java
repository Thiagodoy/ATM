package br.com.agencialove.tpa.workflow;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import br.com.agencialove.tpa.model.Data;
import br.com.agencialove.tpa.model.Encomenda;
import br.com.agencialove.tpa.model.Pack;

public interface IDBService {
	Pack getPackById(String id) throws SQLException;

	boolean appendPack(Pack pack, InputStream image) throws SQLException;

	void createDBIfNoExits() throws SQLException;

	List<Pack> listPacks() throws SQLException;

	boolean insertData(Data data) throws SQLException;
	//boolean insertEncomenda(Encomenda encomenda) throws SQLException;

	Data getDataById(String id) throws SQLException;

	List<Data> listDados() throws SQLException;
}
