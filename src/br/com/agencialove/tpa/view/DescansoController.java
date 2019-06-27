package br.com.agencialove.tpa.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.agencialove.tpa.workflow.Session;
import br.com.agencialove.tpa.workflow.SessionType;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class DescansoController implements IController {

	
	@FXML
	private MediaView mediaView;
	
	private MediaPlayer mp;
	private Media me;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
       String path = new File("src/br/com/agencialove/tpa/media/descanso-atm.mp4").getAbsolutePath();
       me = new Media(new File(path).toURI().toString());
       mp = new MediaPlayer(me);
       mediaView.setMediaPlayer(mp);
       
       mp.setCycleCount(MediaPlayer.INDEFINITE);
       
       mediaView.setOnTouchPressed((event)->this.startWrokFlow());
       mediaView.setOnMouseClicked((event)->this.startWrokFlow());
		
	}
	
	private void startWrokFlow() {
		Session.setScene(Windows.START.getScene());		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub		
	}
	
	
	public void play() {
		mp.setAutoPlay(true);
	}
	
	private void stop() {
		mp.stop();
	}
	

}
