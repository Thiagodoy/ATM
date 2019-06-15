package br.com.agencialove.tpa.workflow;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.model.Agencia;
import javafx.scene.image.Image;

public class DBServiceMock implements IDBService{

	private final Connection conn;

	public DBServiceMock(final String connectionString) throws SQLException {
		this.conn = DriverManager.getConnection(connectionString, "SA", "");
	}

	@Override
	public Pack getPackById(final String pCode) throws SQLException {
		final PreparedStatement ps = this.conn.prepareStatement("SELECT txCodigo, txDescricao, txDimensoes, txValor, txDispenser, blobImage FROM pacotes WHERE txCodigo = ?");
		ps.setString(1, pCode);
		final ResultSet rs = ps.executeQuery();

		Pack ret = null;
		while(rs.next()) {
			ret = new Pack();
			ret.setCodigo(rs.getString("txCodigo"));
			ret.setDescricao(rs.getString("txDescricao"));
			ret.setDimensoes(rs.getString("txDimensoes"));
			ret.setValor(rs.getString("txValor"));
			ret.setDispenser(rs.getString("txDispenser"));
			ret.setImagem(new Image(rs.getBinaryStream("blobImage")));
		}

		ps.close();

		return ret;
	}
	
	@Override
	public Agencia getDataById(final String dCode) throws SQLException {
		final PreparedStatement ps = this.conn.prepareStatement("SELECT txCodigo, txNomeAgencia, txCNPJAgencia, txMcuUnidade, txNomeFornecedor, txModeloAtm, txCartaoPostagem, txNumeroPlp, txRegistroObjeto, txAutorizacaoDoPagamento, txData, txHora, txCreditoDepito, txValorVenda FROM dados WHERE txCodigo = ?");
		ps.setString(1, dCode);
		final ResultSet rs = ps.executeQuery();

		Agencia ret = null;
		while(rs.next()) {
			ret = new Agencia();
			ret.setCodigo(rs.getString("txCodigo"));
			ret.setNomeAgencia(rs.getString("txNomeAgencia"));
			ret.setCnpjAgencia(rs.getString("txCnpjAgencia"));
			ret.setMcuUnidade(rs.getString("txMcuUnidade"));
			ret.setNomeFornecedor(rs.getString("txNomeFornecedor"));
			ret.setModeloAtm(rs.getString("txModeloAtm"));
			ret.setCartaoPostagem(rs.getString("txCartaoPostagem"));
			ret.setNumeroPlp(rs.getString("txNumeroPlp"));
			ret.setRegistroObjejeto(rs.getString("txRegistroObjeto"));
			ret.setNomeAgencia(rs.getString("txNomeAgencia"));
			ret.setAutorizacaoPagamento(rs.getString("txAutorizacaoPagamento"));
			ret.setData(rs.getString("txData"));
			ret.setHora(rs.getString("txHora"));
			ret.setCreditoDebito(rs.getString("txCreditoDebito"));
			ret.setValorVenda(rs.getString("txValorVenda"));
		}

		ps.close();

		return ret;
	}

	@Override
	public void createDBIfNoExits() throws SQLException {
		final PreparedStatement infoTables = this.conn.prepareStatement("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'PACOTES'");

		final ResultSet rs = infoTables.executeQuery();

		if(!rs.next()) {
			final PreparedStatement ps = this.conn.prepareStatement("CREATE TABLE pacotes (txCodigo VARCHAR(4) NOT NULL, txDescricao VARCHAR(50) NOT NULL, txDimensoes VARCHAR(50) NOT NULL, txValor VARCHAR(8) NOT NULL, txDispenser CHAR NOT NULL, blobImage BLOB, PRIMARY KEY(txCodigo))");
			ps.execute();
			ps.close();
			final PreparedStatement dd = this.conn.prepareStatement("CREATE TABLE dados (txCodigo VARCHAR(4) NOT NULL, txNomeAgencia VARCHAR(100), txCNPJAgencia INTEGER(14), txMcuUnidade INTEGER(8), txNomeFornecedor VARCHAR(20), txModeloAtm VARCHAR(10), txCartaoPostagem INTEGER(10), txNumeroPlp INTEGER(11), txRegistroObjeto VARCHAR(12), txAutorizacaoDoPagamento VARCHAR(100), txData DATE, txHora DATE,	txCreditoDepito CHAR(1), txValorVenda FLOAT(7))");
			dd.execute();
			dd.close();
		}
	}

	@Override
	public boolean appendPack(final Pack pack, final InputStream image) throws SQLException {
		final Pack p = this.getPackById(pack.getCodigo());
		final PreparedStatement ps;
		if(p == null) {
			ps = this.conn.prepareStatement("INSERT INTO pacotes (txCodigo, txDescricao, txDimensoes, txValor, txDispenser, blobImage) VALUES (?,?,?,?,?,?)");
			ps.setString(1, pack.getCodigo());
			ps.setString(2, pack.getDescricao());
			ps.setString(3,pack.getDimensoes());
			ps.setString(4, pack.getValor());
			ps.setString(5, pack.getDispenser());
			ps.setBinaryStream(6, image);
		}else {
			ps = this.conn.prepareStatement("UPDATE pacotes SET txDescricao = ?, txDimensoes = ?, txValor = ?, txDispenser = ?, blobImage = ? WHERE txCodigo = ?");
			ps.setString(1, pack.getDescricao());
			ps.setString(2, pack.getDimensoes());
			ps.setString(3, pack.getValor());
			ps.setString(4, pack.getDispenser());
			ps.setBinaryStream(5, image);
			ps.setString(6, pack.getCodigo());
		}

		final boolean ret = ps.execute();

		ps.close();

		return ret;
	}

