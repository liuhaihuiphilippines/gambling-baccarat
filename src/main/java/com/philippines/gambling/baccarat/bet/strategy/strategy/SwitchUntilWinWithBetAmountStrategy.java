
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
 * @author winter
 * @date 2025年3月13日上午10:21:41
 */
public class SwitchUntilWinWithBetAmountStrategy extends StrategyConstants implements BetStrategy {
	
	@Override
	public BetWinerEnum getBetWho(BetWinerEnum ...onRound) {
		return null;
	}
	@Override
	public BetResult getCurrentRoundBetWho(BetResult ...previousRoundBetResult) {
		return switchUntilWinWithBetAmountStrategy(previousRoundBetResult);
	}

	/**
	 * 找出现两次的时候就bet
	 * 如果出现2次PLAYER就betBANKER
	 * 如果出现2次BANKER就betPLAYER
	 * @return 
	 * @author: winter
	 * @date: 2025-03-11 11:58:30
	 */
	protected static BetResult switchUntilWinWithBetAmountStrategy(BetResult ...previousRoundBetResult) {
		BetResult previousOneRoundBetResult = previousRoundBetResult[0];
		//还没有betResult记录，说明还没有开始玩，是开局
		if(previousOneRoundBetResult == null) {
			return BetResult.builder()
						.betWho(null).betAmount(PrintOut.BET_MONEY_PER_ROUND)
						.whoWin(null).totalBalance(PrintOut.HOW_MUCH_MONEY_IHAVE).build();
		}
		
		//有Bet记录，说明上次没有Bet
		BetWinerEnum previousRoundWhoWin = previousOneRoundBetResult.getWhoWin();
		BetWinerEnum currentRoundBetWho = getCurrentRoundIShouldBet(previousRoundWhoWin);
		//需要Bet,则计算bet谁和bet金额
		if(currentRoundBetWho != null) {
			//有Bet记录，判断上一次的Bet输赢情况
			Integer currentBetAmount = 0;
			if(previousOneRoundBetResult.getBetWho() == previousOneRoundBetResult.getWhoWin()) {
				//赢了下一个保持上一局的下注额度不变,初始额度是：PrintOut.BET_MONEY_PER_ROUND
				//currentBetAmount = previousOneRoundBetResult.getBetAmount();
				currentBetAmount = PrintOut.BET_MONEY_PER_ROUND;
			}else {
				//输了下注金额翻倍
				//currentBetAmount = previousOneRoundBetResult.getBetAmount() * 2;
				currentBetAmount = PrintOut.BET_MONEY_PER_ROUND;
			}

			previousOneRoundBetResult.setBetWho(currentRoundBetWho);
			previousOneRoundBetResult.setBetAmount(currentBetAmount);
		}
		//本轮不下注
		return previousOneRoundBetResult;
	}
	protected static BetWinerEnum getCurrentRoundIShouldBet(BetWinerEnum previousRoundWhoWin) {
		if(previousRoundWhoWin == BetWinerEnum.BANKER_WIN) {
			return BetWinerEnum.PLAYER_WIN;
		}else if(previousRoundWhoWin == BetWinerEnum.PLAYER_WIN) {
			return BetWinerEnum.BANKER_WIN;
		}
		//说明是BetWinerEnum.TIE
		//TIE的时候就不下注了
		return null;
	}
}

