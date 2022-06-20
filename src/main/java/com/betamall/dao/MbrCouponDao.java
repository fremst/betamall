package com.betamall.dao;

import com.betamall.dto.MbrCouponInfoDto;
import com.betamall.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MbrCouponDao {
    public static MbrCouponDao instance = new MbrCouponDao();

    private MbrCouponDao() {
    }

    public static MbrCouponDao getInstance() {
        return instance;
    }


    public ArrayList<MbrCouponInfoDto> haveCoupon(int mbrNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT * FROM COUPON INNER JOIN MBRCOUPON using(CPNNO) WHERE MBRNO=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, mbrNo);
            rs = pstmt.executeQuery();
            ArrayList<MbrCouponInfoDto> list = new ArrayList<>();
            while (rs.next()) {
                list.add(
                        new MbrCouponInfoDto(
                                rs.getInt("MBRNO"),
                                rs.getInt("CPNNO"),
                                rs.getString("COND"),
                                rs.getInt("DISCRATE"),
                                rs.getInt("MBRCPNCNT"),
                                rs.getInt("MINORD"),
                                rs.getDate("EXPDATE"),
                                rs.getDate("PERIOD"),
                                rs.getBoolean("ACTIVE"),
                                rs.getBoolean("ISSUED"),
                                rs.getInt("SCATNO"),
                                rs.getInt("MCATNO")
                        )
                );
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }
}
