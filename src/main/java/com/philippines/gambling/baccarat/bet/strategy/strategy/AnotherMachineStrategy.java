
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy.strategy;

import com.philippines.gambling.baccarat.bet.strategy.BetStrategy;
import com.philippines.gambling.baccarat.bet.strategy.StrategyConstants;
import com.philippines.gambling.baccarat.game.BaccaratRoundPlay;
import com.philippines.gambling.baccarat.machine.PokerCardMachine;
import com.philippines.gambling.baccarat.rule.BaccartCalculator;
import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetResultEnum;

/**
 * [02]SWITCH策略
 * @author winter
 * @date 2025年3月13日上午10:21:41
 */
public class AnotherMachineStrategy extends StrategyConstants implements BetStrategy {
	
	@Override
	public BetResultEnum getBetWho(BetResultEnum ...onRound) {
		return anotherMachineStrategy();
	}
	@Override
	public BetResult getCurrentRoundBetWho(BetResult... previousRoundBetResult) {
		return null;
	}

	/**
	 * [05]用另外一台机器跑出来的结果来bet本台机器策略
	 * @return 
	 * @author: winter
	 * @date: 2025-03-11 11:58:30
	 */
	protected static BetResultEnum anotherMachineStrategy() {
		BaccartCalculator baccartCalculator = new BaccartCalculator();
		PokerCardMachine pokerCardMachine = new PokerCardMachine().init();
		//BetWinerEnum currentRoundWhoWin = BaccaratRoundPlay.playOneRound(pokerCardMachine, baccartCalculator);
		BetResult currentRoundWhoWin = BaccaratRoundPlay.playOneRound(pokerCardMachine, baccartCalculator);
		return currentRoundWhoWin.getBetWho();
	}
}

