
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
public class OneTwoTwoStrategy extends StrategyConstants implements BetStrategy {
	
	@Override
	public BetResultEnum getBetWho(BetResultEnum ...onRound) {
		return oneTwoTwoStrategy(onRound);
	}
	@Override
	public BetResult getCurrentRoundBetWho(BetResult... previousRoundBetResult) {
		return null;
	}

	/**
	 * [03]SWITCH BY PREVIOUS RESULT策略
	 * @return 
	 * @author: winter
	 * @date: 2025-03-11 11:58:30
	 */
	private static int oneTwoTwo = 0;
	protected static BetResultEnum oneTwoTwoStrategy(BetResultEnum ...onRound) {
		BetResultEnum currentRoundBet = onRound[0];
		if(currentRoundBet == null) {
			return BETPOOL[oneTwoTwo];
		}
		int oneTwoTwoIndex = ++oneTwoTwo%3;
		if(oneTwoTwoIndex == 0) {
			currentRoundBet = getByPreviousRoundBet(currentRoundBet);
		}
		return currentRoundBet;
	}
	protected static BetResultEnum getByPreviousRoundBet(BetResultEnum previousRoundBet) {
		if(previousRoundBet == BETPOOL[0]) {
			return BETPOOL[1];
		}else if(previousRoundBet == BETPOOL[1]) {
			return BETPOOL[0];
		}
		return BETPOOL[0];
	}
}

