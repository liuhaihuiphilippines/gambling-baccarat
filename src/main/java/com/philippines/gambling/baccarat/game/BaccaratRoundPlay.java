/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.game;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.philippines.gambling.baccarat.bet.strategy.BetStrategy;
import com.philippines.gambling.baccarat.bet.strategy.StrategyContext;
import com.philippines.gambling.baccarat.card.PokerCardUsing;
import com.philippines.gambling.baccarat.constant.PrintOut;
import com.philippines.gambling.baccarat.machine.PokerCardMachine;
import com.philippines.gambling.baccarat.rule.BaccartCalculator;
import com.philippines.gambling.baccarat.rule.BetResult;
import com.philippines.gambling.baccarat.rule.BetWinerEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author winter
 * @date 6 Feb 2025 12:05:04 am
 */
@Data
@NoArgsConstructor
public class BaccaratRoundPlay {

	
	private static long TOTAL_ROUND = 10000L;
	public static void playAllRound(BaccartCalculator baccartCalculator, PokerCardMachine pokerCardMachine) {
		playAllRound(baccartCalculator, pokerCardMachine, TOTAL_ROUND);
	}
	public static void playAllRound(BaccartCalculator baccartCalculator, PokerCardMachine pokerCardMachine, long totalRound) {
		System.out.println("totalRound:" + totalRound);
		long TIE_COUNT = 0L;
		long BANKER_WIN_COUNT = 0L;
		long PLAYER_WIN_COUNT = 0L;
		long BET_WIN_COUNT = 0L;
		//long totalBetCount = 0L;
		TOTAL_ROUND = totalRound;
		//BetWinerEnum onRound = BetWinerEnum.BANKER_WIN;
		//BetWinerEnum previousRound = null;
		BetResult previousRoundBetResult = null;
		BetStrategy specificBetStrategy = StrategyContext.getActiveStrategy();
		for(int round = 1; round < totalRound; round++) {
			//获取当前这轮的下注，该下注谁，下注多少金额
			BetResult currentRoundBetWho = specificBetStrategy.getCurrentRoundBetWho(previousRoundBetResult);
			{
				PrintOut.ROUND_COUNTER++;
				//BetWinerEnum betWho = specificBetStrategy.getBetWho(previousRound);
				//System.out.println(JSONUtil.toJsonStr(betWho));
				boolean canIContinueToPlay = true;
				if(PrintOut.ROUND_COUNTER == totalRound - 1) {
					System.err.println(PrintOut.BLANK_PADDING_LEFT 
							+ "达到最大下注数量了，你不能继续玩了！" 
							+ "(总计下注了:"+ PrintOut.ROUND_BET_COUNTER + "轮)");
					canIContinueToPlay = false;
					PrintOut.ROUND_LEFT_BALANCE = currentRoundBetWho.getTotalBalance();
				}
				if(currentRoundBetWho.getTotalBalance() < currentRoundBetWho.getBetAmount()) {
					System.err.println(PrintOut.BLANK_PADDING_LEFT 
							+ "你已经没钱了，玩不了了！" 
							+ "(总计下注了:"+ PrintOut.ROUND_BET_COUNTER + "轮)");
					canIContinueToPlay = false;
					PrintOut.ROUND_LEFT_BALANCE = currentRoundBetWho.getTotalBalance();
				}
				if(!canIContinueToPlay) {
					System.err.println(PrintOut.BLANK_PADDING_LEFT 
							+ "你一共带了这么多钱去玩：" + PrintOut.HOW_MUCH_MONEY_IHAVE);
					System.err.println(PrintOut.BLANK_PADDING_LEFT 
							+ "下注期间你有过的最大余额是：" + PrintOut.ROUND_MAXIMUM_BALANCE);
					System.err.println(PrintOut.BLANK_PADDING_LEFT 
							+ "离开赌场的时候剩余的余额是：" + PrintOut.ROUND_LEFT_BALANCE);
					break;
				}
				
				if(currentRoundBetWho.getBetWho() != null) {
					//totalBetCount++;
					PrintOut.ROUND_BET_COUNTER++;
				}
			}
			
			//开始下注
			//System.out.println("上一局的结果是:" + onRound.name() + "所以这一局我下注:" + betWho.name());
			//try {Thread.sleep(20);} catch (InterruptedException e) {}
			BetResult currentRoundBetResult = playOneRound(pokerCardMachine, baccartCalculator, currentRoundBetWho);
			
			
			//统计计算结果
			{
				calculateTieCount(currentRoundBetResult);
				if(currentRoundBetResult.getWhoWin() == BetWinerEnum.TIE) {
					TIE_COUNT++;
				}else if(currentRoundBetResult.getWhoWin() == BetWinerEnum.BANKER_WIN) {
					BANKER_WIN_COUNT++;
				}else if(currentRoundBetResult.getWhoWin() == BetWinerEnum.PLAYER_WIN) {
					PLAYER_WIN_COUNT++;
				}
				if(currentRoundBetWho.getBetWho() == currentRoundBetResult.getWhoWin()) {
					BET_WIN_COUNT++;
				}
			}
			
			//赋值给历史做记录
			previousRoundBetResult = currentRoundBetResult;
		}
		if(tieIndex >= 2) {
			//计算剩余最后一轮为计算的SWITCH情况
			if(TIE_ROUND_THREE[1] != TIE_ROUND_THREE[2]) {
				//表示发生了switch
				TIE_SWITCH_COUNT++;
			}
		}
		try {Thread.sleep(100);} catch (InterruptedException e) {}
		System.out.println();
		System.out.println(PrintOut.BLANK + "=============================================");
		System.out.println();
		if(PrintOut.ROUND_COUNTER == 0) { PrintOut.ROUND_COUNTER = 1; }
		if(PrintOut.ROUND_BET_COUNTER == 0) { PrintOut.ROUND_BET_COUNTER = 1; }
		Double RATIO = new BigDecimal(TIE_COUNT * 100).divide(new BigDecimal(PrintOut.ROUND_COUNTER), 3,RoundingMode.HALF_UP).doubleValue();
		System.err.println(PrintOut.BLANK_PADDING_LEFT + "TIE_COUNT:" + TIE_COUNT + "/"+ PrintOut.ROUND_BET_COUNTER + "(" + RATIO + "%)");
		RATIO = new BigDecimal(TIE_SWITCH_COUNT * 100).divide(new BigDecimal(PrintOut.ROUND_BET_COUNTER), 3,RoundingMode.HALF_UP).doubleValue();
		System.err.println(PrintOut.BLANK_PADDING_LEFT + "TIE_SWITCH_COUNT:" + TIE_SWITCH_COUNT + "/"+ PrintOut.ROUND_BET_COUNTER + "(" + RATIO + "%)");
		RATIO = new BigDecimal(BANKER_WIN_COUNT * 100).divide(new BigDecimal(PrintOut.ROUND_BET_COUNTER), 3,RoundingMode.HALF_UP).doubleValue();
		System.err.println(PrintOut.BLANK_PADDING_LEFT + "BANKER_WIN_COUNT:" + BANKER_WIN_COUNT + "/"+ PrintOut.ROUND_BET_COUNTER + "(" + RATIO + "%)");
		RATIO = new BigDecimal(PLAYER_WIN_COUNT * 100).divide(new BigDecimal(PrintOut.ROUND_BET_COUNTER), 3,RoundingMode.HALF_UP).doubleValue();
		System.err.println(PrintOut.BLANK_PADDING_LEFT + "PLAYER_WIN_COUNT:" + PLAYER_WIN_COUNT + "/"+ PrintOut.ROUND_BET_COUNTER + "(" + RATIO + "%)");
		System.out.println();
		//if(totalBetCount == 0) { totalBetCount = 1; }
		RATIO = new BigDecimal(BET_WIN_COUNT * 100).divide(new BigDecimal(PrintOut.ROUND_BET_COUNTER), 3,RoundingMode.HALF_UP).doubleValue();
		System.err.println(PrintOut.BLANK_PADDING_LEFT + "BET_WIN_COUNT:" + BET_WIN_COUNT + "/"+ PrintOut.ROUND_BET_COUNTER + "(" + RATIO + "%)");
	}
	public static BetResult playOneRound(PokerCardMachine pokerCardMachine, BaccartCalculator baccartCalculator) {
		return playOneRound(pokerCardMachine, baccartCalculator, null);
	}
	//protected static BetWinerEnum playOneRound(PokerCardMachine pokerCardMachine, BaccartCalculator baccartCalculator, BetWinerEnum betWho) {
	protected static BetResult playOneRound(PokerCardMachine pokerCardMachine, BaccartCalculator baccartCalculator, BetResult currentRoundBetWho) {
		
		//先扣钱
		if(currentRoundBetWho != null && currentRoundBetWho.getBetWho() != null) {
			currentRoundBetWho.setTotalBalance(currentRoundBetWho.getTotalBalance() - currentRoundBetWho.getBetAmount());
		}
		
		//开始计算发牌
		List<PokerCardUsing> pokerCardBoxPlayer = new ArrayList<PokerCardUsing>();
		List<PokerCardUsing> pokerCardBoxBanker = new ArrayList<PokerCardUsing>();
		pokerCardBoxPlayer.add(new PokerCardUsing(pokerCardMachine.pickOutOnePokerCard(), (short)1));
		pokerCardBoxBanker.add(new PokerCardUsing(pokerCardMachine.pickOutOnePokerCard(), (short)2));
		pokerCardBoxPlayer.add(new PokerCardUsing(pokerCardMachine.pickOutOnePokerCard(), (short)3));
		pokerCardBoxBanker.add(new PokerCardUsing(pokerCardMachine.pickOutOnePokerCard(), (short)4));
		BetWinerEnum twotwoCompareResult = baccartCalculator.twotwoCompare(pokerCardBoxPlayer, pokerCardBoxBanker);
		if(twotwoCompareResult == BetWinerEnum.PLAYER_NEED_ONMORECARD) {
			pokerCardBoxPlayer.add(new PokerCardUsing(pokerCardMachine.pickOutOnePokerCard(), (short)5));
			twotwoCompareResult = baccartCalculator.twotwoCompare(pokerCardBoxPlayer, pokerCardBoxBanker);
		}
		if(twotwoCompareResult == BetWinerEnum.BANKER_NEED_ONMORECARD) {
			pokerCardBoxBanker.add(new PokerCardUsing(pokerCardMachine.pickOutOnePokerCard(), (short)6));
			twotwoCompareResult = baccartCalculator.twotwoCompare(pokerCardBoxPlayer, pokerCardBoxBanker);
		}
		String playerCardDisplay = getDisplayName(pokerCardBoxPlayer);
		String bankerCardDisplay = getDisplayName(pokerCardBoxBanker);
		//System.out.println(String.format("PLAYER:%-20s  BANKER:%-20s = %-10s", 
		//		playerCardDisplay, bankerCardDisplay, twotwoCompareResult.name()));
		
		//判断结果，并扣掉下一次
		//没有下注
		if(currentRoundBetWho == null || currentRoundBetWho.getBetWho() == null) {
			if(!PrintOut.isMachineAgainst) {
				System.out.println(String.format(PrintOut.BLANK + "%10s | %-10s = %-10s | (NO BET,BALANCE|%d)", 
					playerCardDisplay, bankerCardDisplay, twotwoCompareResult.name(), currentRoundBetWho.getTotalBalance()));
			}
		}else {
			//有下注
			if(currentRoundBetWho.getBetWho() == twotwoCompareResult) {
				//赢了|返回本金 + 赢得的奖金=（赔率 * 本金）
				int benJin = currentRoundBetWho.getBetAmount();
				int winAmount = twotwoCompareResult.getOdds() * benJin;
				int currentRoundWinAmount = benJin + winAmount;
				currentRoundBetWho.setTotalBalance(currentRoundBetWho.getTotalBalance() + currentRoundWinAmount);
				
				System.out.println(String.format(PrintOut.BLANK + "%10s | %-10s = %-10s | (BET|-%d,WIN,BALANCE|%d) %-10s (HIT)", 
						playerCardDisplay, bankerCardDisplay, twotwoCompareResult.name(), currentRoundBetWho.getBetAmount(), currentRoundBetWho.getTotalBalance(), currentRoundBetWho.getBetWho().name()));
			}else {
				System.out.println(String.format(PrintOut.BLANK + "%10s | %-10s = %-10s | (BET|-%d,BALANCE|%d) %-10s", 
						playerCardDisplay, bankerCardDisplay, twotwoCompareResult.name(), currentRoundBetWho.getBetAmount(), currentRoundBetWho.getTotalBalance(), currentRoundBetWho.getBetWho().name()));
				
			}
		}
		//记录最大余额的时候有多少钱
		if(currentRoundBetWho.getTotalBalance() > PrintOut.ROUND_MAXIMUM_BALANCE) {
			PrintOut.ROUND_MAXIMUM_BALANCE = currentRoundBetWho.getTotalBalance();
		}
		BetResult currentRoundBetResult = BetResult.builder()
				.betWho(currentRoundBetWho.getBetWho())
				.whoWin(twotwoCompareResult)
				.betAmount(currentRoundBetWho.getBetAmount())
				.totalBalance(currentRoundBetWho.getTotalBalance()).build();
		return currentRoundBetResult;
	}
	protected static String getDisplayName(List<PokerCardUsing> pokerCardBoxList) {
		String pokerName1 = pokerCardBoxList.get(0).getCardName();
		int pokerDigit1 = pokerCardBoxList.get(0).getCardDigit();
		String pokerName2 = pokerCardBoxList.get(1).getCardName();
		int pokerDigit2 = pokerCardBoxList.get(1).getCardDigit();
		StringBuffer strBufDigit = new StringBuffer(pokerDigit1 + "," + pokerDigit2);
		if(pokerCardBoxList.size() == 3) {
			int pokerDigitSum = (pokerDigit1 + pokerDigit2) % 10;
			String pokerName3 = pokerCardBoxList.get(2).getCardName();
			//int pokerDigit3 = pokerCardBoxList.get(2).getCardDigit();
			strBufDigit = new StringBuffer("(" + pokerName1 + "," + pokerName2 + ")" + pokerDigitSum + "," + pokerName3);
		}
		return strBufDigit.toString();
	}
	
