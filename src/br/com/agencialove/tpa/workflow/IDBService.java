package br.com.agencialove.tpa.workflow;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import br.com.agencialove.tpa.model.Agencia;
import br.com.agencialove.tpa.model.Postagem;
import br.com.agencialove.tpa.model.Pack;

public interface IDBService {
	Pack getPackById(String id) throws SQLException;

	boolean appendPack(Pack pack, InputStream image) throws SQLException;

	void createDBIfNoExits() throws SQLException;

	List<Pack> listPacks() throws SQLException;

	boolean insertData(Agencia data) throws SQLException;
	//boolean insertEncomenda(Encomenda encomenda) throws SQLException;

	Agencia getDataById(String id) throws SQLException;

	List<Agencia> listDados() throws SQLException;
}
