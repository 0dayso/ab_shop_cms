package net.rytong.utils;

import java.util.Random;

public class RandomUtils {
	private static final RandomUtils randomUtil = new RandomUtils();

	private RandomUtils() {
	}

	/**
	 * 生成长度为length的字符串,字符串只包含数字
	 * @param length 字符串的长度
	 * @return
	 */
	public static String random(final int length) {
		return randomUtil.buildRandom(length);
	}
	
	/**
	 * 生成长度为length的字符串,字符串只包含数字
	 * @param length 字符串的长度
	 * @return
	 */
	public static String random(int length, int time, String split) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < time -1; i++) {
			sb.append(randomUtil.buildRandom(length)).append(split);
		}
		sb.append(randomUtil.buildRandom(length));
		return sb.toString();
	}

	/**
	 * 生成长度为length的字符串,字符串只包含数字与大写字母
	 * @param length 字符串的长度
	 * @return
	 */
	private String buildRandom(final int length) {
		Random random = new Random();
		StringBuffer sBuffer = new StringBuffer();
		for(int i = 0; i < length; i++) {     
			sBuffer.append((random.nextInt(2) % 2 == 0) ? random.nextInt(10) + "" : (char) (65 + random.nextInt(26)));     
		}
		return sBuffer.toString();
	}
}
