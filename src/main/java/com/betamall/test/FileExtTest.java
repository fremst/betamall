package com.betamall.test;

public class FileExtTest {

	public static void main(String[] args) {
		String fileName = "aa.bbb.png";
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		System.out.println(ext);
	}
}
