package com.philippines.gambling.baccarat.result;

import java.util.ArrayList;
import java.util.List;

import com.philippines.gambling.baccarat.constant.PrintOut;

public class SegmentRecorder {
	
	public static class TieToTie {
		
		/**
		 * 记录(tie,tie]左开右闭区间的链条数据
		 */
		private static final List<String> tietotieCollection = new ArrayList<String>();
		
		public static final void record(String collection) {
			if(!tietotieCollection.contains(collection)) {
				tietotieCollection.add(collection);
			}
		}

		public static void printAllCollection() {
			System.out.println(PrintOut.BLANK_PADDING_LEFT + "所有的牌面顺序集合如下：");
			for(String collection : tietotieCollection) {
				System.out.println(PrintOut.BLANK_PADDING_LEFT + collection);
			}
		}
	}
}
