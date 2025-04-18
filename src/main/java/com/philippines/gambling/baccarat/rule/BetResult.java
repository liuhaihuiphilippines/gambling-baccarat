/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.rule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author winter
 * @date 6 Feb 2025 12:41:08 am
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BetResult {
	
	private BetResultEnum whoWin;

	private BetResultEnum betWho;
	
	/**
	 * 当前下注金额
	 */
	private int betAmount;
	
	/**
	 * 当前下注金额
	 */
	private int totalBalance;
}

