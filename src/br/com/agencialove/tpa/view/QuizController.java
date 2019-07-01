package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.dao.PesquisaSatisfacaoDao;
import br.com.agencialove.tpa.model.PesquisaSatisfacao;
import br.com.agencialove.tpa.workflow.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

public class QuizController implements IController{
	
	@FXML
	private RadioButton questaoUmFacil;
	@FXML
	private RadioButton  questaoUmMedio;
	@FXML
	private RadioButton questaoUmDificil;
	@FXML
	private RadioButton questaoUmNaoSeiAvaliar;	
	
	
	@FXML
	private RadioButton questaoDoisSim;
	@FXML
	private RadioButton questaoDoisNao;
	@FXML
	private RadioButton questaoDoisIqual;
	@FXML
	private RadioButton questaoDoisNaoSeiAvaliar;
	
	@FXML
	private RadioButton questaoTresSim;
	@FXML
	private RadioButton questaoTresNao;
	
	@FXML
	private RadioButton questaoQuatroOtimo;
	@FXML
	private RadioButton questaoQuatroRegular;
	@FXML
	private RadioButton questaoQuatroPessimo;
	@FXML
	private RadioButton questaoQuatroNaoSeiAvaliar;
	
	@FXML
	private TextArea txtArea;
	
	@FXML
	private Label lblQtd;
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private Button btnNext;
	
	@FXML
	private StackPane stake;
	
	private ToggleGroup group1;
	private ToggleGroup group2;
	private ToggleGroup group3;
	private ToggleGroup group4;
	
	
	private PesquisaSatisfacao satisfacao;
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		this.btnNext.setDisable(true);
		
		
		satisfacao = new PesquisaSatisfacao();
		
		group1 = new ToggleGroup();
		questaoUmFacil.setUserData("Fácil");
		questaoUmMedio.setUserData("Médio");
		questaoUmDificil.setUserData("Dificil");
		questaoUmNaoSeiAvaliar.setUserData("Não Sei Avaliar");
		
		questaoUmFacil.setToggleGroup(group1);		
		questaoUmMedio.setToggleGroup(group1);		
		questaoUmDificil.setToggleGroup(group1);		
		questaoUmNaoSeiAvaliar.setToggleGroup(group1);
		
		group1.selectedToggleProperty().addListener((ov,oldOption,newOption)->{		
			validar();
			satisfacao.setQuestaoUm(group1.getSelectedToggle().getUserData().toString());
		});
		
		
		group2 = new ToggleGroup();
		questaoDoisSim.setUserData("Sim");
		questaoDoisNao.setUserData("Não");
		questaoDoisIqual.setUserData("Iqual");
		questaoDoisNaoSeiAvaliar.setUserData("Não Sei Avaliar");
		
		questaoDoisSim.setToggleGroup(group2);		
		questaoDoisNao.setToggleGroup(group2);		
		questaoDoisIqual.setToggleGroup(group2);		
		questaoDoisNaoSeiAvaliar.setToggleGroup(group2);
		
		group2.selectedToggleProperty().addListener((ov,oldOption,newOption)->{	
			validar();
			satisfacao.setQuestaoDois(group1.getSelectedToggle().getUserData().toString());
		});
		
		
		
		group3 = new ToggleGroup();
		questaoTresSim.setUserData("Sim");
		questaoTresNao.setUserData("Não");
		
		
		questaoTresSim.setToggleGroup(group3);		
		questaoTresNao.setToggleGroup(group3);				
		group3.selectedToggleProperty().addListener((ov,oldOption,newOption)->{	
			validar();
			satisfacao.setQuestaoTres(group1.getSelectedToggle().getUserData().toString());
		});		
		
		group4 = new ToggleGroup();
		questaoQuatroOtimo.setUserData("Sim");
		questaoQuatroRegular.setUserData("Não");
		questaoQuatroPessimo.setUserData("Iqual");
		questaoQuatroNaoSeiAvaliar.setUserData("Não Sei Avaliar");
		
		questaoQuatroOtimo.setToggleGroup(group4);		
		questaoQuatroRegular.setToggleGroup(group4);		
		questaoQuatroPessimo.setToggleGroup(group4);		
		questaoQuatroNaoSeiAvaliar.setToggleGroup(group4);
		
		group4.selectedToggleProperty().addListener((ov,oldOption,newOption)->{			
			validar();
			satisfacao.setQuestaoQuatro(group1.getSelectedToggle().getUserData().toString());
		});
		
		txtArea.setPrefColumnCount(100);
		txtArea.setPrefRowCount(10);
		txtArea.textProperty().addListener((event)->{
			satisfacao.setQuestaoCinco(txtArea.getText());
			
			String count  = txtArea.getText().length() == 0 ? "Faltam 1000 de 1000" : "Faltam " + ( 1000 - txtArea.getText().length()) + " de 1000"; 
			
			lblQtd.setText(count);
		});	
		
	}
	
	public boolean validar() {
	
		
		StringBuilder m = new StringBuilder();
		
		if(group1.getSelectedToggle() == null) {
			m.append("Questionário 1 não foi respondido!");
			return false;
		}
		
		
		
		if(group2.getSelectedToggle() == null) {
			m.append("Questionário 2 não foi respondido!");
			return false;
		}
		
		
		if(group3.getSelectedToggle() == null ) {
			m.append("Questionário 3 não foi respondido!");
			return false;
		}
			
		
		
		if(group4.getSelectedToggle() == null ) {
			m.append("Questionário 4 não foi respondido!");
			return false;	
		}	
		
		
		btnNext.setDisable(false);
		
		return true;
		
	}
	
	
	
	@FXML
	public void  btnNextAction() {		
		
		PesquisaSatisfacaoDao.save(satisfacao);		
		Session.setScene(Windows.FINISH.getScene());	
		
		FinishController control = (FinishController) Windows.FINISH.getScene().getUserData();
		control.finish();
		
	}	

	@Override
	public void clear() {
		group1.setUserData(null);
		group2.setUserData(null);
		group3.setUserData(null);
		group4.setUserData(null);
		txtArea.setText("");
		satisfacao = new PesquisaSatisfacao();
		
	}
	
	@FXML
	@Override
	public void cancel() {
		Session.reset();		
	}

}
