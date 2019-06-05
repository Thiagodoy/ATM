package br.com.agencialove.tpa.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import br.com.agencialove.tpa.workflow.Session;
import javafx.application.Platform;

public class FinishController implements IController {

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {

	}

	@Override
	public void clear() {
	}

	public void finish() {
		final TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(()->{
					Session.reset();
				});
			};
		};

		final Timer timer = new Timer();
		timer.schedule(task, 4000);
	}
}
