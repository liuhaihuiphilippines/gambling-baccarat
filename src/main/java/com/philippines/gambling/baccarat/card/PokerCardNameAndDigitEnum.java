/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.card;
/**
 * @author winter
 * @date 6 Feb 2025 1:13:24 am
 */
import lombok.Getter;

@Getter
public enum PokerCardNameAndDigitEnum {

	_A("A", 1),
	_2("2", 2),
	_3("3", 3),
	_4("4", 4),
	_5("5", 5),
	_6("6", 6),
	_7("7", 7),
	_8("8", 8),
	_9("9", 9),
	_10("10", 0),
	_J("J", 0),
	_Q("Q", 0),
	_K("K", 0),
	
	;
	private String cardName;
	private int cardDigit;
	
	PokerCardNameAndDigitEnum(String cardName, int cardDigit) {
		this.cardName = cardName;
		this.cardDigit = cardDigit;
	}
}