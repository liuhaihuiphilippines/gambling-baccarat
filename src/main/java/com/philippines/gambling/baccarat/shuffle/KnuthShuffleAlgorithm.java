
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.shuffle;

import java.util.Arrays;

import com.philippines.gambling.baccarat.card.PokerCard;
import com.philippines.gambling.baccarat.machine.PokerCardBox;

import cn.hutool.core.util.RandomUtil;

/**
 * Knuth洗牌算法
 * Collections.shuffle()就是使用的这种算法
 * @author winter
 * @date 20 Mar 20252:51:54 am
 */
public class KnuthShuffleAlgorithm {

	/**
	 * 用Knuth算法洗扑克牌
	 * @param pokerCardArray
	 * @return 
	 * @author: winter
	 * @date: 2025-03-20 10:23:56
	 */
	public static PokerCard[] shuffle8DeckOfPokerCards(PokerCard[] pokerCardArray) {
		int totalPiecesOfPokerCard = pokerCardArray.length;
		PokerCard[] shuffledpokerCardArray = Arrays.copyOf(pokerCardArray, totalPiecesOfPokerCard);
		//8副扑克牌一共416张牌最少洗2万遍，最多洗5万遍
		int currentCardShuffleTimes = RandomUtil.randomInt(20000, 50000);
		for(int loopTimes = 1; loopTimes <= currentCardShuffleTimes; loopTimes++) {
			//在所有牌中随机选取2张牌进行位置交换
			int swapLeftIndex = RandomUtil.randomInt(0, totalPiecesOfPokerCard - 1);
			int swapRightIndex = RandomUtil.randomInt(0, totalPiecesOfPokerCard - 1);
			PokerCard pokerCardTemp = shuffledpokerCardArray[swapLeftIndex];
			shuffledpokerCardArray[swapLeftIndex] = shuffledpokerCardArray[swapRightIndex];
			shuffledpokerCardArray[swapRightIndex] = pokerCardTemp;
		}
		return shuffledpokerCardArray;
	}

	public static void main(String[] args) {
		PokerCard[] shuffered = KnuthShuffleAlgorithm.shuffle8DeckOfPokerCards(
								PokerCardBox.create8DeckOfPokerCards());
		System.out.println(Arrays.toString(shuffered));
	}

}

