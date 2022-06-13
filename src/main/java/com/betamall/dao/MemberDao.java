package com.betamall.dao;

import com.betamall.dto.MemberDto;
import com.betamall.util.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberDao {
    public static MemberDao instance = new MemberDao();

    private MemberDao() {
    }

    public static MemberDao getInstance() {
        return instance;
    }

    public int insert(MemberDto dto) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "INSERT INTO MEMBER VALUES(SEQ_MEMBER.NEXTVAL,?,?,?,?,?,?,?,SYSDATE,?,0)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dto.getMbrName());
            pstmt.setString(2, dto.getMbrTel());
            pstmt.setString(3, dto.getMbrAdr());
            pstmt.setString(4, dto.getMbrEmail());
            pstmt.setString(5, dto.getMbrId());
            pstmt.setString(6, dto.getMbrPwd());
            pstmt.setDate(7, dto.getMbrBd());
            pstmt.setString(8, dto.getMbrGrade());
            return pstmt.executeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
            return -1;
        } finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }
}
    public MemberDto isMember(String id, String pwd) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "select * from member where MBRID=? and MBRPWD=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int num = rs.getInt("MBRNO");
                String name = rs.getString("MBRNAME");
                String tel = rs.getString("MBRTEL");
                String addr = rs.getString("MBRADR");
                String email = rs.getString("MBREMAIL");
                id = rs.getString("MBRID");
                pwd = rs.getString("MBRPWD");
                Date bd = rs.getDate("MBRBD");
                Date regdate = rs.getDate("REGDATE");
                String grade = rs.getString("GRADE");
                int totamt = rs.getInt("TOTAMT");
                MemberDto dto = new MemberDto(num, name, tel, addr, email, id, pwd, bd, regdate, grade, totamt);
                return dto;
            }
            return null;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }

    public int checkId(String id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = -1; // 오류
        try {
            con = JdbcUtil.getCon();
            String sql = "select * from member where MBRID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = 1;// 아이디가 존재할 경우
            } else {
                result = 0;// 사용가능한 아이디
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return result;
    }

    public String searchId(String email, String tel) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String id = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "select MBRID from member where=MBREMAIL=? and MBRTEL=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, tel);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getString("id");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return id;
    }

    public String searchPwd(String id, String email, String tel) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String pwd = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "select MBRID from member where=MBRID=? and MBREMAIL=? and MBRTEL=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, tel);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pwd = rs.getString("pwd");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return pwd;
    }
}
