
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy;

import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetWinerEnum;

/**
 * @author winter
 * @date 2025年3月13日上午10:22:28
 */
public interface BetStrategy {

	BetWinerEnum getBetWho(BetWinerEnum ...onRound);
	
	BetResult getCurrentRoundBetWho(BetResult ...previousRoundBetResult);
	
}

