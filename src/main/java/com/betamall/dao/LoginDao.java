package com.betamall.dao;

import com.betamall.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginDao {
    private static LoginDao instance = new LoginDao();
    private LoginDao() {}
    public static LoginDao getInstance(){
        return instance;
    }

    public boolean isMember(HashMap<String, String> map){
        String id= map.get("id");
        String pwd = map.get("pwd");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "select * from MEMBER where MBRID=? and MBRPWD=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,pwd);
            rs= pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            JdbcUtil.close(con,pstmt,rs);
        }
        return false;
    }
}
