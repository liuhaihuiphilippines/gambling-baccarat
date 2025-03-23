
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy.strategy;

import com.philippines.gambling.baccarat.bet.strategy.BetStrategy;
import com.philippines.gambling.baccarat.bet.strategy.StrategyConstants;
import com.philippines.gambling.baccarat.constant.PrintOut;
import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetWinerEnum;

/**
 * TIE完之后
 * 1、BET一次连续TIE
 * 2、从第6次开始BET TIE,直到BET成功(设置上限为9次，超过9次就不追啦)
 * @author winter
 * @date: 20 Mar 202510:55:23 pm
 */
public class BetTieContinueslyAndStep6to9TimesWithBetAmountStrategy extends StrategyConstants implements BetStrategy {
	
	@Override
	public BetWinerEnum getBetWho(BetWinerEnum ...onRound) {
		return null;
	}
	@Override
	public BetResult getCurrentRoundBetWho(BetResult ...previousRoundBetResult) {
		return betTieContinueslyAndStep6to9TimesWithBetAmountStrategy(previousRoundBetResult);
	}
	
	protected static int tietoCurrentStepDistance = 1;
	protected static BetResult betTieContinueslyAndStep6to9TimesWithBetAmountStrategy(BetResult ...previousRoundBetResult) {
		BetResult previousOneRoundBetResult = previousRoundBetResult[0];
		//还没有betResult记录，说明还没有开始玩，是开局
		if(previousOneRoundBetResult == null) {
			//第一次不下注
			return BetResult.builder()
						.betWho(null).betAmount(PrintOut.BET_MONEY_PER_ROUND)
						.whoWin(null).totalBalance(PrintOut.HOW_MUCH_MONEY_IHAVE).build();
		}
		//有Bet记录，说明上次没有Bet
		if(previousOneRoundBetResult.getWhoWin() == BetWinerEnum.TIE) {
			//TIE了，将和上一次TIE的距离步长归零
			tietoCurrentStepDistance = 0;
			//连续BET一次
			//previousOneRoundBetResult.setBetWho(BetWinerEnum.TIE);
			//不连续下注
			previousOneRoundBetResult.setBetWho(null);
		}else {
			//说明不是TIE
			tietoCurrentStepDistance++;
			if(tietoCurrentStepDistance >= 6 && tietoCurrentStepDistance <= 9) {
				//第6次到第9次连续bet 4 次
				previousOneRoundBetResult.setBetWho(BetWinerEnum.TIE);
			}else {
				//不下注
				previousOneRoundBetResult.setBetWho(null);
			}
		}
		previousOneRoundBetResult.setBetAmount(PrintOut.BET_MONEY_PER_ROUND);
		return previousOneRoundBetResult;
	}

}

