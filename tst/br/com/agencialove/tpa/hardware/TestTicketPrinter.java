package br.com.agencialove.tpa.hardware;

import javax.print.PrintException;

import br.com.agencialove.tpa.workflow.Session;

public class TestTicketPrinter {
	public static void main (final String[] args) throws PrintException {
		final IPrinterService printerService = Session.getPrinterService();

		printerService.printTicket("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ut tristique risus, quis commodo sem. Praesent tincidunt quam eget dui viverra tempor a ac ex. Aliquam odio lorem, imperdiet ac condimentum a, auctor sed nibh. Donec at tincidunt ante. Aliquam dolor velit, imperdiet sed egestas vel, volutpat nec ipsum. Suspendisse et sodales arcu. Praesent eleifend turpis id maximus condimentum. In imperdiet at ex vel commodo. Sed faucibus dui ipsum, sit amet molestie leo elementum dictum. Curabitur interdum suscipit odio eu consectetur.");

		System.exit(0);
	}

}
