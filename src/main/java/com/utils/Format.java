package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Format {
	public static int exctractNumberFrom(String input) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(input);
		int value=-1;
		while (m.find()) {
			value=Integer.parseInt(m.group());
			
		}
		return value;
		
		
	}
}
