package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.MbrCouponDto;
import com.betamall.util.JdbcUtil;

public class MbrCouponDao {
    public static MbrCouponDao instance = new MbrCouponDao();
    private MbrCouponDao(){}
    public static MbrCouponDao getInstance(){
        return instance;
    }       
    
    
    public MbrCouponDto haveCoupon(String mbrId) {
    	 Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try {
             con = JdbcUtil.getCon();
             String sql = "SELECT MEM.MBRID, C.COND "
             		+ "FROM COUPON C JOIN MBRCOUPON M ON(C.CPNNO = M.CPNNO) JOIN MEMBER MEM ON (M.MBRNO=MEM.MBRNO) WHERE MBRID=?";
             pstmt = con.prepareStatement(sql);
             pstmt.setString(1, mbrId);
             rs = pstmt.executeQuery();
             MbrCouponDto mbrCouponDto = null;
             if (rs.next()) {
                 mbrCouponDto = new MbrCouponDto (
                		 rs.getString("MBRID"),
                         rs.getString("COND"));
             }
             return mbrCouponDto;
         } catch (SQLException e) {
             e.printStackTrace();
             return null;
         } finally {
             JdbcUtil.close(con, pstmt, rs);
         }
     }
    
    public MbrCouponDto select(int mbrNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT * FROM MBRCOUPON WHERE MBRNO=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, mbrNo);
            rs = pstmt.executeQuery();
            MbrCouponDto mbrCouponDto = null;
            if (rs.next()) {
                mbrCouponDto = new MbrCouponDto(
                        rs.getInt("MBRNO"),
                        rs.getInt("CPNNO"),
                        rs.getInt("MBRCPNCNT"));
                
            }
            return mbrCouponDto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }
    
    public ArrayList<MbrCouponDto> selectAll() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT * FROM MBRCOUPON ORDER BY MBRNO";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ArrayList<MbrCouponDto> mbrCouponDtos = new ArrayList<>();
            while (rs.next()) {
            	mbrCouponDtos.add(
                        new MbrCouponDto(
                                rs.getInt("MBRNO"),
                                rs.getInt("CPNNO"),
                                rs.getInt("MBRCPNCNT")));
            }
            return mbrCouponDtos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }
}
