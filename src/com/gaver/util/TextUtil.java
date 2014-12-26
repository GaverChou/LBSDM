package com.gaver.util;

public class TextUtil {

	public static boolean isValid(String data) {
		if ("".equals(data) || data == null) {
			return false;
		}
		return true;
	}
}
