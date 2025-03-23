
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy.combine;

import java.util.ArrayList;
import java.util.List;

import com.philippines.gambling.baccarat.bet.strategy.BetStrategy;
import com.philippines.gambling.baccarat.bet.strategy.StrategyConstants;
import com.philippines.gambling.baccarat.bet.strategy.strategy.FourTimesSwitchStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.TwoTimesSwitchStrategy;
import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetWinerEnum;

/**
 * @author winter
 * @date 2025年3月13日上午10:21:41
 */
public class CombineTwoTimesAndFourTimesSwitchStrategy extends StrategyConstants implements BetStrategy {
	
	private final static List<BetStrategy> strategyPackageList = new ArrayList<BetStrategy>();
	static {
		strategyPackageList.add(new TwoTimesSwitchStrategy());
		strategyPackageList.add(new FourTimesSwitchStrategy());
	}
	
	@Override
	public BetWinerEnum getBetWho(BetWinerEnum ...onRound) {
		for(BetStrategy betStrategy : strategyPackageList) {
			BetWinerEnum betWho = betStrategy.getBetWho(onRound);
			if(betWho != null) {
				return betWho;
			}
		}
		return null;
	}

	@Override
	public BetResult getCurrentRoundBetWho(BetResult... previousRoundBetResult) {
		return null;
	}

}

