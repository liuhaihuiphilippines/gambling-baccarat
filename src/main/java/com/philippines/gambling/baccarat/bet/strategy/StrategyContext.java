
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet.strategy;

import java.util.HashMap;
import java.util.Map;

import com.philippines.gambling.baccarat.bet.strategy.combine.CombineTwoTimesAndFourTimesSwitchStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.AnotherMachineStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.BetTieContinueslyAndStep6to9TimesWithBetAmountStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.BetTieOnlyWithBetAmountStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.FourTimesSwitchStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.OneTwoTwoStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.RandomStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.SwitchByPreviousResultStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.SwitchStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.SwitchUntilWinWithBetAmountStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.TenTimesSwitchStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.ThreeTimesSwitchStrategy;
import com.philippines.gambling.baccarat.bet.strategy.strategy.TwoTimesSwitchStrategy;
import com.philippines.gambling.baccarat.rule.BetResultEnum;

import cn.hutool.core.util.RandomUtil;

/**
 * @author winter
 * @date 2025年3月13日上午10:25:32
 */
public class StrategyContext {

	/**
	 * 诸葛亮的3个锦囊+特殊锦囊
	 */
	private static BetStrategy specificBetStrategy = null;
	private final static Map<Class<?>, BetStrategy> strategyPackageMap = new HashMap<Class<?>, BetStrategy>();
	static {
		strategyPackageMap.put(RandomStrategy.class, new RandomStrategy());//42%
		strategyPackageMap.put(SwitchStrategy.class, new SwitchStrategy());//40%
		strategyPackageMap.put(SwitchByPreviousResultStrategy.class, new SwitchByPreviousResultStrategy());//45.48%
		strategyPackageMap.put(OneTwoTwoStrategy.class, new OneTwoTwoStrategy());//43.14%
		strategyPackageMap.put(AnotherMachineStrategy.class, new AnotherMachineStrategy());
		strategyPackageMap.put(TwoTimesSwitchStrategy.class, new TwoTimesSwitchStrategy());//45.41%
		strategyPackageMap.put(ThreeTimesSwitchStrategy.class, new ThreeTimesSwitchStrategy());//45.35%
		strategyPackageMap.put(FourTimesSwitchStrategy.class, new FourTimesSwitchStrategy());//45.58%
		strategyPackageMap.put(CombineTwoTimesAndFourTimesSwitchStrategy.class, new CombineTwoTimesAndFourTimesSwitchStrategy());//45%
		strategyPackageMap.put(TenTimesSwitchStrategy.class, new TenTimesSwitchStrategy());//44.92%
		strategyPackageMap.put(SwitchUntilWinWithBetAmountStrategy.class, new SwitchUntilWinWithBetAmountStrategy());//44.92%
		strategyPackageMap.put(BetTieOnlyWithBetAmountStrategy.class, new BetTieOnlyWithBetAmountStrategy());//44.92%
		strategyPackageMap.put(BetTieContinueslyAndStep6to9TimesWithBetAmountStrategy.class, new BetTieContinueslyAndStep6to9TimesWithBetAmountStrategy());//44.92%
		setActiveStrategy(BetTieContinueslyAndStep6to9TimesWithBetAmountStrategy.class);
	}
	
    private static void setActiveStrategy(Class<? extends BetStrategy> betStrategyClass) {
    	specificBetStrategy = strategyPackageMap.get(betStrategyClass);
    }

	public static BetStrategy getActiveStrategy() {
    	return specificBetStrategy;
    }
    
	private StrategyContext() {
    }
	
    public static void add(BetStrategy strategy) {
    	strategyPackageMap.put(strategy.getClass(), strategy);
    }
    
    protected static BetStrategy specificBetStrategy(Class<? extends BetStrategy> strategyClass) {
        specificBetStrategy = strategyPackageMap.get(strategyClass);
        return specificBetStrategy;
    }

    @SuppressWarnings("unlikely-arg-type")
	public static BetResultEnum getBetWho() {
    	if(specificBetStrategy != null) {
    		return specificBetStrategy.getCurrentRoundBetWho().getBetWho();
    	}
    	int randomIndex = RandomUtil.randomInt(strategyPackageMap.size());
    	BetStrategy betStrategy = strategyPackageMap.get(randomIndex);
    	return betStrategy.getCurrentRoundBetWho().getBetWho();
    }
    
    
    public static void main(String[] args) {
    	
    }
}

