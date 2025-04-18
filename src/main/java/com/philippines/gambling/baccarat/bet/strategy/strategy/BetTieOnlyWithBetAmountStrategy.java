
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy.strategy;

import com.philippines.gambling.baccarat.bet.strategy.BetStrategy;
import com.philippines.gambling.baccarat.bet.strategy.StrategyConstants;
import com.philippines.gambling.baccarat.constant.PrintOut;
import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetResultEnum;

/**
 * 
 * @author winter
 * @date: 20 Mar 202510:55:23 pm
 */
public class BetTieOnlyWithBetAmountStrategy extends StrategyConstants implements BetStrategy {
	
	@Override
	public BetResultEnum getBetWho(BetResultEnum ...onRound) {
		return null;
	}
	@Override
	public BetResult getCurrentRoundBetWho(BetResult ...previousRoundBetResult) {
		return betTieOnlyWithBetAmountStrategy(previousRoundBetResult);
	}
	
	protected static BetResult betTieOnlyWithBetAmountStrategy(BetResult ...previousRoundBetResult) {
		BetResult previousOneRoundBetResult = previousRoundBetResult[0];
		//还没有betResult记录，说明还没有开始玩，是开局
		if(previousOneRoundBetResult == null) {
			return BetResult.builder()
						.betWho(BetResultEnum.TIE).betAmount(PrintOut.BET_MONEY_PER_ROUND)
						.whoWin(null).totalBalance(PrintOut.HOW_MUCH_MONEY_IHAVE).build();
		}
		//有Bet记录，说明上次没有Bet
		previousOneRoundBetResult.setBetWho(BetResultEnum.TIE);
		previousOneRoundBetResult.setBetAmount(PrintOut.BET_MONEY_PER_ROUND);
		return previousOneRoundBetResult;
	}

}

