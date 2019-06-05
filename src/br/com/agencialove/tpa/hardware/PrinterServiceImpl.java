package br.com.agencialove.tpa.hardware;

import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Fidelity;
import javax.print.attribute.standard.MediaSizeName;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfPrint.PDFPrint;

import br.com.agencialove.tpa.Configuration;
import br.com.agencialove.tpa.hardware.printer.PrinterCallback;
import gnu.io.PortInUseException;

public class PrinterServiceImpl implements IPrinterService {

	/*
	private static final byte[] TICKET_CUT_COMMAND = new byte[] {0x1D, 0x56, 0x01};
	private static final byte[] LABEL_CUT_COMMAND = new byte[] {0x1E};
	private static CommPortIdentifier ticketPrinterPort = null;
	private static CommPortIdentifier labelPrinterPort = null;

	 * public PrinterServiceImpl() {
		PrinterServiceImpl.ticketPrinterPort = Configuration.getTricketPrinterPort();
		PrinterServiceImpl.labelPrinterPort = Configuration.getLabelPrinterPort();
	}*/

	@Override
	public void printTicket(final String txt) throws PrintException {
		//final PrintService printService = PrinterServiceImpl.findPrintService(Configuration.getString("printer.ticket"));
		final PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
		printService.createPrintJob();
		final PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(Fidelity.FIDELITY_FALSE);
		aset.add(MediaSizeName.ISO_A4); //TODO ajustar o tamanho da página de impressão

		//final Doc doc = new SimpleDoc(txt.getBytes(), DocFlavor.BYTE_ARRAY.TEXT_PLAIN_US_ASCII, null);
		//job.print(doc,aset);
		try {
			this.cutTicket();
		} catch (final PortInUseException | IOException e) {
			//TODO tratar erro de porta em uso
		}
	}


	@Override
	public void printTicket(final byte[] pdf) throws PrintException {

		try {
			final ByteArrayInputStream bias = new ByteArrayInputStream(pdf);
			PDFPrint pdfPrint;
			pdfPrint = new PDFPrint (bias,null);
			final PrintRequestAttributeSet attrSet = new HashPrintRequestAttributeSet();
			//attrSet.add(OrientationRequested.PORTRAIT);
			//attrSet.add(new MediaPrintableArea(10, 0, 80, 110, MediaPrintableArea.INCH));
			attrSet.add(MediaSizeName.ISO_A7);

			PrintService printService = PrinterServiceImpl.findPrintService(Configuration.getString("printer.ticket"));
			if (printService == null) {
				printService = PrintServiceLookup.lookupDefaultPrintService();
			}
			final DocPrintJob job = printService.createPrintJob();
			final SimpleDoc doc = new SimpleDoc (pdfPrint, DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
			job.print(doc, attrSet);
			this.cutLabel();
		} catch (final PrintException | PortInUseException | IOException | PDFException e) {
			//callback.afterPrint(e);
			e.printStackTrace();
			//TODO tratar essas exce��es
		}

	}

	private void cutTicket() throws PortInUseException, IOException {
		//		final CommPort port = PrinterServiceImpl.ticketPrinterPort.open("",500);
		//		final OutputStream os = port.getOutputStream();
		//		os.write(PrinterServiceImpl.TICKET_CUT_COMMAND);
	}


	@Override
	public void printLabel(final byte[] pdf) throws PrintException {

		try {
			final ByteArrayInputStream bias = new ByteArrayInputStream(pdf);
			final PDFPrint pdfPrint = new PDFPrint (bias,null);
			final PrintRequestAttributeSet attrSet = new HashPrintRequestAttributeSet();
			//attrSet.add(OrientationRequested.PORTRAIT);
			//attrSet.add(new MediaPrintableArea(10, 0, 80, 110, MediaPrintableArea.INCH));
			attrSet.add(MediaSizeName.ISO_A7);

			PrintService printService = PrinterServiceImpl.findPrintService(Configuration.getString("printer.label"));
			if (printService == null) {
				printService = PrintServiceLookup.lookupDefaultPrintService();
			}
			final DocPrintJob job = printService.createPrintJob();
			final SimpleDoc doc = new SimpleDoc (pdfPrint, DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
			job.print(doc, attrSet);
			this.cutLabel();
		} catch (final PrintException | PortInUseException | IOException | PDFException e) {
			//callback.afterPrint(e);
			e.printStackTrace();
			//TODO tratar essa exce��o
		}


	}


	private void cutLabel() throws PortInUseException, IOException {
		//		final CommPort port = PrinterServiceImpl.labelPrinterPort.open("",500);
		//		final OutputStream os = port.getOutputStream();
		//		os.write(PrinterServiceImpl.LABEL_CUT_COMMAND);
	}
	public static PrintService findPrintService(String printerName) {

		printerName = printerName.toLowerCase();

		PrintService service = null;

		// Get array of all print services
		final PrintService[] services = PrinterJob.lookupPrintServices();

		// Retrieve a print service from the array
		for (int index = 0; service == null && index < services.length; index++) {

			if (services[index].getName().toLowerCase().indexOf(printerName) >= 0) {
				service = services[index];
			}
		}

		// Return the print service
		return service;
	}


	@Override
	public void printLabel(final byte[] pdf, final PrinterCallback callback) throws PrintException {
		// TODO Auto-generated method stub

	}

}