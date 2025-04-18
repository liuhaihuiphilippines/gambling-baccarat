
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.constant;
/**
 * @author winter
 * @date 2025年3月10日下午7:55:12
 */
public class PrintOut {

	/**
	 * 是否打印输出
	 */
	public static final boolean isSystemOutEnabled = false;
	public static final String PADDING_LEFT = "           ";
	public static final String BLANK = "                                          ";
	public static final String BLANK_PADDING_LEFT = BLANK + "      ";
	public static boolean isMachineAgainst = false;//是否为机器对抗
	public static long HOW_MANY_ROUND_WNAT_TO_PLAY = 1000L;//我想玩多少轮
	public static Integer HOW_MUCH_MONEY_IHAVE = 10 * 1000;//我带了多少钱去赌场
	public static Integer BET_MONEY_PER_ROUND = 200;//每轮下注多少钱
	public static Integer ROUND_COUNTER = 1;//一共跑了多少轮
	public static Integer ROUND_BET_COUNTER = 0;//总计有效下注轮数
	public static Integer ROUND_MAXIMUM_BALANCE = 0;//所有轮里面余额最大的时候是多少
	public static Integer ROUND_LEFT_BALANCE = 0;//离开赌场时的账户余额
	public static int cardBoxInitTime = 1;//卡盒纸牌用完的盒子数量
}

