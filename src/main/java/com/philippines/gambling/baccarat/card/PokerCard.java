/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author winter
 * @date 6 Feb 2025 12:57:29 am
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokerCard {

	/**
	 * 红桃，方块，黑桃，梅花
	 */
	private String figureName;
	
	/**
	 * A,2,3,4,5,6,7,8,9,10,J,Q,K
	 */
	private String cardName;
	
	/**
	 * 1,2,3,4,5,6,7,8,9,0,0,0,0
	 */
	private int cardDigit;
	
}

