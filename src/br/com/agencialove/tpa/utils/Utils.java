package br.com.agencialove.tpa.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.agencia.tpa.rest.request.PrecoPrazoRequest;
import br.com.agencia.tpa.rest.request.ServicoAdicionalRequest;
import br.com.agencia.tpa.rest.response.PrecoPrazoResponse;
import javafx.scene.layout.StackPane;

public class Utils {

	public static String formatCurrency(Double value) {
		Locale ptBr = new Locale("pt", "BR");
		String valorString = NumberFormat.getCurrencyInstance(ptBr).format(value);
		return valorString;
	}

	public static String format(Double value) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
		decimalFormatSymbols.setCurrencySymbol("");
		((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
		return nf.format(value).trim();
	}

	public static Double parse(String value) {
		return Double.valueOf(value.replace("R$", "").replace(",", ".").trim());
	}

	public static ServicoAdicionalRequest getServicos(PrecoPrazoRequest precoPrazoRequest) {

		ServicoAdicionalRequest servicosAdicionais = new ServicoAdicionalRequest();

		if (precoPrazoRequest.getMaoPropria().contentEquals("S")) {
			servicosAdicionais.getCodigos().add("045");
		}
		if (precoPrazoRequest.getAvisoRecebimento().contentEquals("S")) {
			servicosAdicionais.getCodigos().add("046");
		}
		if (precoPrazoRequest.getValorDeclarado() != null && precoPrazoRequest.getValorDeclarado().length() > 0) {
			servicosAdicionais.setValor(Utils.parse(precoPrazoRequest.getValorDeclarado()));
			servicosAdicionais.getCodigos().add("064");
		}

		return servicosAdicionais;
	}
	
	
	
	

}
