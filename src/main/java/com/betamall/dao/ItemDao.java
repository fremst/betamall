package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.ItemDto;
import com.betamall.util.JdbcUtil;


public class ItemDao {

	private static ItemDao instance = new ItemDao();

	private ItemDao() {}
	
	public static ItemDao getInstance() {
		return instance;
	}
	
	public ArrayList<ItemDto> selectAll(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			String sql="select * from item";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<ItemDto> list=new ArrayList<ItemDto>();
			while(rs.next()) {
				int itemNo=rs.getInt("itemNo");
				int mcatNo=rs.getInt("mcatNo");
				int scatNo=rs.getInt("scatNo");
				String itemName=rs.getString("itemName");
				String tImg=rs.getString("itemname");
				String detImg=rs.getString("detImg");
				String hash=rs.getString("hash");
				boolean itemDel=rs.getBoolean("itemDel");
				ItemDto dto=new ItemDto(itemNo, mcatNo, scatNo, itemName, tImg, detImg, hash, scatNo, itemDel);
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
	
	public int insert(ItemDto itemdto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql = "insert into item(itemno, mcatno, scatno,itemname,timg,detimg,hash,price)"
								 + "values(seq_item.nextval,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, itemdto.getMcatNo());
			pstmt.setInt(2,itemdto.getScatNo());
			pstmt.setString(3, itemdto.getItemName());
			pstmt.setString(4, itemdto.gettImg());
			pstmt.setString(5, itemdto.getDetImg());
			pstmt.setString(6, itemdto.getHash());
			pstmt.setInt(7, itemdto.getPrice());
			
			return pstmt.executeUpdate();
			
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
}
