
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy.strategy;

import com.philippines.gambling.baccarat.bet.strategy.BetStrategy;
import com.philippines.gambling.baccarat.bet.strategy.StrategyConstants;
import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetWinerEnum;

/**
 * @author winter
 * @date 2025年3月13日上午10:21:41
 */
public class FourTimesSwitchStrategy extends StrategyConstants implements BetStrategy {
	
	@Override
	public BetWinerEnum getBetWho(BetWinerEnum ...onRound) {
		return fourTimesSwitchStrategy(onRound);
	}
	@Override
	public BetResult getCurrentRoundBetWho(BetResult... previousRoundBetResult) {
		return null;
	}

	/**
	 * 找出现两次的时候就bet
	 * 如果出现2次PLAYER就betBANKER
	 * 如果出现2次BANKER就betPLAYER
	 * @return 
	 * @author: winter
	 * @date: 2025-03-11 11:58:30
	 */
	private static BetWinerEnum[] previousTwoResult = new BetWinerEnum[4];
	protected static BetWinerEnum fourTimesSwitchStrategy(BetWinerEnum ...onRound) {
		BetWinerEnum currentRoundBet = onRound[0];
		if(currentRoundBet == null) {
			return null;
		}
		previousTwoResult[0] = previousTwoResult[1];
		previousTwoResult[1] = previousTwoResult[2];
		previousTwoResult[2] = previousTwoResult[3];
		previousTwoResult[3] = currentRoundBet;
		if(previousTwoResult[0] == previousTwoResult[1] 
				&& previousTwoResult[1] == previousTwoResult[2]
						&& previousTwoResult[2] == previousTwoResult[3]) {
			currentRoundBet = getCurrentRoundIShouldBet(currentRoundBet);
			return currentRoundBet;
		}
		return null;
	}
	protected static BetWinerEnum getCurrentRoundIShouldBet(BetWinerEnum previousRoundBet) {
		if(previousRoundBet == BETPOOL[0]) {
			return BETPOOL[1];
		}else if(previousRoundBet == BETPOOL[1]) {
			return BETPOOL[0];
		}
		return BETPOOL[0];
	}
}

