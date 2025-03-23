
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.machine;

import com.philippines.gambling.baccarat.card.PokerCard;
import com.philippines.gambling.baccarat.card.PokerCardFigureNameEnum;
import com.philippines.gambling.baccarat.card.PokerCardNameAndDigitEnum;

/**
 * @author winter
 * @date 20 Mar 202510:28:38 pm
 */
public class PokerCardBox {
	
	//需要8副扑克牌
	private static final int CARD_TOTAL_DECKS = 8;

	//每一副扑克牌有多少种花色
	public static final int CARD_FIGURE_NUMS = PokerCardFigureNameEnum.values().length;
	//每一副扑克牌有多少个数字
	public static final int CARD_DIGIT_NUMS = PokerCardNameAndDigitEnum.values().length;
	
	//每一副扑克牌去掉大小王后剩余52张
	public static final int CARD_TOTAL_NUMS = CARD_TOTAL_DECKS * CARD_FIGURE_NUMS * CARD_DIGIT_NUMS;
	

	/**
	 * 创建8副扑克牌放到盒子里面
	 * @return 
	 * @author: winter
	 * @date: 2025-03-20 10:29:21
	 */
	public static PokerCard[] create8DeckOfPokerCards() {
		int cardArrayIndex = 0;
		PokerCard[] pokerCardArray = new PokerCard[CARD_TOTAL_NUMS];
		PokerCardFigureNameEnum[] pokerFigureNameEnums = PokerCardFigureNameEnum.values();
		PokerCardNameAndDigitEnum[] pokerCardNameAndDigitEnums = PokerCardNameAndDigitEnum.values();
		for(int deck = 1; deck <= CARD_TOTAL_DECKS; deck++) {
			//一共需要初始化8副扑克牌
			for(PokerCardFigureNameEnum pokerFigureName: pokerFigureNameEnums) {
				//4种花色
				for(PokerCardNameAndDigitEnum pokerCardNameAndDigit: pokerCardNameAndDigitEnums) {
					//52种面额
					pokerCardArray[cardArrayIndex++] = new PokerCard(pokerFigureName.getFigureName(), 
							pokerCardNameAndDigit.getCardName(), pokerCardNameAndDigit.getCardDigit());
				}
			}
		}
		return pokerCardArray;
	}
}