	protected static String getDisplayDigit(List<PokerCardUsing> pokerCardBoxList) {
		int pokerDigit1 = pokerCardBoxList.get(0).getCardDigit();
		int pokerDigit2 = pokerCardBoxList.get(1).getCardDigit();
		StringBuffer strBufDigit = new StringBuffer(pokerDigit1 + "," + pokerDigit2);
		if(pokerCardBoxList.size() == 3) {
			int pokerDigitSum = (pokerDigit1 + pokerDigit2) % 10;
			int pokerDigit3 = pokerCardBoxList.get(2).getCardDigit();
			strBufDigit = new StringBuffer("(" + pokerDigit1 + "," + pokerDigit2 + ")" + pokerDigitSum + "," + pokerDigit3);
		}
		return strBufDigit.toString();
	}
	/**
	 * 下注策略
	 * @return 
	 * @author: winter
	 * @date: 2025-03-11 11:45:09
	 */
	
	private static int tieIndex = -1;
	private static long TIE_SWITCH_COUNT = 0L;
	private static BetWinerEnum[] TIE_ROUND_THREE = new BetWinerEnum[3];
	//private static void calculateTieCount(BetWinerEnum onRound) {
	private static void calculateTieCount(BetResult previousRoundBetResult) {
		if(previousRoundBetResult.getWhoWin() == BetWinerEnum.TIE) {
			//计算上一轮的SWITCH情况
			if(TIE_ROUND_THREE[1] != TIE_ROUND_THREE[2]) {
				//表示发生了switch
				TIE_SWITCH_COUNT++;
			}
			tieIndex = 0;
			TIE_ROUND_THREE = new BetWinerEnum[3];
		}
		if(tieIndex >= 0 && tieIndex <= 2) {
			TIE_ROUND_THREE[tieIndex++] = previousRoundBetResult.getWhoWin();
		}
	}
}

