package com.betamall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.NoticeDto;
import com.betamall.util.JdbcUtil;

public class NoticeDao {
	private static NoticeDao instance=new NoticeDao();
	private NoticeDao() {}
	public static NoticeDao getInstance() {
		return instance;
	}
	
	public int insert(NoticeDto dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql="insert into board values(seq_board.nextval,?,?,?,?,?,current_date,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,dto.getMgrNo());
			pstmt.setString(2,dto.getBrdCat());
			pstmt.setString(3, dto.getBrdTitle());
			pstmt.setString(4, dto.getBrdCon());
			pstmt.setString(5, dto.getBrdImg());
			pstmt.setDate(6, dto.getBrdWdate());
			pstmt.setDate(7, dto.getBrdSdate());
			pstmt.setDate(8, dto.getBrdFdate());
			pstmt.setBoolean(9, dto.isPopUp());
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
	
	public ArrayList<NoticeDto> list(int startRow,int endRow, String field,String keyword){
		String sql=	null;
		if(field==null || field.equals("")) {
			sql="select * from "
					+ "("
					+ "    select aa.*,rownum rnum from"
					+ "    ("
					+ "	      select * from board "
					+ "	      order by brdno desc"
					+ "    )aa"
					+ ") "
					+ "where rnum>=? and rnum<=?";
		}else {
			sql="select * from "
					+ "("
					+ "    select aa.*,rownum rnum from"
					+ "    ("
					+ "	      select * from board where "+ field + " like '%"+ keyword + "%' "
					+ "	      order by brdno desc"
					+ "    )aa"
					+ ") "
					+ "where rnum>=? and rnum<=?";
			}
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<NoticeDto> list=new ArrayList<NoticeDto>();
			while(rs.next()) {
				int brdNo=rs.getInt("brdNo");
				int mgrNo=rs.getInt("mgrNo");
				String brdCat=rs.getString("brdCat");
				String brdTitle=rs.getString("brdTitle");
				String brdCon=rs.getString("brdCon");
				String brdImg=rs.getString("brdImg");
				Date brdWdate=rs.getDate("brdWdate");
				Date brdMdate=rs.getDate("brdMdate");
				Date brdSdate=rs.getDate("brdSdate");
				Date brdFdate=rs.getDate("brdFdate");
				boolean popUp=rs.getBoolean("popUp");
				NoticeDto dto=new NoticeDto(brdNo, mgrNo, brdCat, brdTitle, brdCon, brdImg, brdWdate, brdMdate, brdSdate, brdFdate, popUp);
				list.add(dto);
			}
			return list;
		}catch(SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	public int getCount(String field, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			String sql="select COUNT(*) from board";
			if(field!=null && !field.equals("")) {
				sql += " where "+ field +" like '%"+ keyword + "%' ";
			}	
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return -1;
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}		
	}
	
	public NoticeDto select(int brdNo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			String sql="select * from board where brdno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, brdNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int mgrNo=rs.getInt("mgrNo");
				String brdCat=rs.getString("brdCat");
				String brdTitle=rs.getString("brdTitle");
				String brdCon=rs.getString("brdCon");
				String brdImg=rs.getString("brdImg");
				Date brdWdate=rs.getDate("brdWdate");
				Date brdMdate=rs.getDate("brdMdate");
				Date brdSdate=rs.getDate("brdSdate");
				Date brdFdate=rs.getDate("brdFdate");
				boolean popUp=rs.getBoolean("popUp");
				NoticeDto dto=new NoticeDto(brdNo, mgrNo, brdCat, brdTitle, brdCon, brdImg, brdWdate, brdMdate, brdSdate, brdFdate, popUp);
				return dto;
			}
			return null;
		}catch(SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}		
	}
	
	public int update(NoticeDto dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql="update board set brdCat=?, brdTitle=?, brdCon=?, brdImg=?, brdMdate=current_date, brdSdate=?, brdFdate=?, popUp=? where brdno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getBrdCat());
			pstmt.setString(2, dto.getBrdTitle());
			pstmt.setString(3, dto.getBrdCon());
			pstmt.setString(4, dto.getBrdImg());
			pstmt.setDate(5, dto.getBrdSdate());
			pstmt.setDate(6, dto.getBrdFdate());
			pstmt.setBoolean(7, dto.isPopUp());
			pstmt.setInt(8, dto.getBrdNo());
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
	
	public int delete(int brdNo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql="delete from board where brdno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, brdNo);
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
}
