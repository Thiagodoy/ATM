package br.com.agencialove.tpa.hardware;

import br.com.agencialove.tpa.model.PaymentData;
import br.com.agencialove.tpa.model.PaymentResult;

public interface ITEFService {

	void charge(String value, String code, String description, TEFCallback callback);

	public interface TEFCallback {
		void charged(PaymentResult result, PaymentData paymentData, String toPrint);

		void error(Exception e);

		void timeout();
	}
}
