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
	
	public static String getReciboPostagem(Map<String,String>parameter) {
		
		String  recibo = ""+
				"------------------------------------------------------\r\n" + 
				"                    CORREIOS                          \r\n" + 
				"Ag. São Bernardo do Campo             - São Paulo - SP\r\n" + 
				"CNPJ: 34028316312423 Ins. Est.: 00901190              \r\n" + 
				"------------------------------------------------------\r\n" + 
				"        COMPROVANTE DO CLIENTE ATM                    \r\n" + 
				"Movimento: [@data@data]             Hora: [@hora@]    \r\n" + 
				"Modalidade: Á Vista                   ATM : 0074290380\r\n" + 
				"------------------------------------------------------\r\n" + 
				"DESCRIÇÃO                                   PREÇO (R$)\r\n" + 
				"[@SE]                                                 \r\n" + 
				"Aviso Recibimento Eletrónico                   [@AR]  \r\n" + 
				"Valor Declarado                                [@VD]  \r\n" + 
				"Mão própria                                    [@MP]  \r\n" + 
				"------------------------------------------------------\r\n" + 
				"TOTAL DO ATENDIMENTO (R$)                      [@TA]  \r\n" + 
				"                                                      \r\n" + 
				"CEP de destino                      [@CEP00000000]    \r\n" + 
				"Dimensões (cm)                    [@DIME]             \r\n" + 
				"Peso (Kg)                         [@PE]               \r\n" + 
				"Registro                         [@ETI]               \r\n" + 
				"Prazo Estimado de Entrega        [@DIA] dias uteis";	
		
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
