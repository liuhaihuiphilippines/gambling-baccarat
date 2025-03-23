/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.rule;

import lombok.Builder;
import lombok.Data;

/**
 * 每一轮的下注情况
 * @author winter
 * @date 6 Feb 2025 2:08:42 am
 */
@Data
@Builder
public class BetResultOfEachRound {

	/**
	 * 本轮下注谁赢
	 */
	private BetWinerEnum betWhoWin;
	
	/**
	 * 本轮下注金额
	 */
	private int betAmount;
}

