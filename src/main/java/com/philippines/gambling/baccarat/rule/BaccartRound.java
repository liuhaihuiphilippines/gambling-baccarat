/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.philippines.gambling.baccarat.card.PokerCard;
import com.philippines.gambling.baccarat.card.PokerCardUsing;
import com.philippines.gambling.baccarat.constant.PrintOut;
import com.philippines.gambling.baccarat.game.BaccaratGame;
import com.philippines.gambling.baccarat.machine.PokerCardMachine;
import com.philippines.gambling.baccarat.role.BaccaratBanker;
import com.philippines.gambling.baccarat.role.BaccaratPlayer;


/**
 * @author winter
 * @date 6 Feb 2025 12:37:04 am
 */
public class BaccartRound extends BaccartRuleChecker {

	public BaccartRound(BaccaratGame baccaratGame, long roundNo) {
		this.baccaratGame = baccaratGame;
		this.roundNo = roundNo;
	}
	
	
	public void play() {
		/**
		 * 触发银行家开始play
		 */
		BaccaratBanker baccaratBanker = baccaratGame.getBaccaratBanker();
		baccaratBanker.play();
		
		/**
		 * 触发所有玩家开始play，也就是让玩家可以投注了
		 */
		betResultMap = new HashMap<BaccaratPlayer, BetResultOfEachRound>();
		for(BaccaratPlayer baccaratPlayer : baccaratGame.getBaccaratPlayerList()) {
			BetResultOfEachRound betResult = baccaratPlayer.playAndBet();
			if(betResult != null) {
				betResultMap.put(baccaratPlayer, betResult);
			}
		}
		
		if(waitPlayerBetFinishedOrBetTimeout()) {
			checkIsBetAmountExcceededRange();
			checkAndSetCardsToEnoughForCurrentRound();
			getOneCardToPlayer((short)1);
			getOneCardToBanker((short)2);
			getOneCardToPlayer((short)3);
			getOneCardToBanker((short)4);
			if(checkIsNeedTheThirdCard()) {
				if(checkIsPlayerNeedOneMoreCard()){
					getOneCardToPlayer((short)5);
				}
				if(checkIsBankerNeedOneMoreCard()){
					getOneCardToBanker((short)6);
				}
			}
			calculateAndSetWinResult();
			displayWinResultAndPlayerBalance();
			clearCurrentRoundCards();
		}
	}

	
	/**
	 * 等待所有玩家都下注完成或者等待超时
	 * 不管是否所有玩家都下注完成在等待超时之后游戏都继续正常进行
	 */
	private boolean waitPlayerBetFinishedOrBetTimeout() {
		try {
			//Thread.sleep(1 * 1000);
			//Thread.sleep(1 * 50);
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
	


	/**
	 * 给玩家发一张牌
	 */
	private void getOneCardToPlayer(short cardOutSequence) {
		PokerCardMachine pokerCardMachine = this.baccaratGame.getPokerCardMachine();
		PokerCard pokerCard = pokerCardMachine.pickOutOnePokerCard();
		List<BaccaratPlayer> baccaratPlayerList = this.baccaratGame.getBaccaratPlayerList();
		for(BaccaratPlayer baccaratPlayer : baccaratPlayerList) {
			baccaratPlayer.receiveOneCard(new PokerCardUsing(pokerCard, cardOutSequence));
		}
	}
	
	/**
	 * 给庄家发一张牌
	 */
	private void getOneCardToBanker(short cardOutSequence) {
		PokerCardMachine pokerCardMachine = this.baccaratGame.getPokerCardMachine();
		PokerCard pokerCard = pokerCardMachine.pickOutOnePokerCard();
		BaccaratBanker baccaratBanker = this.baccaratGame.getBaccaratBanker();
		baccaratBanker.receiveOneCard(new PokerCardUsing(pokerCard, cardOutSequence));
	}
	
	
	
	public void displayWinResultAndPlayerBalance() {
		//BaccaratBanker baccaratBanker = this.baccaratGame.getBaccaratBanker();
		//System.out.println("银行家当前余额：" + baccaratBanker.getCardBalance());
		//只打印当前这一轮有下注的玩家
		for(Map.Entry<BaccaratPlayer, BetResultOfEachRound> entry : betResultMap.entrySet()) {
			//玩家在本轮有下注我才给你打印显示余额，否则不显示，你啥时候来完我就给你显示玩的那一轮时候的余额
			BaccaratPlayer baccaratPlayer = entry.getKey();
			if(baccaratPlayer.getCardNumber().equals(BaccaratPlayer.getSystemPlayer().getCardNumber())) {
				//系统玩家不打印结果。
				continue;
			}
			if(PrintOut.isSystemOutEnabled) {
				System.out.println("玩家["+ baccaratPlayer.getCardName() +"]当前余额：" + baccaratPlayer.getCardBalance());
			}
		}
		//不打印全部玩家
		//List<BaccaratPlayer> baccaratPlayerList = this.baccaratGame.getBaccaratPlayerList();
		//for(BaccaratPlayer baccaratPlayer : baccaratPlayerList) {
		//	if(betResultMap.containsKey(baccaratPlayer)) {
		//		System.out.println("玩家["+ baccaratPlayer.getCardName() +"]当前余额：" + baccaratPlayer.getCardBalance());
		//	}
		//}
		if(PrintOut.isSystemOutEnabled) {
			System.out.println();
		}
	}

	/**
	 * 清空本局的所有数据
	 */
	public void clearCurrentRoundCards() {
		//清空所有玩家桌面上的卡牌
		BaccaratBanker baccaratBanker = this.baccaratGame.getBaccaratBanker();
		baccaratBanker.getPokerCardBox().clear();
		for(Map.Entry<BaccaratPlayer, BetResultOfEachRound> entry : betResultMap.entrySet()) {
			BaccaratPlayer baccaratPlayer = entry.getKey();
			baccaratPlayer.getPokerCardBox().clear();
		}
		
		//清空本轮
		baccaratGame = null;
		betResultMap.clear();
		betResultMap = null;
	}

}

