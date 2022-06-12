package com.betamall.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	public static Connection getCon() {
		Connection con = null;
		Properties props = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			props = new Properties();
			props.load(new FileReader("C:\\2203\\web\\workspace\\betamall\\src\\main\\resources\\jdbc.properties"));

			
			String url = props.getProperty("url")+props.getProperty("path");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			
			con = DriverManager.getConnection(url, user, password);
//			con.setAutoCommit(false);
			
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection con) {
		try {
			if(con!=null) con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null) stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null) rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if(con!=null) stmt.close();
			if(stmt!=null) stmt.close();
			if(rs!=null) stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
