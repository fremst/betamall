package com.betamall.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtil {
	public static Connection getCon() {
		Connection con = null;
		try {
			DataSource ds = (DataSource)((Context)new InitialContext().lookup("java:/comp/env")).lookup("jdbc/myoracle");
			con = ds.getConnection();
			
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
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
