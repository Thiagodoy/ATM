package br.com.agencialove.tpa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.agencialove.tpa.utils.Recibo;

public class TestReciboEmbalagem {

	public static void main(String[] args) throws IOException {

		
		Map<String,String>parameter = new HashMap();
		parameter.put("[@data]", "12/12/2010");
		parameter.put("[@hora]", "12:12:34");
		parameter.put("[@DESC]", "Caixa	27x22,5x13,5".trim());
		parameter.put(" [@QTD]", "1       ");
		parameter.put("[@PRECO]", "R$ 30,00");
		parameter.put("[@TOTAL]", "R$ 30,00");
		parameter.put("[@TOTALV]", "R$ 30,00");
		
		
		String recibo = Recibo.getReciboEmbalagem(parameter);
		
		FileWriter writer = new FileWriter(new File("reciboEmbalagem.txt"));
		
		writer.write(recibo);
		writer.flush();
		writer.close();
		
		

	}

}
