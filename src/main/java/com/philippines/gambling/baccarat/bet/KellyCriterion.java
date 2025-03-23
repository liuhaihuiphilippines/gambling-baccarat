
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.bet;
/**
 * 凯利公式（Kelly Criterion）是由美国数学家
 * John Larry Kelly Jr.于1965年提出的，
 * 用于确定在重复性投资中每次应投入的最佳资金比例。
 * 
 * 其基本表达式为：
 * 
 *     f* = (p * b - q) / b
 *     
 *     或
 *     
 *     f* = (p * (b + 1) - 1)/b
 *     
 * 其中：
 *    p是获胜的概率，
 *    q = 1 - p 是失败的概率，
 *    b是净赔率（即盈利与亏损的比例）（不含本金）
 *    f*表示现有资金应下注投入资金的比例，
 * 
 * 
 * 例如：
 * 如果获胜概率p = 0.6，失败概率q = 0.4，净赔率b = 2
 * 则应投入资金的比例为40%。
 * 
 * 若一赌博有60%的获胜率（p = 0.6，q = 0.4），
 * 而且赌客在赢得赌局时，可获得一赔一的赔率（b = 1），
 * 则赌客应在每次机会中下注现有资金的20%（f* = 0.2）]
 * 以最大化资金的长期增长率。 
 * 如果赔率没有优势，即 b < q / p，公式的结果是负的，那么公式建议不下注。 
 * 如果赔率是负的，即b < 0，也就是暗示应该下注到另外一边。
 * 
 * 
 * 凯利公式的目标是最大化资产的增长率，也即最大化对数资产的期望值。
 * 不失一般性，设开始时的资产是1，
 * 每次下注的比例为f∗ 
 * 有p的概率会以b的赔率赢钱，
 * 这时资产变为1+bf*。
 * 如果输了，资产就是1-f*。
 * 因此资产的对数的期望值为
 * E=plog(1+bf*)+(1-p)log(1-f*)
 * 要找到最大化这个期望值的f*
 * 只需令E对f*的导数值为零：
 * dE/df* = pb/1+bf* - 1-p/1-f*=0
 * 求解上述方程即得凯利公式f*=(pb+p-1)/b
 * 
 * 在凯利最初发表的论文中有着更一般而更严谨的证明[3]
 * https://web.archive.org/web/20190427080903/http://www.herrold.com/brokerage/kelly.pdf
 * 
 * @author winter
 * @date 20 Mar 20253:12:06 am
 */
public class KellyCriterion {

    /**
     * 计算凯利公式
     * @param winProbability 赢的概率
     * @param winOdds 赢的赔率
     * @return 最佳赌注比例
     */
    public static double calculateKelly(double winProbability, double winOdds) {
        if (winProbability <= 0 || winProbability >= 1 || winOdds <= 0) {
            throw new IllegalArgumentException("参数值不合法");
        }
        return (winProbability * (winOdds + 1) - 1) / winOdds;
    }

    public static void main(String[] args) {
        double winProbability = 0.4667; // 赢的概率
        double winOdds = 2.0; // 赢的赔率

        double kellyBet = calculateKelly(winProbability, winOdds);
        System.out.println("最佳赌注比例: " + kellyBet);
    }
}


