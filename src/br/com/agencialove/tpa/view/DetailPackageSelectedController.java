package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.model.Pack;
import br.com.agencialove.tpa.utils.Utils;
import br.com.agencialove.tpa.workflow.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DetailPackageSelectedController implements IController {

	private ObservableList<PackageInfo> packages = FXCollections.observableArrayList();

	private Scene previousScene;

	@FXML
	private TableView<PackageInfo> tableView;

	@FXML
	private TableColumn<PackageInfo, String> colProduto;

	@FXML
	private TableColumn<PackageInfo, String> colQuantidade;

	@FXML
	private TableColumn<PackageInfo, String> colValor;

	@FXML
	public void btnBackAction(final ActionEvent e) {
		SelectPackageController control = (SelectPackageController) previousScene.getUserData();
		control.loadInfo();
		Session.setScene(previousScene);
	}

	@FXML
	public void btnNextAction(final ActionEvent e) {
		Scene nextScene = Windows.BUYER_INFORMATION.getScene();
		Session.setScene(nextScene);
	}

	@FXML
	public void btnCancelAction(final ActionEvent e) {
		// Session.reset();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colProduto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		this.tableView.setItems(packages);

	}

	@Override
	public void clear() {
	}

	public Scene getPreviousScene() {
		return this.previousScene;
	}

	public void setPreviousScene(final Scene previousScene) {
		this.previousScene = previousScene;
	}

	public void populateInfo() {

		Pack pack = (Pack) Session.getSession().get(Session.SELECTED_PACKAGE);

		this.packages.add(new PackageInfo(pack));
		PackageInfo infoTotal = new PackageInfo();
		infoTotal.setCodigo("");
		infoTotal.setQuantidade("");

		double valor = Double.valueOf(pack.getValor().replace(",", ".").replace("R$", ""));
		Double total = (valor * pack.getQuantidade());
		infoTotal.setValor(Utils.format(total));

		this.packages.add(infoTotal);

	}

	public class PackageInfo {

		private String codigo;
		private String quantidade;
		private String valor;

		public PackageInfo() {
		}

		public PackageInfo(Pack pack) {
			this.codigo = pack.getCodigo();
			this.quantidade = String.valueOf(pack.getQuantidade());
			this.valor = pack.getValor();
		}

		@SuppressWarnings("unused")
		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		@SuppressWarnings("unused")
		public String getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(String quantidade) {
			this.quantidade = quantidade;
		}

		@SuppressWarnings("unused")
		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}

	}

}
