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
	
	public ArrayList<ItemDto> selectAll(int startRow,int endRow){
		String sql="select * from"
				+ "("
				+"		select aa.*, rownum rnum from"
				+"		("
				+"			select * from item"
				+"			order by mcatno asc, scatno asc "
				+"		) aa"
				+"	)where rnum>=? and rnum<=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<ItemDto> list=new ArrayList<ItemDto>();
			while(rs.next()) {
				int itemNo=rs.getInt("ITEMNO");
				int mcatNo=rs.getInt("MCATNO");
				int scatNo=rs.getInt("SCATNO");
				String itemName=rs.getString("ITEMNAME");
				String tImg=rs.getString("TIMG");
				String detImg=rs.getString("DETIMG");
				String hash=rs.getString("HASH");
				int price = rs.getInt("PRICE");
				boolean itemDel=rs.getBoolean("ITEMDEL");
				ItemDto dto=new ItemDto(itemNo, mcatNo, scatNo, itemName, tImg, detImg, hash, price, itemDel);
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
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="select count(*) from item";
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int cnt=rs.getInt(1); //1번째 칼럼값 얻어오기
			return cnt;
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
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
	
	public int updateItem(ItemDto dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="update item set itemdel=? where itemname=?";
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareStatement(sql);
			pstmt.setBoolean(1, dto.isItemDel());
			pstmt.setString(2, dto.getItemName());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
}