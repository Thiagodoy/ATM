package br.com.agencialove.tpa;

public class StarterSimulatorsAgent {
	public static void main(final String[] args) throws InterruptedException {
		new Thread(()->{
			try {
				TEFSimulatorAgent.main(args);
			} catch (final Exception e) {
				e.printStackTrace();
			}}).start();

		new Thread(()->{
			try {
				ScaleSimulatorAgent.main(args);
			} catch (final Exception e) {
				e.printStackTrace();
			}}).start();

		new Thread(()->{
			try {
				ArduinoSimulatorAgent.main(args);
			} catch (final Exception e) {
				e.printStackTrace();
			}}).start();

		while(true)
			Thread.sleep(500);
	}
}
