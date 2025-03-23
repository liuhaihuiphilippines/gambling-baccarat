/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.role;

import cn.hutool.core.util.RandomUtil;

/**
 * @author winter
 * @date: 6 Feb 2025 2:38:22 am
 */
public final class BaccaratBanker extends BaccaratParticipant {

	private static final BaccaratBanker SYSTEM_BANKER = new BaccaratBanker
			("CASINO_BANKER_BIG_BOSS", RandomUtil.randomNumbers(25), Integer.MAX_VALUE);
	public static BaccaratBanker getSystemBanker() {
		return SYSTEM_BANKER;
	}
	
	/**
	 * 子类构造方法
	 * @param cardName
	 * @param cardNumber
	 * @param cardBalance
	 */
	public BaccaratBanker(String cardName, String cardNumber, int cardBalance) {
		super(cardName, cardNumber, cardBalance);
	}
	
	/**
	 * 银行家只需要玩就可以了不需要下注
	 * @author winter
	 */
	public void play() {
		if(this.getCardBalance() < Integer.MAX_VALUE) {
			this.setCardBalance(Integer.MAX_VALUE);
		}
	}
	
}

