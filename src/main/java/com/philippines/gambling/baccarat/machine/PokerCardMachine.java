/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.machine;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

import com.philippines.gambling.baccarat.card.PokerCard;
import com.philippines.gambling.baccarat.constant.PrintOut;
import com.philippines.gambling.baccarat.shuffle.KnuthShuffleAlgorithm;

import lombok.Data;

/**
 * @author winter
 * @date 6 Feb 2025 12:44:53 am
 */
@Data
public class PokerCardMachine {
	
	private String cardMachineName;
	private ArrayBlockingQueue<PokerCard> cardQueue;
	
	public PokerCardMachine init() {
		/**
		 * 生成8副扑克牌
		 */
		PokerCard[] _8DeckOfPokerCards = PokerCardBox.create8DeckOfPokerCards();
		
		/**
		 * 洗牌把这8副都洗乱
		 */
		PokerCard[] shuffled8DeckOfPokerCards = KnuthShuffleAlgorithm.shuffle8DeckOfPokerCards(_8DeckOfPokerCards);
		
		/**
		 * 放入扑克派机器的盒子里面
		 */
		put8DeckShuffledPokerCardsIntoMachineBox(shuffled8DeckOfPokerCards);
		return this;
	}


	private void put8DeckShuffledPokerCardsIntoMachineBox(PokerCard[] shuffledpokerCardArray) {
		cardQueue = new ArrayBlockingQueue<PokerCard>(shuffledpokerCardArray.length);
		cardQueue.addAll(Arrays.asList(shuffledpokerCardArray));
	}
	
	public int getPokerCardCountsRemaining() {
		return cardQueue.size();
	}
	
	public PokerCard pickOutOnePokerCard() {
		PokerCard pokerCard = cardQueue.poll();
		if(pokerCard == null) {
			System.err.println(PrintOut.BLANK + PrintOut.PADDING_LEFT + "卡用完了，重新初始化!");
			init();
			pokerCard = cardQueue.poll();
		}
		return pokerCard;
	}
	
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> cardQueue = new ArrayBlockingQueue<Integer>(1000);
		cardQueue.add(new Integer(2));
		cardQueue.add(new Integer(3));
		cardQueue.add(new Integer(4));
		System.out.println(cardQueue.size());
		System.out.println(cardQueue.remainingCapacity());
	}
}

