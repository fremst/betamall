package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.ItemDetailDto;
import com.betamall.util.JdbcUtil;

public class ItemDetailDao {

	private static ItemDetailDao instance = new ItemDetailDao();

	private ItemDetailDao() {}
	
	public static ItemDetailDao getInstance() {
		return instance;
	}
	
	public ArrayList<ItemDetailDto> selectAll(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			String sql="SELECT ITEMNO, ITEMNAME, TIMG, MCATNO, SCATNO, MCATNAME, SCATNAME, HASH, PRICE, BRNAME, NVL(STKCNT, 0) STKCNT "
					+ "FROM (STOCK INNER JOIN BRANCH USING (BRNO) "
					+ 		"RIGHT OUTER JOIN (ITEM INNER JOIN MCAT USING (MCATNO) "
					+ 						  "INNER JOIN SCAT USING(SCATNO, MCATNO)) USING (ITEMNO)) "
					+ "WHERE ITEMDEL = 0 ORDER BY ITEMNO";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<ItemDetailDto> list = new ArrayList<ItemDetailDto>();
			while(rs.next()) {
				int itemNo = rs.getInt("ITEMNO");
				String itemName = rs.getString("ITEMNAME");
				String tImg = rs.getString("TIMG");
				int mcatNo = rs.getInt("MCATNO");
				int scatNo = rs.getInt("SCATNO");
				String mcatName = rs.getString("MCATNAME");
				String scatName = rs.getString("SCATNAME");
				String hash = "";
				if(rs.getString("HASH")!=null) {
					hash = '#' + rs.getString("HASH").replace(","," #");
				}
				int price = rs.getInt("PRICE");
				String brName = rs.getString("BRNAME");
				int stkCnt = rs.getInt("STKCNT");
				ItemDetailDto dto = new ItemDetailDto(itemNo, itemName, tImg, mcatNo, scatNo, mcatName, scatName, hash, price, brName, stkCnt);
				list.add(dto);
			}
			return list;
		}catch(SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public ArrayList<ItemDetailDto> selectByKeyword(String field, String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			String sql="SELECT ITEMNO, ITEMNAME, TIMG, MCATNO, SCATNO, MCATNAME, SCATNAME, HASH, PRICE, BRNAME, NVL(STKCNT, 0) STKCNT "
					+ "FROM (STOCK INNER JOIN BRANCH USING (BRNO) "
					+ 		"RIGHT OUTER JOIN (ITEM INNER JOIN MCAT USING (MCATNO) "
					+ 						  "INNER JOIN SCAT USING(SCATNO, MCATNO)) USING (ITEMNO)) ";
			switch (field) {
				case "itemName":
					sql += "WHERE ITEMNAME LIKE '%" + keyword + "%' AND ITEMDEL = 0 ORDER BY ITEMNO";
					break;
				case "brName":
                    sql += "WHERE BRNAME LIKE '%" + keyword + "%' AND ITEMDEL = 0 ORDER BY ITEMNO";;
                    break;
				case "mcatName":
					sql += "WHERE MCATNAME LIKE '%" + keyword + "%' AND ITEMDEL = 0 ORDER BY ITEMNO";;
					break;
			}
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<ItemDetailDto> list = new ArrayList<ItemDetailDto>();
			while(rs.next()) {
				int itemNo = rs.getInt("ITEMNO");
				String itemName = rs.getString("ITEMNAME");
				String tImg = rs.getString("TIMG");
				int mcatNo = rs.getInt("MCATNO");
				int scatNo = rs.getInt("SCATNO");
				String mcatName = rs.getString("MCATNAME");
				String scatName = rs.getString("SCATNAME");
				String hash = "";
				if(rs.getString("HASH")!=null) {
					hash = '#' + rs.getString("HASH").replace(","," #");
				}
				int price = rs.getInt("PRICE");
				String brName = rs.getString("BRNAME");
				int stkCnt = rs.getInt("STKCNT");
				ItemDetailDto dto = new ItemDetailDto(itemNo, itemName, tImg, mcatNo, scatNo, mcatName, scatName, hash, price, brName, stkCnt);
				list.add(dto);
			}
			return list;
		}catch(SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}

}