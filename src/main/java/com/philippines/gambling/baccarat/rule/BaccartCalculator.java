
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.rule;

import java.util.List;
import java.util.Map;

import com.philippines.gambling.baccarat.card.PokerCardUsing;
import com.philippines.gambling.baccarat.constant.PrintOut;
import com.philippines.gambling.baccarat.game.BaccaratGame;
import com.philippines.gambling.baccarat.role.BaccaratBanker;
import com.philippines.gambling.baccarat.role.BaccaratPlayer;

/**
 * @author winter
 * @date 2025年3月10日下午3:15:42
 */
public class BaccartCalculator {

	protected long roundNo;//当前是第几个回合
	protected BaccaratGame baccaratGame;
	/**
	 * 玩家本一轮下注的金额列表
	 */
	protected Map<BaccaratPlayer, BetResultOfEachRound> betResultMap;

	protected long getRoundNo() {
		return roundNo;
	}
	protected BaccaratGame getBaccaratGame() {
		return baccaratGame;
	}
	protected Map<BaccaratPlayer, BetResultOfEachRound> getBetResultMap() {
		return betResultMap;
	}
	
	
	/**
	 * 计算本局的输赢
	 */
	protected void calculateAndSetWinResult() {
		BetResultEnum whoWin = calculateWhoWin();
		setWinResult(whoWin);
	}

	/**
	 * 计算本轮谁赢了
	 * @author winter
	 * @return
	 */
	private BetResultEnum calculateWhoWin() {
		/**
		 * 拿这两个系统玩家的牌来算输赢(BIG_BOSS VS BIG_GAMBLER)
		 */
		BaccaratBanker systemBaccaratBanker = getBaccaratGame().getBaccaratBanker();
		BaccaratPlayer systemBaccaratPlayer = getBaccaratGame().getBaccaratPlayerList().get(0);
		int digitSumBanker = systemBaccaratBanker.getPokerCardBoxDigitSum();
		int digitSumPlayer = systemBaccaratPlayer.getPokerCardBoxDigitSum();
		if(digitSumBanker > digitSumPlayer) {
			return BetResultEnum.BANKER_WIN;
		}else if(digitSumBanker < digitSumPlayer) {
			return BetResultEnum.PLAYER_WIN;
		}
		return BetResultEnum.TIE;
	}
	

