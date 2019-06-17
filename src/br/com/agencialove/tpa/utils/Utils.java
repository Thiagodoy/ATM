package br.com.agencialove.tpa.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {	
	
	public static String format(Double value) {
		Locale ptBr = new Locale("pt", "BR");
		String valorString = NumberFormat.getCurrencyInstance(ptBr).format(value);
		return valorString;
	}
	
	public static Double parse(String value) {
		return Double.valueOf(value.replace("R$", "").replace(",", ".").trim());
	}
	
	

}
