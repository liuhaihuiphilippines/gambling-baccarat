/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.role;

import java.util.ArrayList;
import java.util.List;

import com.philippines.gambling.baccarat.card.PokerCardUsing;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 百家乐游戏的参与者
 * @author winter
 * @date 6 Feb 202511:59:32 pm
 */
@Data
@NoArgsConstructor
public abstract class BaccaratParticipant {
	
	/**
	 * 卡主名字
	 */
	private String cardName;
	
	/**
	 * 卡号
	 */
	private String cardNumber;
	
	/**
	 * 卡的余额
	 */
	private int cardBalance;
	
	/**
	 * 卡的等级
	 */
	private String cardLevel = "白银";
	
	/**
	 * 用来装扑克牌的盒子
	 */
	List<PokerCardUsing> pokerCardBox = new ArrayList<PokerCardUsing>(3);
	
	/**
	 * 扑克牌的盒子pokerCardBox里面的所有卡片数字牌面
	 */
	private String cardNames;
	private String cardNamesForShort;
	
	/**
	 * 扑克牌的盒子pokerCardBox里面的所有卡片数字和
	 */
	private int cardDigitSum;
	
	
	/**
	 * 父类构造方法
	 * @param cardName
	 * @param cardNumber
	 * @param cardBalance
	 */
	public BaccaratParticipant(String cardName, String cardNumber, int cardBalance) {
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.cardBalance = cardBalance;
	}
	
	/**
	 * 参与者接收系统发过来的牌(一次接收1张牌)
	 * @author winter
	 * @param pokerCard
	 */
	public void receiveOneCard(PokerCardUsing pokerCardUsing) {
		pokerCardBox.add(pokerCardUsing);
	}
	
	/**
	 * 计算扑克牌盒子里面的点数
	 * @author winter
	 * @return
	 */
	public int getPokerCardBoxDigitSum() {
		int cardDigitSum = 0;
		StringBuffer cardNames = new StringBuffer();
		StringBuffer cardNamesForShort = new StringBuffer();
		for(PokerCardUsing pokerCardUsing : pokerCardBox) {
			cardNames.append(",")
					 .append(pokerCardUsing.getFigureName())
					 .append(pokerCardUsing.getCardName())
					 .append("|").append(pokerCardUsing.getCardOutSequence());
			cardNamesForShort.append(",").append(pokerCardUsing.getCardName());
			cardDigitSum = (cardDigitSum + pokerCardUsing.getCardDigit()) % 10;
		}
		this.cardNames = cardNames.substring(1);
		this.cardNamesForShort = cardNamesForShort.substring(1);
		this.cardDigitSum = cardDigitSum;
		return cardDigitSum;
	}
	

	/**
	 * 给参与者(银行家或玩家)的cardBalance加钱或者扣钱
	 * @author winter
	 * @param winAmount
	 *    当为负数时候就扣余额里面的钱
	 *    当为正数时候就给余额里面加钱
	 */
	public void addBalance(int winAmount) {
		/**
		 * 主要是设置Banker的时候老板太有钱了
		 */
		int afterAmount = this.getCardBalance() + winAmount;
		this.setCardBalance(afterAmount < 0 ? Integer.MAX_VALUE : afterAmount);
	}
	
	/**
	 * 打印出本轮所有的牌面和数字和
	 * @author winter
	 * @return
	 */
	public String displayCardBoxResult() {
		//return "[" + this.cardNames + "](" + this.cardDigitSum + ")";
		return "[" + this.cardNamesForShort + "](" + this.cardDigitSum + ")";
	}
	
	public static void main(String[] args) {
		int amount = Integer.MAX_VALUE;
		int afterAmount = amount + 2000;
		System.out.println(afterAmount);
		boolean isTrue = afterAmount > Integer.MAX_VALUE;
		System.out.println(isTrue);
	}
}