	@Override
	public boolean insertData(final Agencia data) throws SQLException {
		final Agencia d = this.getDataById(data.getCodigo());
		final PreparedStatement ps;
		if(d == null) {
			ps = this.conn.prepareStatement("INSERT INTO dados (txCodigo, txNomeAgencia, txCNPJAgencia, txMcuUnidade, txNomeFornecedor, txModeloAtm, txCartaoPostagem, txNumeroPlp, txRegistroObjeto, txAutorizacaoDoPagamento, txData, txHora, txCreditoDepito, txValorVenda) VALUES (?,?,?,?,?,?)");
			ps.setString(1, data.getCodigo());
			ps.setString(2, data.getNomeAgencia());
			ps.setString(3,data.getMcuUnidade());
			ps.setString(4, data.getNomeFornecedor());
			ps.setString(5, data.getModeloAtm());
			ps.setString(6, data.getCartaoPostagem());
			ps.setString(7, data.getNumeroPlp());
			ps.setString(8, data.getRegistroObjeto());
			ps.setString(9, data.getAutorizacaoPagamento());
			ps.setString(10, data.getData());
			ps.setString(11, data.getHora());
			ps.setString(12, data.getCreditoDebito());
			ps.setString(13, data.getValorVenda());
		}else {
			ps = this.conn.prepareStatement("UPDATE dados SET txNomeAgencia = ?, txCNPJAgencia = ?, txMcuUnidade = ?, txNomeFornecedor = ?, txModeloAtm = ?, txCartaoPostagem = ?, txNumeroPlp = ?, txRegistroObjeto = ?, txAutorizacaoDoPagamento = ?, txData = ?, txHora = ?, txCreditoDepito = ?, txValorVenda = ? WHERE txCodigo = ?");
			ps.setString(1, data.getCodigo());
			ps.setString(2, data.getNomeAgencia());
			ps.setString(3,data.getMcuUnidade());
			ps.setString(4, data.getNomeFornecedor());
			ps.setString(5, data.getModeloAtm());
			ps.setString(6, data.getCartaoPostagem());
			ps.setString(7, data.getNumeroPlp());
			ps.setString(8, data.getRegistroObjeto());
			ps.setString(9, data.getAutorizacaoPagamento());
			ps.setString(10, data.getData());
			ps.setString(11, data.getHora());
			ps.setString(12, data.getCreditoDebito());
			ps.setString(13, data.getValorVenda());
		}

		final boolean ret = ps.execute();

		ps.close();

		return ret;
	}
	
	@Override
	public List<Agencia> listDados() throws SQLException {
		final List<Agencia> ret = new ArrayList<Agencia>();

		final PreparedStatement ps = this.conn.prepareStatement("SELECT txCodigo, txNomeAgencia, txCNPJAgencia, txMcuUnidade, txNomeFornecedor, txModeloAtm, txCartaoPostagem, txNumeroPlp, txRegistroObjeto, txAutorizacaoDoPagamento, txData, txHora, txCreditoDepito, txValorVenda FROM dados");
		final ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			final Agencia data = new Agencia();
			data.setCodigo(rs.getString("txCodigo"));
			data.setNomeAgencia(rs.getString("txNomeAgencia"));
			data.setCnpjAgencia(rs.getString("txCnpjAgencia"));
			data.setMcuUnidade(rs.getString("txMcuUnidade"));
			data.setNomeFornecedor(rs.getString("txNomeFornecedor"));
			data.setModeloAtm(rs.getString("txModeloAtm"));
			data.setCartaoPostagem(rs.getString("txCartaoPostagem"));
			data.setNumeroPlp(rs.getString("txNumeroPlp"));
			data.setRegistroObjejeto(rs.getString("txRegistroObjeto"));
			data.setNomeAgencia(rs.getString("txNomeAgencia"));
			data.setAutorizacaoPagamento(rs.getString("txAutorizacaoPagamento"));
			data.setData(rs.getString("txData"));
			data.setHora(rs.getString("txHora"));
			data.setCreditoDebito(rs.getString("txCreditoDebito"));
			data.setValorVenda(rs.getString("txValorVenda"));
			ret.add(data);
		}

		ps.close();

		return ret;
	}

	@Override
	public List<Pack> listPacks() throws SQLException {
		final List<Pack> ret = new ArrayList<Pack>();

		final PreparedStatement ps = this.conn.prepareStatement("SELECT txCodigo, txDescricao, txDimensoes, txValor, txDispenser, blobImage FROM pacotes");
		final ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			final Pack pack = new Pack();
			pack.setCodigo(rs.getString("txCodigo"));
			pack.setDescricao(rs.getString("txDescricao"));
			pack.setDimensoes(rs.getString("txDimensoes"));
			pack.setValor(rs.getString("txValor"));
			pack.setDispenser(rs.getString("txDispenser"));
			pack.setImagem(new Image(rs.getBinaryStream("blobImage")));
			ret.add(pack);
		}

		ps.close();

		return ret;
	}


}
