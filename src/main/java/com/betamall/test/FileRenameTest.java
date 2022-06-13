package com.betamall.test;

import java.io.File;

public class FileRenameTest {

	public static void main(String[] args) {
		String saveDir = "C:\\2203\\web\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\betamall\\resources\\uploads\\admin\\manager";
		String systemFileName = "mgr128.jpg";
		String saveFileName = "asdf.jpg";
		new File(saveDir, systemFileName).renameTo(new File(saveDir, saveFileName));
		System.out.println(systemFileName);
		System.out.println(saveFileName);
	}
}
