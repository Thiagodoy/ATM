/*
 * Copyright (c) 2015 VA programming
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package br.com.agencialove.tpa.utils;


import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

public class MaskField extends TextField {


	/**
	 * позиция в маске позволит ввести только цифры
	 */
	public static final char MASK_DIGIT = 'D';

	/**
	 * позиция в маске позволит ввести буквы и цифры
	 */
	public static final char MASK_DIG_OR_CHAR = 'W';

	/**
	 * позиция в маске позволит ввести только буквы
	 */
	public static final char MASK_CHARACTER = 'A';


	public static final char WHAT_MASK_CHAR = '#';
	public static final char WHAT_MASK_NO_CHAR = '-';


	public static final char PLACEHOLDER_CHAR_DEFAULT = '_';


	private List<Position> objectMask = new ArrayList<>();

	/**
	 * простой текст без применения маски
	 */
	private StringProperty plainText;

	public final String getPlainText() {
		return this.plainTextProperty().get();
	}

	public final void setPlainText(final String value) {
		this.plainTextProperty().set(value);
		this.updateShowingField();
	}

	public final StringProperty plainTextProperty() {
		if (this.plainText == null)
			this.plainText = new SimpleStringProperty(this, "plainText", "");
		return this.plainText;
	}


	/**
	 * это сама маска видимая в поле ввода
	 */
	private StringProperty mask;

	public final String getMask() {
		return this.maskProperty().get();
	}

	public final void setMask(final String value) {
		this.maskProperty().set(value);
		this.rebuildObjectMask();
		this.updateShowingField();
	}

	public final StringProperty maskProperty() {
		if (this.mask == null)
			this.mask = new SimpleStringProperty(this, "mask");

		return this.mask;
	}


	/**
	 * если маска должна отображать символы которые зарезервированы для маски, то задается дополнительная подсказка где символ маски, а где просто символ
	 */
	private StringProperty whatMask;

	public final String getWhatMask() {
		return this.whatMaskProperty().get();
	}

	public final void setWhatMask(final String value) {
		this.whatMaskProperty().set(value);
		this.rebuildObjectMask();
		this.updateShowingField();
	}

	public final StringProperty whatMaskProperty() {
		if (this.whatMask == null) {
			this.whatMask = new SimpleStringProperty(this, "whatMask");
		}
		return this.whatMask;
	}


	/**
	 * это символы замещения
	 */
	private StringProperty placeholder;

	public final String getPlaceholder() {
		return this.placeholderProperty().get();
	}

	public final void setPlaceholder(final String value) {
		this.placeholderProperty().set(value);
		this.rebuildObjectMask();
		this.updateShowingField();
	}

	public final StringProperty placeholderProperty() {
		if (this.placeholder == null)
			this.placeholder = new SimpleStringProperty(this, "placeholder");
		return this.placeholder;
	}


	private class Position {
		public char mask;
		public char whatMask;
		public char placeholder;

		public Position(final char mask, final char whatMask, final char placeholder) {
			this.mask = mask;
			this.placeholder = placeholder;
			this.whatMask = whatMask;
		}

		public boolean isPlainCharacter()
		{
			return this.whatMask == MaskField.WHAT_MASK_CHAR;
		}

		public boolean isCorrect(final char c)
		{
			switch (this.mask)
			{
			case MASK_DIGIT:
				return Character.isDigit(c);
			case MASK_CHARACTER:
				return Character.isLetter(c);
			case MASK_DIG_OR_CHAR:
				return Character.isLetter(c) || Character.isDigit(c);
			}
			return false;
		}
	}


	/**
	 * формирует список объектов Position по каждому символу маски
	 */
	private void rebuildObjectMask() {
		this.objectMask = new ArrayList<>();

		for (int i = 0; i < this.getMask().length(); i++) {
			final char m = this.getMask().charAt(i);
			char w = MaskField.WHAT_MASK_CHAR;
			char p = MaskField.PLACEHOLDER_CHAR_DEFAULT;

			if (this.getWhatMask() != null && i < this.getWhatMask().length()) {
				//конкретно указано символ маски это или нет
				if (this.getWhatMask().charAt(i) != MaskField.WHAT_MASK_CHAR) {
					w = MaskField.WHAT_MASK_NO_CHAR;
				}
			}
			else
			{
				//так как не указано что за символ - понимаем самостоятельно
				//и если символ не находится среди символов маски - то это считается простым литералом
				if (m != MaskField.MASK_CHARACTER && m != MaskField.MASK_DIG_OR_CHAR && m != MaskField.MASK_DIGIT)
					w = MaskField.WHAT_MASK_NO_CHAR;

			}

			if (this.getPlaceholder() != null && i < this.getPlaceholder().length())
				p = this.getPlaceholder().charAt(i);

			this.objectMask.add(new Position(m, w, p));
		}
	}


	/**
	 * функция как бы накладывает просто текст plainText на заданную маску,
	 * корректирует позицию каретки
	 */
	private void updateShowingField()
	{
		int counterPlainCharInMask = 0;
		int lastPositionPlainCharInMask = 0;
		int firstPlaceholderInMask = -1;
		String textMask = "";
		String textPlain = this.getPlainText();
		for (int i = 0; i < this.objectMask.size(); i++) {
			final Position p = this.objectMask.get(i);
			if (p.isPlainCharacter()) {
				if (textPlain.length() > counterPlainCharInMask) {

					char c = textPlain.charAt(counterPlainCharInMask);
					while (!p.isCorrect(c))
					{
						//вырезаем то что не подошло
						textPlain = textPlain.substring(0, counterPlainCharInMask) + textPlain.substring(counterPlainCharInMask + 1);

						if (textPlain.length() > counterPlainCharInMask)
							c = textPlain.charAt(counterPlainCharInMask);
						else
							break;
					}

					textMask += c;
					lastPositionPlainCharInMask = i;
				}
				else {
					textMask += p.placeholder;
					if (firstPlaceholderInMask == -1)
						firstPlaceholderInMask = i;
				}

				counterPlainCharInMask++;

			} else {
				textMask += p.mask;
			}
		}

		this.setText(textMask);

		if (firstPlaceholderInMask == -1)
			firstPlaceholderInMask = 0;

		final int caretPosition = (textPlain.length() > 0 ? lastPositionPlainCharInMask + 1 : firstPlaceholderInMask);
		this.selectRange(caretPosition, caretPosition);

		if (textPlain.length() > counterPlainCharInMask)
			textPlain = textPlain.substring(0, counterPlainCharInMask);

		if (!textPlain.equals(this.getPlainText()))
			this.setPlainText(textPlain);

	}



	private int interpretMaskPositionInPlainPosition(final int posMask)
	{
		int posPlain = 0;

		for (int i = 0; i < this.objectMask.size() && i < posMask; i++) {
			final Position p = this.objectMask.get(i);
			if (p.isPlainCharacter())
				posPlain++;
		}

		return posPlain;
	}


	@Override
	public void replaceText(final int start, final int end, final String text) {


		final int plainStart = this.interpretMaskPositionInPlainPosition(start);
		final int plainEnd = this.interpretMaskPositionInPlainPosition(end);

		String plainText1 = "";
		if (this.getPlainText().length() > plainStart)
			plainText1 = this.getPlainText().substring(0, plainStart);
		else
			plainText1 = this.getPlainText();


		String plainText2 = "";
		if (this.getPlainText().length() > plainEnd)
			plainText2 = this.getPlainText().substring(plainEnd);
		else
			plainText2 = "";


		this.setPlainText(plainText1 + text + plainText2);

	}


}
