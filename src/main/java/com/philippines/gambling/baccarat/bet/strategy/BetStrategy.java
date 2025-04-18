
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy;

import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetResultEnum;

/**
 * @author winter
 * @date 2025年3月13日上午10:22:28
 */
public interface BetStrategy {

	BetResultEnum getBetWho(BetResultEnum ...onRound);
	
	BetResult getCurrentRoundBetWho(BetResult ...previousRoundBetResult);
	
}

