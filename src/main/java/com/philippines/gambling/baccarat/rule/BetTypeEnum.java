/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.rule;

import lombok.Getter;

/**
 * 下注的类型
 * @author winter
 * @date 6 Feb 2025 12:41:08 am
 */
@Getter
public enum BetTypeEnum {

	BANKER_WIN("庄家赢", 2),
	PLAYER_WIN("玩家赢", 2),
	SUPER_TIE("双方打平", 8),
	PLAYER_WINS_WITH_7_2CARDS("超级幸运7-2张牌", 6),
	PLAYER_WINS_WITH_7_3CARDS("超级幸运7-3张牌", 15),
	PLAYER_WINS_WITH_7_OVER_BANKER6_4CARDS("超级幸运7-同时Banker出现6-4张牌", 30),
	PLAYER_WINS_WITH_7_OVER_BANKER6_5CARDS("超级幸运7-同时Banker出现6-5张牌", 40),
	PLAYER_WINS_WITH_7_OVER_BANKER6_6CARDS("超级幸运7-同时Banker出现6-6张牌", 100),
	PLAYER_PAIR("玩家出现对子", 8),
	BANKER_PAIR("庄家出现对子", 8),
	;

	private String winnerName;
	
	/**
	 * 赔率
	 */
	private int odds;
	
	BetTypeEnum(String winnerName, int odds) {
		this.winnerName = winnerName;
		this.odds = odds;
	}
}

