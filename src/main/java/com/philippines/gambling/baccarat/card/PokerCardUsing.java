/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.card;

import lombok.Getter;
import lombok.Setter;

/**
 * 记录被使用了的卡的信息
 * Banker或者Player的那个PokerCardBox里面
 * 装的是这个PokerCardUsing而不是PokerCard
 * @author winter
 * @date: 7 Feb 20252:56:26 am
 */
@Getter
@Setter
public class PokerCardUsing extends PokerCard {

	/**
	 * 记录这张卡的出场顺序
	 * 也就是在这一轮里面是第几个出场的
	 * (取值只可能是数字1-6)
	 */
	private short cardOutSequence;
	
	/**
	 * 记录这张卡的使用时间
	 * 取值System.currentTimeMillis()
	 */
	private long cardUsedTime;
	

	/**
	 * 带参数的构造函数
	 * @param pokerCard
	 * @param cardOutSequence
	 */
	public PokerCardUsing(PokerCard pokerCard, short cardOutSequence) {
		super(pokerCard.getFigureName(), pokerCard.getCardName(),pokerCard.getCardDigit());
		this.cardOutSequence = cardOutSequence;
		this.cardUsedTime = System.currentTimeMillis();
	}

}

