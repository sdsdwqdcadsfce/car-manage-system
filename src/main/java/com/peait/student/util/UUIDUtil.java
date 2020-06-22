package com.peait.student.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String getSixNum() {
		String str = "0123456789";
		StringBuilder sb = new StringBuilder(4);
		for (int i = 0; i < 6; i++) {
			char ch = str.charAt(new Random().nextInt(str.length()));
			sb.append(ch);
		}
		long l = System.currentTimeMillis();

		String subTimeMill = String.valueOf(l).substring(String.valueOf(l).length()-6);
		return sb.toString()+subTimeMill;
	}
}
