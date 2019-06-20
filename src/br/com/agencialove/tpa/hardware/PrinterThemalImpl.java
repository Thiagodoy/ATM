package br.com.agencialove.tpa.hardware;

import javafx.concurrent.Task;

public class PrinterThemalImpl  implements IPrinterThermal {

	@Override
	public void print(String value) {
		
		new Thread(new Task<Void>() {			
		
			
			@Override
			protected Void call() throws Exception {
				
				
				Thread.sleep(2000);
				
				return null;
			}
		}).start();
		
		
		
		
	}

}
