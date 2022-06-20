package com.betamall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.BoardDto;
import com.betamall.util.JdbcUtil;

public class BoardDao {
	private static BoardDao instance=new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstance() {
		return instance;
	}
	
	public int insert(BoardDto dto) {
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
	
	public ArrayList<BoardDto> noticeList(int startRow,int endRow, String field,String keyword){
		String sql=	null;
		if(field==null || field.equals("")) {
			sql="select * from "
					+ "("
					+ "    select aa.*,rownum rnum from"
					+ "    ("
					+ "	      select * from board "
					+ "       where brdcat='공지' or brdcat='이벤트'"
					+ "	      order by brdno desc"
					+ "    )aa"
					+ ") "
					+ "where rnum>=? and rnum<=?";
		}else {
			sql="select * from "
					+ "("
					+ "    select aa.*,rownum rnum from"
					+ "    ("
					+ "	      select * from board where "+ field + " like '%"+ keyword + "%' and (brdcat='공지' or brdcat='이벤트') "
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
			ArrayList<BoardDto> list=new ArrayList<BoardDto>();
			ManagerDao dao=ManagerDao.getInstance();
			while(rs.next()) {
				int brdNo=rs.getInt("brdno");
				int mgrNo=rs.getInt("mgrno");
				String brdCat=rs.getString("brdcat");
				String brdTitle=rs.getString("brdtitle");
				String brdCon=rs.getString("brdcon");
				String brdImg=rs.getString("brdimg");
				Date brdWdate=rs.getDate("brdwdate");
				Date brdMdate=rs.getDate("brdmdate");
				Date brdSdate=rs.getDate("brdsdate");
				Date brdFdate=rs.getDate("brdfdate");
				boolean popUp=rs.getBoolean("popup");
				BoardDto dto=new BoardDto(brdNo, mgrNo, brdCat, brdTitle, brdCon, brdImg, brdWdate, brdMdate, brdSdate, brdFdate, popUp, dao.select(mgrNo).getMgrId());
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
	
	public ArrayList<BoardDto> faqList(int startRow,int endRow, String field,String keyword){
		String sql=	null;
		if(field==null || field.equals("")) {
			sql="select * from "
					+ "("
					+ "    select aa.*,rownum rnum from"
					+ "    ("
					+ "	      select * from board "
					+ "       where brdcat in('회원가입', '주문결제', '배송')"
					+ "	      order by brdno desc"
					+ "    )aa"
					+ ") "
					+ "where rnum>=? and rnum<=?";
		}else {
			sql="select * from "
					+ "("
					+ "    select aa.*,rownum rnum from"
					+ "    ("
					+ "	      select * from board where "+ field + " like '%"+ keyword + "%' and brdcat in('회원가입', '주문결제', '배송') "
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
			ArrayList<BoardDto> list=new ArrayList<BoardDto>();
			ManagerDao dao=ManagerDao.getInstance();
			while(rs.next()) {
				int brdNo=rs.getInt("brdno");
				int mgrNo=rs.getInt("mgrno");
				String brdCat=rs.getString("brdcat");
				String brdTitle=rs.getString("brdtitle");
				String brdCon=rs.getString("brdcon");
				String brdImg=rs.getString("brdimg");
				Date brdWdate=rs.getDate("brdwdate");
				Date brdMdate=rs.getDate("brdmdate");
				Date brdSdate=rs.getDate("brdsdate");
				Date brdFdate=rs.getDate("brdfdate");
				boolean popUp=rs.getBoolean("popup");
				BoardDto dto=new BoardDto(brdNo, mgrNo, brdCat, brdTitle, brdCon, brdImg, brdWdate, brdMdate, brdSdate, brdFdate, popUp, dao.select(mgrNo).getMgrId());
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
			String sql="select count(*) from board";
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
	
	public BoardDto select(int brdNo) {
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
				int mgrNo=rs.getInt("mgrno");
				String brdCat=rs.getString("brdcat");
				String brdTitle=rs.getString("brdtitle");
				String brdCon=rs.getString("brdcon");
				String brdImg=rs.getString("brdimg");
				Date brdWdate=rs.getDate("brdwdate");
				Date brdMdate=rs.getDate("brdmdate");
				Date brdSdate=rs.getDate("brdsdate");
				Date brdFdate=rs.getDate("brdfdate");
				boolean popUp=rs.getBoolean("popup");
				BoardDto dto=new BoardDto(brdNo, mgrNo, brdCat, brdTitle, brdCon, brdImg, brdWdate, brdMdate, brdSdate, brdFdate, popUp);
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
	
	public int update(BoardDto dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql="update board set brdcat=?, brdtitle=?, brdcon=?, brdimg=?, brdmdate=current_date, brdsdate=?, brdfdate=?, popup=? where brdno=?";
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
