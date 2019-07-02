package br.com.agencialove.tpa.hardware;

import javax.print.PrintException;

import com.qoppa.pdf.PDFException;

import br.com.agencialove.tpa.hardware.printer.PrinterCallback;

public interface IPrinterService {

	void printTicket(String pdf) throws PrintException;

	void printTicket(byte[] pdf) throws PrintException;

	void printLabel(byte[] pdf) throws PrintException;

	void printLabel(byte[] pdf, PrinterCallback callback) throws PrintException;
	void printPdf(final byte[] pdf, String fileName);


}
