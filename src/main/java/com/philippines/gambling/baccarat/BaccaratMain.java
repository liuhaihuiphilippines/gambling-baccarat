
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat;

import com.philippines.gambling.baccarat.constant.PrintOut;
import com.philippines.gambling.baccarat.game.BaccaratRoundPlay;
import com.philippines.gambling.baccarat.machine.PokerCardMachine;
import com.philippines.gambling.baccarat.rule.BaccartCalculator;

/**
 * @author winter
 * @date 2025年3月13日上午10:53:43
 */
public class BaccaratMain {

	public static void main(String[] args) {
		//BaccaratGame.init().start();
		BaccaratRoundPlay.playAllRound(
				new BaccartCalculator(), 
				new PokerCardMachine().init(), 
				PrintOut.HOW_MANY_ROUND_WNAT_TO_PLAY);
	}
}

