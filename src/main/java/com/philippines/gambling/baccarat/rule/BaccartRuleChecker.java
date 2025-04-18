
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.rule;

import java.util.Map;

import com.philippines.gambling.baccarat.machine.PokerCardMachine;
import com.philippines.gambling.baccarat.role.BaccaratBanker;
import com.philippines.gambling.baccarat.role.BaccaratPlayer;

/**
 * @author winter
 * @date 2025年3月10日下午3:13:56
 */
public class BaccartRuleChecker extends BaccartCalculator {
	
	/**
	 * 检查是否需要继续发放第三张牌
	 * 什么情况下会发第三张牌？
	 * 不论庄家或者闲家
	 * 如果任何一方的总点数没有达到8或9点，发牌官会根据点数加牌于庄家和/或闲家手牌。
	 * 如果最初两张牌牌面的总点数为8或9点而赢牌，这种玩法叫做例牌赢法。
	 * 此时游戏结束，并对玩家进行赔付。
	 * @author winter
	 * @return
	 */
	protected boolean checkIsNeedTheThirdCard() {
		int pokerCardBoxDigitSumBanker = BaccaratBanker.getSystemBanker().getPokerCardBoxDigitSum();
		int pokerCardBoxDigitSumPlayer = BaccaratPlayer.getSystemPlayer().getPokerCardBoxDigitSum();
		if(pokerCardBoxDigitSumBanker == 8 || pokerCardBoxDigitSumBanker == 9 ||
		   pokerCardBoxDigitSumPlayer == 8 || pokerCardBoxDigitSumPlayer == 9) {
			return false;
		}
		return true;
	}

	
	/**
	 * 校验所有玩家的下注金额是否在允许范围内
	 */
	protected void checkIsBetAmountExcceededRange() {
		for(Map.Entry<BaccaratPlayer, BetResultOfEachRound> entry : getBetResultMap().entrySet()) {
			BetResultOfEachRound betResult = entry.getValue();
			if(betResult.getBetAmount() > 40000 || betResult.getBetAmount() < 200) {
				throw new RuntimeException("玩家下注异常！");
			}
		}
	}
	
	/**
	 * 检查发卡机器里面的卡是否够一轮的6张牌
	 * 如果不够的话就初始化后游戏继续进行
	 */
	protected void checkAndSetCardsToEnoughForCurrentRound() {
		PokerCardMachine pokerCardMachine = getBaccaratGame().getPokerCardMachine();
		int pokerCardCountsRemaining = pokerCardMachine.getPokerCardCountsRemaining();
		if(pokerCardCountsRemaining < 6) {
			pokerCardMachine.init();
		}
	}
	
