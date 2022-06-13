package com.betamall.test;

import java.io.File;

public class FileDeleteTest {
	public static void main(String[] args) {
		String saveDir = "C:\\2203\\web\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\betamall\\resources\\uploads\\admin\\manager";
		new File(saveDir, "asdf.jpg").delete();
	}
}