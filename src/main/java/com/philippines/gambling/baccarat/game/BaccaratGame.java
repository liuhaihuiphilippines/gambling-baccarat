/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.game;

import java.util.ArrayList;
import java.util.List;

import com.philippines.gambling.baccarat.constant.PrintOut;
import com.philippines.gambling.baccarat.machine.PokerCardMachine;
import com.philippines.gambling.baccarat.role.BaccaratBanker;
import com.philippines.gambling.baccarat.role.BaccaratCasinoGirl;
import com.philippines.gambling.baccarat.role.BaccaratPlayer;
import com.philippines.gambling.baccarat.rule.BaccartRound;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author winter
 * @date 6 Feb 2025 12:05:04 am
 */
@Data
@NoArgsConstructor
public class BaccaratGame {

	protected BaccaratCasinoGirl baccaratCasinoGirl;
	protected BaccaratBanker baccaratBanker;
	protected List<BaccaratPlayer> baccaratPlayerList;
	protected PokerCardMachine pokerCardMachine;

	/**
	 * 开始这个游戏
	 * @author winter
	 */
	public void start() {
		long roundNo = 0L;
		while(true) {
			new BaccartRound(this, ++roundNo).play();
		}
	}
	
	/**
	 * 初始化一个游戏
	 * @author winter
	 * @param baccaratGame
	 */
	public static BaccaratGame init() {
		BaccaratGame baccaratGame = new BaccaratGame();
		BaccaratCasinoGirl baccaratCasinoGirl = BaccaratCasinoGirl.builder()
				.casinoGirlName("ALICE").casinoTableName("TABLE_LUCKY")
				.workingTimeStart("10:00pm").workingTimeEnd("06:00am").build();
		baccaratGame.setBaccaratCasinoGirl(baccaratCasinoGirl);
		//初始化一个系统银行家
		baccaratGame.setBaccaratBanker(BaccaratBanker.getSystemBanker());
				List<BaccaratPlayer> baccaratPlayerList = new ArrayList<BaccaratPlayer>();
				//初始化一个系统玩家
				baccaratPlayerList.add(0, BaccaratPlayer.getSystemPlayer());
				//其他真实玩家
				baccaratPlayerList.add(new BaccaratPlayer("Wesley", "20250206003028668", 10000));
		baccaratGame.setBaccaratPlayerList(baccaratPlayerList);
				PokerCardMachine pokerCardMachine = new PokerCardMachine();
				pokerCardMachine.init();
		baccaratGame.setPokerCardMachine(pokerCardMachine);
		if(PrintOut.isSystemOutEnabled) {
			for(BaccaratPlayer baccaratPlayer : baccaratPlayerList) {
				System.out.println("玩家["+ baccaratPlayer.getCardName() +"]带着钱进来玩了,金额：" + baccaratPlayer.getCardBalance());
			}
			System.out.println("\n");
		}
		return baccaratGame;
	}
}

