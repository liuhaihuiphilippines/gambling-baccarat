/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.card;

import lombok.Getter;

/**
 * @author winter
 * @date 6 Feb 2025 1:13:17 am
 */
@Getter
public enum PokerCardFigureNameEnum {

	HEARTS("红桃"),
	DIAMONDS("方块"),
	SPADES("黑桃"),
	CLUBS("梅花"),
	;
	private String figureName;
	
	PokerCardFigureNameEnum(String figureName) {
		this.figureName = figureName;
	}
}

