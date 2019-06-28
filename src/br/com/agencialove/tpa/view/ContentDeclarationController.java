package br.com.agencialove.tpa.view;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencia.tpa.rest.request.DeclaracaoConteudoRequest;
import br.com.agencia.tpa.rest.request.ItemDeclaracaoConteudoRequest;
import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.Validator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javassist.compiler.ast.CondExpr;

public class ContentDeclarationController implements IController {

	private static long count = 0;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnAdd;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtDescription;

	@FXML
	private TextField txtQuantity;

	@FXML
	private TextField txtValue;
	
	@FXML
	private Label labelErro;

	@SuppressWarnings("rawtypes")
	@FXML
	private TableView tableView;

	private Scene backScene;

	@SuppressWarnings("unchecked")
	@FXML
	private void btnNextAction(final ActionEvent e) {
		
		
		
		DeclaracaoConteudoRequest conteudoRequest = new DeclaracaoConteudoRequest();
		double total = 0.0;
		
		ObservableList<Item> items = this.tableView.getItems();	
		
		 total = items.stream().mapToDouble(i->{
			return Double.parseDouble(i.getValue());
		}).sum();
		
		
		conteudoRequest.setTotal(total);		
		Session.getSession().put(Session.CONTEUDO_DECLARADO, conteudoRequest);
		

		if (this.backScene.equals(Windows.ADDITIONAL_SERVICES1.getScene())) {
			Scene scene = Windows.MEASURES.getScene();
			final MeasuresController controller = (MeasuresController) scene.getUserData();
			controller.setPreviousScene(Windows.ADDITIONAL_SERVICES1.getScene());
			controller.clear();
			Session.setScene(scene);
		} else {

			final Scene scene = Windows.CHECKOUT.getScene();
			final CheckoutController controller = (CheckoutController) scene.getUserData();
			controller.clear();
			controller.loadInfo();
			Session.setScene(scene);
		}
	}

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.NFE_CHOOSE.getScene();
		Session.setScene(scene);
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void btnAddAction(final ActionEvent e) {
		final Validator validator = new Validator();
		this.labelErro.setVisible(false);	

		// validator.validateIntegerNotEmpty(this.txtId, true);
		validator.validateStringNotEmpty(this.txtDescription, true, 3, 100);
		validator.validateIntegerNotEmpty(this.txtQuantity, true);		
		
		String message = validator.validateValue(this.txtValue, true, 20.00, 1000.0);
		
		

		if (validator.isEmpty())
			this.btnAdd.setDisable(true);
		else
			return;

		final Button b = new Button("X");
		b.getStyleClass().add("bt-excluir-linha");

		long quantidade = Long.parseLong(this.txtQuantity.getText());
		double valor = Double.parseDouble(this.txtValue.getText());

		double total = quantidade * valor;

		final Item item = new Item(String.valueOf(++count), this.txtDescription.getText(), this.txtQuantity.getText(),
				String.valueOf(total), b);

		final ObservableList<Item> items = this.tableView.getItems();
		items.add(item);

		b.setOnAction((event) -> {
			--count;
			items.remove(item);

			for (int i = 0; i < items.size(); ++i)
				items.get(i).setId(Integer.toString(i + 1));
			this.txtId.setText(Integer.toString(items.size() + 1));
			this.txtDescription.requestFocus();
			if (items.size() == 0)
				this.btnNext.setDisable(true);
		});

		this.txtId.setText(Integer.toString(items.size() + 1));
		this.txtDescription.setText("");
		this.txtQuantity.setText("");
		this.txtValue.setText("");

		this.txtDescription.requestFocus();

		if (items.size() > 0)
			this.btnNext.setDisable(false);
	}

	@FXML
	private void validateFields(final KeyEvent e) {
		final Validator validator = new Validator();

		// validator.validateIntegerNotEmpty(this.txtId, true);
		validator.validateStringNotEmpty(this.txtDescription, true, 3, 100);
		validator.validateIntegerNotEmpty(this.txtQuantity, true);
		validator.validatePriceNotEmpty(this.txtValue, true, 6, 2);

		if (validator.isEmpty())
			this.btnAdd.setDisable(false);
		else
			this.btnAdd.setDisable(true);
	}

	@Override
	public void initialize(final URL arg0, final ResourceBundle arg1) {
	}

	@Override
	public void clear() {
		this.tableView.getItems().clear();
		this.txtId.setText("1");
		this.txtDescription.setText("");
		this.txtQuantity.setText("");
		this.txtValue.setText("");
		count = 0;
		this.btnNext.setDisable(true);
	}
	
	public void setBackScene(Scene scene) {
		this.backScene = scene;
	}

	public class Item {
		public Item(final String pId, final String pDescription, final String pQuantity, final String pValue,
				final Button pDelete) {
			this.id = pId;
			this.description = pDescription;
			this.quantity = pQuantity;
			this.value = pValue;
			this.delete = pDelete;
		}

		private String id;
		private String description;
		private String quantity;
		private String value;
		private Button delete;

		public String getId() {
			return this.id;
		}

		public void setId(final String id) {
			this.id = id;
		}

		public String getDescription() {
			return this.description;
		}

		public void setDescription(final String description) {
			this.description = description;
		}

		public String getQuantity() {
			return this.quantity;
		}

		public void setQuantity(final String quantity) {
			this.quantity = quantity;
		}

		public String getValue() {
			return this.value;
		}

		public void setValue(final String value) {
			this.value = value;
		}

		public Button getDelete() {
			return this.delete;
		}

		public void setDelete(final Button delete) {
			this.delete = delete;
		}
	}
}