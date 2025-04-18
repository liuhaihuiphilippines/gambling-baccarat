/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.role;

import com.philippines.gambling.baccarat.constant.PrintOut;
import com.philippines.gambling.baccarat.rule.BaccartGloalResult;
import com.philippines.gambling.baccarat.rule.BetResultOfEachRound;
import com.philippines.gambling.baccarat.rule.BetResultEnum;

import cn.hutool.core.util.RandomUtil;

/**
 * @author winter
 * @date 6 Feb 2025 12:28:21 am
 */
public final class BaccaratPlayer extends BaccaratParticipant {

	private static final Integer[] BET_AMOUNT_ARR = new Integer[] {
			200, 300, 400, 500, 600, 700, 800, 900, 
			1000, 1100,	1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900,
			2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900,
			3000, 3100, 3200, 3300, 3400, 3500, 3600, 3700, 3800, 3900,
			4000, 4100, 4200, 4300, 4400, 4500, 4600, 4700, 4800, 4900,
			5000, 5100, 5200, 5300, 5400, 5500, 5600, 5700, 5800, 5900
	};
	
	private static final BaccaratPlayer SYSTEM_PLAYER = new BaccaratPlayer
			("CASINO_PALYER_BIG_GAMBLER", RandomUtil.randomNumbers(25), Integer.MAX_VALUE);
	public static BaccaratPlayer getSystemPlayer() {
		return SYSTEM_PLAYER;
	}
	
	/**
	 * 子类构造方法
	 * @param cardName
	 * @param cardNumber
	 * @param cardBalance
	 */
	public BaccaratPlayer(String cardName, String cardNumber, int cardBalance) {
		super(cardName, cardNumber, cardBalance);
	}
	
	/**
	 * 玩家需要玩和下注
	 * @author winter
	 * @return
	 */
	public BetResultOfEachRound playAndBet() {
		if(this.getCardBalance() <= 0) {
			//没钱了，不玩了
			BaccartGloalResult.printSelf();
			System.exit(0);
			return null;
		}
		boolean iWantToBetAtCurrentRound = true;
		//系统玩家每次都要下注，其他玩家除外
		if(this != SYSTEM_PLAYER) {
			iWantToBetAtCurrentRound = RandomUtil.randomBoolean();
		}
		if(iWantToBetAtCurrentRound) {
			return playerBet();
		}
		return null;
	}

	/**
	 * 玩家下注
	 * @author winter
	 * @return
	 */
	private BetResultOfEachRound playerBet() {
		//按照BANKER:TIE:PLAYER=8:1:8的比例去下注
		int randomWhoWin = RandomUtil.randomInt(0, 17);
		randomWhoWin = randomWhoWin < 8 ? 0 : (randomWhoWin > 8 ? 2 : 1);
		int randomWhichBetAmount = RandomUtil.randomInt(0, BET_AMOUNT_ARR.length-1);
		BetResultEnum whoWin = BetResultEnum.values()[randomWhoWin];
		Integer currentTimeBetAmount = BET_AMOUNT_ARR[randomWhichBetAmount];
		if(currentTimeBetAmount > this.getCardBalance()) {
			//下注金额大于余额就取余额
			currentTimeBetAmount = this.getCardBalance();
		}
		/**
		 * 下注后要马上扣掉玩家的cardBalance
		 */
		this.setCardBalance(this.getCardBalance() - currentTimeBetAmount);
		//系统玩家不打印下注结果。
		if(PrintOut.isSystemOutEnabled) {
			if(this != SYSTEM_PLAYER) {
				System.out.println("玩家["+ this.getCardName() +"]本轮下注：["+ whoWin.name() + ",金额：" + currentTimeBetAmount + "]=>余额：" + this.getCardBalance());
			}
		}
		return BetResultOfEachRound.builder()
				.betWhoWin(whoWin)
				.betAmount(currentTimeBetAmount)
				.build();
	}
}

