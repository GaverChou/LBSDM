package com.gaver.util;

public class TextUtil {

	public static boolean textValid(String data) {
		if ("".equals(data) || data == null) {
			return false;
		}
		return true;
	}
}
