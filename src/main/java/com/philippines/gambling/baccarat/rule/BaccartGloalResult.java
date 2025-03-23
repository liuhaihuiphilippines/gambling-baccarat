/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author winter
 * @date 7 Feb 20253:37:12 am
 */
public class BaccartGloalResult {

	/**
	 * 记录全局出现的赢的清空
	 */
	private static List<Character> winResult = new ArrayList<Character>();
	
	public static void addWinResult(Character c) {
		winResult.add(c);
	}
	
	public static void printSelf() {
		char[] winResultChar = new char[winResult.size()];
		int index = 0;
		for(Character c : winResult) {
			winResultChar[index++] = c.charValue();
		}
		String winResult = Arrays.toString(winResultChar);
		System.out.println("本次的所有序列为：\n" + winResult);
	}
}

