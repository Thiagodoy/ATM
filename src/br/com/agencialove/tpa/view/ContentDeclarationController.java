package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.Validator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ContentDeclarationController implements IController {

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
	private TableView tableView;

	@FXML
	private void btnNextAction(final ActionEvent e) {
		final Scene scene = Windows.CHECKOUT.getScene();
		final CheckoutController controller = (CheckoutController) scene.getUserData();
		controller.clear();
		controller.loadInfo();
		Session.setScene(scene);
	}

	@FXML
	private void btnBackAction(final ActionEvent e) {
		final Scene scene = Windows.NFE_CHOOSE.getScene();
		Session.setScene(scene);
	}

	@FXML
	private void btnAddAction(final ActionEvent e) {
		final Validator validator = new Validator();

		validator.validateIntegerNotEmpty(this.txtId, true);
		validator.validateStringNotEmpty(this.txtDescription, true, 3, 100);
		validator.validateIntegerNotEmpty(this.txtQuantity, true);
		validator.validatePriceNotEmpty(this.txtValue, true, 6, 2);

		if(validator.isEmpty())
			this.btnAdd.setDisable(true);
		else
			return;

		final Button b = new Button("X");
		final Item item = new Item(this.txtId.getText(), this.txtDescription.getText(), this.txtQuantity.getText(), this.txtValue.getText(), b);

		final ObservableList<Item> items = this.tableView.getItems();
		items.add(item);

		b.setOnAction((event)->{
			items.remove(item);
			for(int i = 0; i < items.size(); ++i)
				items.get(i).setId(Integer.toString(i+1));
			this.txtId.setText(Integer.toString(items.size()+1));
			this.txtDescription.requestFocus();
			if(items.size() == 0)
				this.btnNext.setDisable(true);
		});

		this.txtId.setText(Integer.toString(items.size()+1));
		this.txtDescription.setText("");
		this.txtQuantity.setText("");
		this.txtValue.setText("");

		this.txtDescription.requestFocus();

		if(items.size() > 0)
			this.btnNext.setDisable(false);
	}

	@FXML
	private void validateFields(final KeyEvent e) {
		final Validator validator = new Validator();

		validator.validateIntegerNotEmpty(this.txtId, true);
		validator.validateStringNotEmpty(this.txtDescription, true, 3, 100);
		validator.validateIntegerNotEmpty(this.txtQuantity, true);
		validator.validatePriceNotEmpty(this.txtValue, true, 6, 2);

		if(validator.isEmpty())
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

		this.btnNext.setDisable(true);
	}

	public class Item {
		public Item(final String pId, final String pDescription, final String pQuantity, final String pValue, final Button pDelete) {
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