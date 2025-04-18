
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy.strategy;

import com.philippines.gambling.baccarat.bet.strategy.BetStrategy;
import com.philippines.gambling.baccarat.bet.strategy.StrategyConstants;
import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetResultEnum;

/**
 * [02]SWITCH策略
 * @author winter
 * @date 2025年3月13日上午10:21:41
 */
public class SwitchStrategy extends StrategyConstants implements BetStrategy {
	
	@Override
	public BetResultEnum getBetWho(BetResultEnum ...onRound) {
		return switchStrategy();
	}
	@Override
	public BetResult getCurrentRoundBetWho(BetResult... previousRoundBetResult) {
		return null;
	}

	/**
	 * [02]SWITCH策略
	 * @return 
	 * @author: winter
	 * @date: 2025-03-11 11:58:30
	 */
	private static int currentRoundIndex = -1;
	protected static BetResultEnum switchStrategy() {
		//策略就是：1000万-9000万之间取随机数
		currentRoundIndex++;
		if(currentRoundIndex % 8 == 0) {
			return BETPOOL[2];
		}
		return BETPOOL[currentRoundIndex % 2];
	}
}

