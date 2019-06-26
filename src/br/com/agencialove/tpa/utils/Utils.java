package br.com.agencialove.tpa.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

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
		return nf.format(value);		
	}
	
	public static Double parse(String value) {
		return Double.valueOf(value.replace("R$", "").replace(",", ".").trim());
	}
	
	

}