	/**
	 * 根据赔率更新所有本轮下注玩家的cardBalance(设置玩家赢的或者输掉的钱)
	 * @author winter
	 * @param whoWin
	 */
	private void setWinResult(BetResultEnum whoWin) {
		BaccaratBanker systemBaccaratBanker = getBaccaratGame().getBaccaratBanker();
		/**
		 * 注意这里取的是所有本轮的下注玩家betResultMap
		 * 而不是取所有玩家this.baccaratGame.getBaccaratPlayerList()
		 */
		for(Map.Entry<BaccaratPlayer, BetResultOfEachRound> entry : getBetResultMap().entrySet()) {
			BaccaratPlayer betPlayer = entry.getKey();
			BetResultOfEachRound betResult = entry.getValue();
			if(betResult.getBetWhoWin() == whoWin) {
				//堵中了，按照(下注的赔率*下注金额)给玩家加钱
				//banker和player同时做取反操作，一方扣钱另一方就一定是加钱
				betPlayer.addBalance(whoWin.getOdds() * betResult.getBetAmount());
				systemBaccaratBanker.addBalance(-1 * whoWin.getOdds() * betResult.getBetAmount());
			}else {
				//没堵中，仅按照(下注金额)扣玩家的钱
				//:::::::(这里输掉了之后不能再扣了，否则就出问题了，前面下注的时候已经扣了)
				//banker和player同时做取反操作，一方扣钱另一方就一定是加钱
				//betPlayer.addBalance(-1 * betResult.getBetAmount());
				//systemBaccaratBanker.addBalance(betResult.getBetAmount());
			}
		}
		BaccartGloalResult.addWinResult(whoWin.name().charAt(0));
		if(PrintOut.isSystemOutEnabled) {
			System.out.println("ROUND["+ getRoundNo()
					+ "] => 本轮：[" + whoWin.name() + "]\t"
					+ "PLAYER" + BaccaratPlayer.getSystemPlayer().displayCardBoxResult()
					+ "   VS  "
					+ "BANKER" + BaccaratBanker.getSystemBanker().displayCardBoxResult());
		}
	}
	public BetResultEnum twotwoCompare(List<PokerCardUsing> pokerCardBoxPlayer, List<PokerCardUsing> pokerCardBoxBanker) {
		return calculateBothSideCardDigitResult(pokerCardBoxPlayer, pokerCardBoxBanker);
	}
	private BetResultEnum calculateBothSideCardDigitResult(List<PokerCardUsing> pokerCardBoxPlayer, List<PokerCardUsing> pokerCardBoxBanker) {
		//PLAYER
		int playerCardCount = pokerCardBoxPlayer.size();
		int playerFirstCardDigit = pokerCardBoxPlayer.get(0).getCardDigit();
		int playerSecondCardDigit = pokerCardBoxPlayer.get(1).getCardDigit();
		int playerThirdCardDigit = pokerCardBoxPlayer.size() > 2 ? pokerCardBoxPlayer.get(2).getCardDigit() : 0;
		int playerCardDigitSum = (playerFirstCardDigit + playerSecondCardDigit + playerThirdCardDigit) % 10;
		//BANKER
		int bankerCardCount = pokerCardBoxBanker.size();
		int bankerFirstCardDigit = pokerCardBoxBanker.get(0).getCardDigit();
		int bankerSecondCardDigit = pokerCardBoxBanker.get(1).getCardDigit();
		int bankerThirdCardDigit = pokerCardBoxBanker.size() > 2 ? pokerCardBoxBanker.get(2).getCardDigit() : 0;
		int bankerCardDigitSum = (bankerFirstCardDigit + bankerSecondCardDigit + bankerThirdCardDigit) % 10;
		/**
		 * 不补牌,例牌赢法(两张牌定输赢)
		 */
		if(playerCardDigitSum == 8 || playerCardDigitSum == 9) {
			//COMPARE
			//直接比较大小
			return compareDirectory(playerCardDigitSum, bankerCardDigitSum);
		}
		
		
		/**
		 * 闲家不补牌，6或7点的时候(2张或3张牌才能定输赢)
		 */
		if(playerCardDigitSum == 6 || playerCardDigitSum == 7) {
			if(bankerCardDigitSum < 6 && bankerCardCount == 2) {
				return BetResultEnum.BANKER_NEED_ONMORECARD;
			}
			return compareDirectory(playerCardDigitSum, bankerCardDigitSum);
		}
		/**
		 * 闲家补一张： 在闲家总点数为0至5任意点数时，两牌合计1、2、3、4、5或10点。
		 * 只要庄家总点数不为8或9点时，闲家必须增牌，如果庄家在此时点数为8或9点，则闲家不补牌庄家赢。
		 * 庄家补第三张牌的规则：
		 * 0，1或2点：除非闲家得到总计8或9点的例牌，庄家补牌。
		 * 3点: 如果闲家补的第三张牌为0，1，2，3，4，5，6，7或9点，则庄家补牌。当闲家补牌为8点时，庄家不补牌。
		 * 4点：当闲家补的第三张牌为2，3，4，5，6或7点时，庄家补牌。当闲家补牌为0，1，8或9点时，庄家不补牌。
		 * 5点：当闲家补的第三张牌为4，5，6或7点时，庄家补牌。闲家补牌为0，1，2，3，8或9点时，庄家不补牌。
		 * 6点：当闲家补的第三张牌为6或7点时，庄家补牌。当闲家补的牌为0，1，2，3，4，5，8或9点时，庄家不补牌。
		 * 7点: 双方均不补牌。
		 * 8或9点：例牌数字任何一方都不补第三张牌。
		 */
		if(playerCardDigitSum <= 5) {
			if(bankerCardDigitSum >= 8) {
				return BetResultEnum.BANKER_WIN;
			}
			
			if(playerCardCount <= 2) {
				return BetResultEnum.PLAYER_NEED_ONMORECARD;
			}
			
			if(bankerCardCount <= 2) {
				if(bankerCardDigitSum <= 2){
					return BetResultEnum.BANKER_NEED_ONMORECARD;
				}
				if(bankerCardDigitSum == 3){
					if(playerThirdCardDigit >= 0 && playerThirdCardDigit != 8) {
						return BetResultEnum.BANKER_NEED_ONMORECARD;
					}
				}
				if(bankerCardDigitSum == 4){
					if(playerThirdCardDigit >= 0 && playerThirdCardDigit != 0 
							&& playerThirdCardDigit != 8 && playerThirdCardDigit != 9) {
						return BetResultEnum.BANKER_NEED_ONMORECARD;
					}
				}
				if(bankerCardDigitSum == 5){
					if(playerThirdCardDigit >= 0 && playerThirdCardDigit == 4 
							&& playerThirdCardDigit == 5 && playerThirdCardDigit == 6
							&& playerThirdCardDigit == 7) {
						return BetResultEnum.BANKER_NEED_ONMORECARD;
					}
				}
				if(bankerCardDigitSum == 6){
					if(playerThirdCardDigit >= 0 && playerThirdCardDigit == 6
							&& playerThirdCardDigit == 7) {
						return BetResultEnum.BANKER_NEED_ONMORECARD;
					}
				}
			}
			return compareDirectory(playerCardDigitSum, bankerCardDigitSum);
		}
		throw new RuntimeException("未知的结果比较!playerCardDigitSum=" + playerCardDigitSum);
	}
	private BetResultEnum compareDirectory(int playerCardDigitSum, int bankerCardDigitSum) {
		if(playerCardDigitSum == bankerCardDigitSum) {
			return BetResultEnum.TIE;
		}else if(playerCardDigitSum < bankerCardDigitSum) {
			return BetResultEnum.BANKER_WIN;
		}else {
			return BetResultEnum.PLAYER_WIN;
		}
	}
	
}

