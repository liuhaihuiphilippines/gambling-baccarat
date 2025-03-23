/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.rule;

import lombok.Getter;

/**
 * @author winter
 * @date 6 Feb 2025 12:41:08 am
 */
@Getter
public enum BetWinerEnum {

	PLAYER_NEED_ONMORECARD("玩家还需要再发一张牌才能判断输赢", 1),
	BANKER_NEED_ONMORECARD("庄家还需要再发一张牌才能判断输赢", 1),
	BANKER_WIN("庄家赢", 2),
	TIE("双方打平", 8),
	PLAYER_WIN("玩家赢", 2),
	;

	private String winnerName;
	
	/**
	 * 赔率
	 */
	private int odds;
	
	BetWinerEnum(String winnerName, int odds) {
		this.winnerName = winnerName;
		this.odds = odds;
	}
}

