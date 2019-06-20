package br.com.agencialove.tpa.utils;

import java.util.Map;

public class Recibo {
	
	public static String getReciboEmbalagem(Map<String,String>parameter) {
		
		String  recibo = ""+
				"------------------------------------------------------\r\n" + 
				"                    CORREIOS                          \r\n" + 
				"Ag. São Bernardo do Campo             - São Paulo - SP\r\n" + 
				"CNPJ: 34028316312423 Ins. Est.: 00901190              \r\n" + 
				"------------------------------------------------------\r\n" + 
				"                  COMPROVANTE                         \r\n" + 
				"Movimento: [@data]             [@hora]                \r\n" + 
				"Modalidade: À Vista                                   \r\n" + 
				"------------------------------------------------------\r\n" + 
				"DESCRIÇÃO                QTD.    Preço Un. Total(R$) \r\n" + 
				"[@DESC]                  [@QTD]  [@PRECO]  [@TOTAL]  \r\n" + 
				"------------------------------------------------------\r\n" + 
				"                     TOTAL DA VENDA (R$) =  [@TOTALV]\r\n" + 
				"------------------------------------------------------\r\n" + 
				"   SERV. POSTAIS: DIREITOS E DEVERES - LEI 6538/78    \r\n" + 
				"                                                      \r\n" + 
				" No caso de dúvidas/reclamações/elogios, entre em     \r\n" + 
				" contato com a Central de Atendimento dos Correios    \r\n" + 
				"        (CAC):3003 0100 ou 0800 725 7282.             \r\n" ;		
		
		    StringBuilder st = new StringBuilder(recibo);
		
		    parameter.forEach((key,valor)->{				
				int index = st.indexOf(key);
				int leng = key.length() > valor.length() ? key.length() : valor.length();
				
				
				
				st.delete(index, index + leng);
				st.insert(index, valor.toCharArray());
				
			});		
		
		return st.toString();
		
	}
	

}