	/**
	 * 检查是否需要多给玩家多发一张牌
		https://www.playsmart.ca/table-games/baccarat/how-to-play/?lang=zh-hans
		
		闲家补第三张牌的规则
		在百家乐的规则里，永远都是闲家优先。庄家手牌会取决于闲家手牌。
		下面就是具体规则：
		如果闲家总点数为8或9点，那就是例牌赢法，不补牌。闲家赢。
		在闲家总点数为6或7点的时候，不补牌。
		在闲家总点数为0至5任意点数时，只要庄家总点数不为8或9点，则闲家补一张。如果庄家在此时点数为8或9点，则庄家赢，闲家不补牌。
		
		https://www.ky-sport.com/baccarat/
		在百家乐中，牌面总和9点为最大，称为天牌，其次为8点，称为王牌，
		当庄家或闲家任意一方出现8点或9点的牌面总和，即不需再进行补牌程序，直接定输赢
		而当出现总和为7点时同样也不需补牌，
		
		此外，倘若任意一方点数总和为0、1、2点时，根据规则必须强迫补牌，
		而扣除掉以上几种浅显易懂的开牌结果后，其余点数总和的补牌规则会有些不同，
		我们就以文字叙述之方式来说明，并以庄家来做为基准点，
		排除点数总和(0 、1、2)三种情况之后，
		我们分类为以下5种百家乐补牌的状况。
		
		（1）百家乐补牌规则一 – 庄家牌面总和为3点时
		当庄家牌面总和为3点时，闲家必须补牌，若闲家第三张牌补到8点，庄家则不需再补牌，其他点数呈现，庄家都必须在补第三张牌。
		（2）百家乐补牌规则二 – 庄家牌面总和为4点时
		当庄家牌面总和为4点时，闲家必须补牌，若闲家第三张牌补到0、1、8、9点，庄家则不需补牌，其他点数呈现，庄家必须补牌。
		（3）百家乐补牌规则三 – 庄家牌面总和为5点时
		当庄家牌面总和为5点时，闲家必须补牌，若闲家第三张牌补到0、1、2、3、8、9点，庄家则不需补牌，其他点数呈现，庄家必须补牌。
		（4）百家乐补牌规则四 – 庄家牌面总和为6点时
		当庄家牌面总和为6点时，闲家必须补牌，若闲家第三张牌补到6、7点，庄家必须补牌，其他点数呈现，庄家不需补牌，但在这里的前提是，闲家的点数总和必须是5点以下。
		（5）百家乐补牌规则五 – 庄家和闲家的牌面总和呈现为6点、7点
		当庄家和闲家的牌面总和呈现为6点和7点，将不进行补牌，即定输赢，
		以举例的方式；若庄为6点，闲为7点，则闲赢；
		若庄为7点，闲为6点，则庄赢；若两方皆为6点和7点，则以和局收场。
	 */
	protected boolean checkIsPlayerNeedOneMoreCard() {
		//List<BaccaratPlayer> baccaratPlayerList = getBaccaratGame().getBaccaratPlayerList();
		//BaccaratPlayer baccaratPlayer = baccaratPlayerList.get(0);
		//if(baccaratPlayer.getPokerCardBoxDigitSum() < 6) {
		//	return true;
		//}
		//return false;
		BaccaratPlayer baccaratPlayer = getBaccaratGame().getBaccaratPlayerList().get(0);
		BaccaratBanker baccaratBanker = getBaccaratGame().getBaccaratBanker();
		BetResultEnum twotwoCompareResult = twotwoCompare(baccaratPlayer.getPokerCardBox(), baccaratBanker.getPokerCardBox());
		return twotwoCompareResult == BetResultEnum.PLAYER_NEED_ONMORECARD;
	}
	
	/**
	 * 检查是否需要多给庄家多发一张牌

		https://www.playsmart.ca/table-games/baccarat/how-to-play/?lang=zh-hans
		
		庄家补第三张牌的规则：
		0，1或2点：除非闲家得到总计8或9点的例牌，庄家补牌。
		3点: 如果闲家补的第三张牌为0，1，2，3，4，5，6，7或9点，则庄家补牌。当闲家补牌为8点时，庄家不补牌。
		4点：当闲家补的第三张牌为2，3，4，5，6或7点时，庄家补牌。当闲家补牌为0，1，8或9点时，庄家不补牌。
		5点：当闲家补的第三张牌为4，5，6或7点时，庄家补牌。闲家补牌为0，1，2，3，8或9点时，庄家不补牌。
		6点：当闲家补的第三张牌为6或7点时，庄家补牌。当闲家补的牌为0，1，2，3，4，5，8或9点时，庄家不补牌。
		7点: 双方均不补牌。
		8或9点：例牌数字任何一方都不补第三张牌。
	 */
	protected boolean checkIsBankerNeedOneMoreCard() {
		//BaccaratBanker baccaratBanker = getBaccaratGame().getBaccaratBanker();
		//if(baccaratBanker.getPokerCardBoxDigitSum() < 6) {
		//	return true;
		//}
		//return false;
		BaccaratPlayer baccaratPlayer = getBaccaratGame().getBaccaratPlayerList().get(0);
		BaccaratBanker baccaratBanker = getBaccaratGame().getBaccaratBanker();
		BetResultEnum twotwoCompareResult = twotwoCompare(baccaratPlayer.getPokerCardBox(), baccaratBanker.getPokerCardBox());
		return twotwoCompareResult == BetResultEnum.BANKER_NEED_ONMORECARD;
	}

}

