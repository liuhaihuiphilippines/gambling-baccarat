
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy.strategy;

import com.philippines.gambling.baccarat.bet.strategy.BetStrategy;
import com.philippines.gambling.baccarat.bet.strategy.StrategyConstants;
import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetWinerEnum;

/**
 * [02]SWITCH策略
 * @author winter
 * @date 2025年3月13日上午10:21:41
 */
public class SwitchByPreviousResultStrategy extends StrategyConstants implements BetStrategy {
	
	@Override
	public BetWinerEnum getBetWho(BetWinerEnum ...onRound) {
		return switchByPreviousResultStrategy(onRound);
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
	protected static BetWinerEnum switchByPreviousResultStrategy(BetWinerEnum ...onRound) {
		BetWinerEnum currentRound = onRound[0];
		if(currentRound == null) {
			return BETPOOL[0];
		}
		if(currentRound == BETPOOL[0]) {
			return BETPOOL[1];
		}else if(currentRound == BETPOOL[1]) {
			return BETPOOL[0];
		}
		return BETPOOL[0];
	}
}

