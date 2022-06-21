package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.OrderInfoDto;
import com.betamall.util.JdbcUtil;

public class OrderInfoDao {

	private static OrderInfoDao instance = new OrderInfoDao();
	
	private OrderInfoDao() {}
	
	public static OrderInfoDao getInstance() {
		return instance;
	}
	
	public ArrayList<OrderInfoDto> selectByBrNQuery(int brNo, String query) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if(query == null) {
			query = "";
		}
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT ORDNO, ITEMNO, ITEMNAME, ORDCNT, PRICE, ORDDATE, ORDSTA, MBRID, ORDNAME, ORDTEL, ORDARRD "
					+ "FROM (((\"ORDER\" INNER JOIN ORDITEM USING(ORDNO)) INNER JOIN ITEM USING(ITEMNO))) INNER JOIN MEMBER USING (MBRNO) "
					+ "WHERE BRNO = ? ";
			sql += query;
			sql +=    "ORDER BY ORDNO DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, brNo);
			rs = pstmt.executeQuery();
			ArrayList<OrderInfoDto> ordInfoDtos = new ArrayList<OrderInfoDto>();
			while (rs.next()) {
				ordInfoDtos.add(
						new OrderInfoDto(
								rs.getInt("ORDNO"),
								rs.getInt("ITEMNO"),
								rs.getString("ITEMNAME"),
								rs.getInt("ORDCNT"),
								rs.getInt("PRICE"),
								rs.getDate("ORDDATE"),
								rs.getString("ORDSTA"),
								rs.getString("MBRID"),
								rs.getString("ORDNAME"),
								rs.getString("ORDTEL"),
								rs.getString("ORDARRD")
								)
						);
			}
			return ordInfoDtos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
}