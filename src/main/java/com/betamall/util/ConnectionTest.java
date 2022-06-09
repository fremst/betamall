package com.betamall.util;

import java.sql.Connection;

public class ConnectionTest {
	public static void main(String[] args) {
		Connection con = JdbcUtil.getCon();
		if(con!=null) {System.out.println("db 접속 성공");}
	}
}
