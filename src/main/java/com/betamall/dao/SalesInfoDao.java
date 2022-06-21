package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.SalesInfoDto;
import com.betamall.util.JdbcUtil;

public class SalesInfoDao {

	private static SalesInfoDao instance = new SalesInfoDao();

	private SalesInfoDao() {
	}

	public static SalesInfoDao getInstance() {
		return instance;
	}

	public ArrayList<SalesInfoDto> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM "
					+ "    (SELECT ORDNO, MCATNAME, SCATNAME, ITEMNAME, ORDCNT "
					+ "     FROM (((ORDITEM INNER JOIN \"ORDER\" USING (ORDNO)) INNER JOIN ITEM USING (ITEMNO)) "
					+ "              INNER JOIN MCAT USING (MCATNO)) INNER JOIN SCAT USING (MCATNO, SCATNO)) "
					+ "    INNER JOIN "
					+ "    (SELECT ORDNO, BRNAME, ORDDATE, ORDSTA "
					+ "     FROM (\"ORDER\" INNER JOIN BRANCH USING (BRNO))) "
					+ "    USING (ORDNO)";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<SalesInfoDto> salesInfoDtos = new ArrayList<SalesInfoDto>();
			while (rs.next()) {
				salesInfoDtos.add(
						new SalesInfoDto(
								rs.getInt("ORDNO"),
								rs.getString("MCATNAME"),
								rs.getString("SCATNAME"),
								rs.getString("ITEMNAME"),
								rs.getInt("ORDCNT"),
								rs.getString("BRNAME"),
								rs.getDate("ORDDATE"),
								rs.getString("ORDSTA")));
			}
			return salesInfoDtos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}
