package com.betamall.dao;

import com.betamall.dto.MemberDto;
import com.betamall.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MemberDao {
    public static MemberDao instance = new MemberDao();

    private MemberDao() {}

    public static MemberDao getInstance(){
        return instance;
    }

    public int insert(MemberDto dto){
        Connection con = null;
        PreparedStatement pstmt =null;
        try{
            con = JdbcUtil.getCon();
            String sql = "INSERT INTO MEMBER VALUES(SEQ_MEMBER.NEXTVAL,?,?,?,?,?,?,?,SYSDATE,?,0)";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,dto.getMbrName());
            pstmt.setString(2,dto.getMbrTel());
            pstmt.setString(3,dto.getMbrAdr());
            pstmt.setString(4,dto.getMbrEmail());
            pstmt.setString(5,dto.getMbrId());
            pstmt.setString(6,dto.getMbrPwd());
            pstmt.setDate(7,dto.getMbrBd());
            pstmt.setString(8,dto.getMbrGrade());
            return pstmt.executeUpdate();
        }catch(SQLException s) {
            s.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }
}
