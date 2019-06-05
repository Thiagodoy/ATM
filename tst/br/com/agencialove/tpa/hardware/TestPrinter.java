package br.com.agencialove.tpa.hardware;

import java.io.IOException;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfPrint.PDFPrint;

import br.com.agencialove.tpa.Configuration;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;


public class TestPrinter {

	public static void main (final String[] args) throws PrintException, IOException, InterruptedException, NoSuchPortException, PortInUseException, InstantiationException, IllegalAccessException, ClassNotFoundException, PDFException {

		//((PrePostResponse)Session.getSession().get(Session.PRE_POST));
		//print.printLabel(pdf, callback);

		//		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0:/dev/ttyUSB0");
		//		System.setProperty("gnu.io.rxtx.ParallelPorts", "/dev/usb/lp0");

		final PDFPrint pdfPrint = new PDFPrint ("xxx.pdf", null);

		final PrintRequestAttributeSet attrSet = new HashPrintRequestAttributeSet();
		//attrSet.add(OrientationRequested.PORTRAIT);
		//attrSet.add(new MediaPrintableArea(10, 0, 80, 110, MediaPrintableArea.INCH));
		attrSet.add(MediaSizeName.ISO_A7);

		//FileInputStream fis = new FileInputStream("xxx.pdf");

		PrintService printService = PrinterServiceImpl.findPrintService(Configuration.getString("printer.ticket"));
		if (printService == null) {
			printService = PrintServiceLookup.lookupDefaultPrintService();
		}
		final DocPrintJob job = printService.createPrintJob();

		//Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
		final SimpleDoc doc = new SimpleDoc (pdfPrint, DocFlavor.SERVICE_FORMATTED.PRINTABLE, null);
		new HashPrintRequestAttributeSet();

		job.print(doc,attrSet);

		//		final Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
		//		while(ports.hasMoreElements()) {
		//			System.out.println(ports.nextElement().getName());
		//		}
		//
		//		final CommPortIdentifier commPortId = CommPortIdentifier.getPortIdentifier("/dev/usb/lp0");
		//		final CommPort port = commPortId.open("TestPrinter", 50);
		//		final OutputStream os = port.getOutputStream();
		//		os.write(new byte[] {0x1E});
		//		os.flush();
		//		os.close();

		// open the parallel port -- open(App name, timeout)
		//		final ParallelPort parallelPort = (ParallelPort) CommPortIdentifier.getPortIdentifier("/dev/ttyUSB0").open("CommTest", 50);
		//		final OutputStream outputStream = parallelPort.getOutputStream();
		//
		//		System.out.println("Write...");
		//		outputStream.write(new byte[] {0x1B, 0x69});
		//		System.out.println("Flush...");
		//		outputStream.flush();
		//		System.out.println("Close...");
		//		outputStream.close();
		//
		//		parallelPort.close();


		//		final PrintService myService = PrintServiceLookup.lookupPrintServices(null, null)[0];
		//
		//		final FileInputStream fis = new FileInputStream("./res/example2.txt");
		//
		//		final DocPrintJob job = myService.createPrintJob();
		//		final PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		//		aset.add(Fidelity.FIDELITY_FALSE);
		//		aset.add(MediaSizeName.ISO_A4);
		//
		//		job.addPrintJobListener(new JobListener(myService));
		//
		//		final Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
		//		job.print(doc,aset);
		//		fis.close();
		//
		//		Thread.sleep(3000);
		//
		//		final byte[] bytes = { 0x1B, 0x69}; //Code for cutting
		//		final ParallelPort ticketPort =

	}

	/*System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0:/dev/ttyACM1");
	System.setProperty("gnu.io.rxtx.ParallelPorts", "/dev/usb/lp0");

	final Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();

	while(ports.hasMoreElements()) {
		System.out.println(ports.nextElement().getName());
	}

	final CommPortIdentifier port= CommPortIdentifier.getPortIdentifier("/dev/usb/lp0");

	System.out.println("\nport.portType = " + port.getPortType());
	System.out.println("port.type = " + TestPrinter.PORT_TYPE[port.getPortType() - 1]);
	System.out.println("port.name = " + port.getName());

	// open the parallel port -- open(App name, timeout)
	final ParallelPort parallelPort = (ParallelPort) port.open("CommTest", 50);
	final OutputStream outputStream = parallelPort.getOutputStream();

	System.out.println("Write...");
	outputStream.write(new byte[] {0x1B, 0x69});
	System.out.println("Flush...");
	outputStream.flush();
	System.out.println("Close...");
	outputStream.close();

	parallelPort.close();*/
}
