package br.com.agencialove.tpa.hardware;

import com.qoppa.pdf.PDFException;

import br.com.agencialove.tpa.hardware.arduino.Debug;
import br.com.agencialove.tpa.hardware.arduino.Error;
import br.com.agencialove.tpa.hardware.arduino.Warning;
import br.com.agencialove.tpa.hardware.arduino.Working;

public interface IHardwareCallback {
	void callbackWorking(Working working);
	void callbackWarning(Warning warning);
	void callbackErro(Error error);
	void callbackException(Exception e);
	void callbackDebug(Debug debug);
}
