
/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
 */
package com.philippines.gambling.baccarat.shuffle;

import java.util.Arrays;

/**
 * Knuth洗牌算法
 * Collections.shuffle()就是使用的这种算法
 * @author winter
 * @date 20 Mar 20252:51:54 am
 */
public class KnuthShuffleSimpleAlgorithm {

	
	/**
	 * 洗牌
	 */
	public void shuffle(int[] nums) {
		for (int i = nums.length - 1; i >= 0; i--) {
			swap(nums, i, (int) (Math.random() * (i + 1)));
		}
	}

	private void swap(int[] nums, int left, int right) {
		int temp = nums[left];
		nums[left] = nums[right];
		nums[right] = temp;
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		new KnuthShuffleSimpleAlgorithm().shuffle(nums);
		System.out.println(Arrays.toString(nums));
	}

}

