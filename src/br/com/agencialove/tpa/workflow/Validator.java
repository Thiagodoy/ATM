package br.com.agencialove.tpa.workflow;

import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.Node;
import javafx.scene.control.TextField;



public class Validator {

	public static String REGEX_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	public static String REGEX_PRICE = "[0-9]+([,.][0-9]{1,2})?";
	public static String REGEX_CELULLAR = "^\\([0-9]{2}\\)[9][0-9]{2} [0-9]{3} [0-9]{3}$";

	private static Pattern emailPattern = Pattern.compile(Validator.REGEX_EMAIL);
	private static Pattern pricePattern = Pattern.compile(Validator.REGEX_PRICE);
	private static Pattern celullarPattern = Pattern.compile(Validator.REGEX_CELULLAR);

	private Map<Node,String> invalidFields = new HashMap<>();

	private String error(final TextField field, final boolean store, final String message) {
		if(!field.getStyleClass().contains("error"))
			field.getStyleClass().add("error");

		if(store)
			this.invalidFields.put(field, message);

		return message;
	}

	public String validateEmail(final TextField field, final boolean store) {
		final String ret = "O email digitado é inválido.";

		if(this.validateNotEmpty(field, store) != null)
			return this.error(field, store, ret);

		final String email = field.getText();

		final Matcher matcher = Validator.emailPattern.matcher(email);
		if(!matcher.matches())
			return this.error(field, store, ret);

		field.getStyleClass().remove("error");
		return null;
	}

	public String validateCelullar(final TextField field, final boolean store) {
		final String ret = "O celular digitado é inválido.";

		if(this.validateNotEmpty(field, store) != null)
			return this.error(field, store, ret);

		final String celular = field.getText();

		final Matcher matcher = Validator.celullarPattern.matcher(celular);
		if(!matcher.matches())
			return this.error(field, store, ret);

		field.getStyleClass().remove("error");
		return null;
	}

	public String validateCPF(final TextField field, final boolean store) {
		final String ret = "O CPF digitado é inválido.";

		if(this.validateNotEmpty(field, store) != null)
			return this.error(field, store, ret);

		final String cpf = field.getText();

		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
				cpf.equals("22222222222") || cpf.equals("33333333333") ||
				cpf.equals("44444444444") || cpf.equals("55555555555") ||
				cpf.equals("66666666666") || cpf.equals("77777777777") ||
				cpf.equals("88888888888") || cpf.equals("99999999999") ||
				(cpf.length() != 11)) {
			return this.error(field, store, ret);
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i=0; i<9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = cpf.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for(i=0; i<10; i++) {
				num = cpf.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else dig11 = (char)(r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 != cpf.charAt(9)) || (dig11 != cpf.charAt(10))) {
				return this.error(field,store,ret);
			}
		} catch (final InputMismatchException erro) {
			return this.error(field, store, ret);
		}

		field.getStyleClass().remove("error");
		return null;

	}

	public String validateNotEmpty(final TextField field, final boolean store) {
		final String ret = "Esta informação não pode ser vazia.";

		if(field == null)
			return this.error(field, store, ret);


		final String value = field.getText();
		if(value == null)
			return this.error(field, store, ret);

		if("".equals(value.trim())){
			return this.error(field, store, ret);
		}

		field.getStyleClass().remove("error");
		return null;
	}

	public String validateIntegerNotEmpty(final TextField field, final boolean store) {
		final String ret = "Esta informação não pode ser vazia e deve ter apenas números de 0 à 9.";
		final String v = this.validateNotEmpty(field, store);
		if(v != null)
			return this.error(field, store, ret);

		try {
			final String value = field.getText();
			if(Integer.valueOf(value) == null)
				return this.error(field, store, ret);
		}catch(final NumberFormatException e) {
			return this.error(field, store, ret);
		}

		field.getStyleClass().remove("error");
		return null;
	}

	public String validateStringNotEmpty(final TextField field, final boolean store, final int min, final int max) {
		final String ret = "Esta informação não pode ser vazia e deve ter conter entre " + min + " e " + max + " caracteres.";
		final String v = this.validateNotEmpty(field, store);
		if(v != null)
			return this.error(field, store, ret);

		final String value = field.getText();
		if(value.length() < min || value.length() > max)
			return this.error(field, store, ret);

		field.getStyleClass().remove("error");
		return null;
	}

	public String validatePriceNotEmpty(final TextField field, final boolean store, final int integer, final int decimal) {
		final String ret = "Esta informação precisa ser um número decimal com no máximo " + integer + " números inteiros e " + decimal + " casas decimais.";
		final String v = this.validateNotEmpty(field, store);

		if(v != null)
			return this.error(field, store, ret);

		final String value = field.getText();
		if(Validator.pricePattern.matcher(value).matches()) {
			final String[] parts = value.split("[,.]");
			final String i = parts[0];
			if(i.length() > integer)
				return this.error(field, store, ret);
			if(parts.length > 1)
				if(parts[1].length() > decimal)
					return this.error(field, store, ret);
		}else
			return this.error(field, store, ret);

		field.getStyleClass().remove("error");
		return null;
	}

	public Map<Node,String> getInvalidFields() {
		return this.invalidFields;
	}

	public void setInvalidFields(final Map<Node,String> invalidFields) {
		this.invalidFields = invalidFields;
	}

	public boolean isEmpty() {
		return this.invalidFields.isEmpty();
	}

	public void clear() {
		this.invalidFields.clear();
	}

	public boolean containsKey(final Object key) {
		return this.invalidFields.containsKey(key);
	}

	public boolean containsValue(final Object value) {
		return this.invalidFields.containsValue(value);
	}

	public Set<Entry<Node, String>> entrySet() {
		return this.invalidFields.entrySet();
	}

	public String get(final Object key) {
		return this.invalidFields.get(key);
	}

	public Set<Node> keySet() {
		return this.invalidFields.keySet();
	}

	public String put(final Node key, final String value) {
		return this.invalidFields.put(key, value);
	}

	public void putAll(final Map<? extends Node, ? extends String> m) {
		this.invalidFields.putAll(m);
	}

	public boolean remove(final Object key, final Object value) {
		return this.invalidFields.remove(key, value);
	}

	public String remove(final Object key) {
		return this.invalidFields.remove(key);
	}

	public boolean replace(final Node key, final String oldValue, final String newValue) {
		return this.invalidFields.replace(key, oldValue, newValue);
	}

	public String replace(final Node key, final String value) {
		return this.invalidFields.replace(key, value);
	}

	public int size() {
		return this.invalidFields.size();
	}

	public Collection<String> values() {
		return this.invalidFields.values();
	}


}
