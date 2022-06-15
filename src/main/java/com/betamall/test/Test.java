package com.betamall.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		
		String[] as = {
				"1234.a"};
		
		for(String s : as ) {
			Pattern p = Pattern.compile("(^[0-9]{1,}\\.)(.+)");
			Matcher m = p.matcher(s);
			if(m.matches() ) {
				System.out.println("group 0 : " + m.group(1));
				System.out.println("group 1 : " + m.group(2));
			}
		}
		
		
		for(String s : as ) {
			Matcher m = Pattern.compile("(^[0-9]{1,})(\\.)(.+)").matcher(s);
			if(m.matches() ) {
				System.out.println("group 0 : " + m.group(1));
				System.out.println("group 1 : " + m.group(3));
			}
		}
	}
}
