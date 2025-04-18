
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy.strategy;

import com.philippines.gambling.baccarat.bet.strategy.BetStrategy;
import com.philippines.gambling.baccarat.bet.strategy.StrategyConstants;
import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetResultEnum;

import cn.hutool.core.util.RandomUtil;

/**
 * [01]随机策略
 * @author winter
 * @date 2025年3月13日上午10:21:41
 */
public class RandomStrategy extends StrategyConstants implements BetStrategy {
	
	@Override
	public BetResultEnum getBetWho(BetResultEnum ...onRound) {
		return randomStrategy();
	}
	@Override
	public BetResult getCurrentRoundBetWho(BetResult... previousRoundBetResult) {
		return null;
	}

	/**
	 * [01]随机策略
	 * @return 
	 * @author: winter
	 * @date: 2025-03-11 11:58:30
	 */
	protected static BetResultEnum randomStrategy() {
		//策略就是：1000万-9000万之间取随机数
		long randomLong = RandomUtil.randomLong(1000000L, 9000000L);
		//对随机数进行数字求和,如4360 2305=23
		String randomLongString = String.valueOf(randomLong);
		char[] charArray = randomLongString.toCharArray();
		int charSum = 0;
		for(char charStr : charArray) {
			Integer digit = Integer.valueOf(charStr);
			charSum += digit;
		}
		//对23进行取模3
		//这个时候是3,3,3分的
		//要将其转换成25,24,3这样子
		//所以其实实际应该是要取模52
		//然后按分布情况取下标
		int charSumMode = charSum % 52;
		if(charSumMode <= 25) {
			return BETPOOL[0];
		}else if(charSumMode <= 49) {
			return BETPOOL[1];
		}else {
			return BETPOOL[2];
		}
	}
}

