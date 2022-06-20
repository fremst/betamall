package com.betamall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.BoardDto;
import com.betamall.dto.QnaDto;
import com.betamall.util.JdbcUtil;

public class QnaDao {
	private static QnaDao instance=new QnaDao();
	private QnaDao() {}
	public static QnaDao getInstance() {
		return instance;
	}
	
	public ArrayList<QnaDto> list(int startRow,int endRow, String field,String keyword){
		String sql=	null;
		if(field==null || field.equals("")) {
			sql="select * from "
					+ "("
					+ "    select aa.*,rownum rnum from"
					+ "    ("
					+ "	      select * from qna "
					+ "	      order by qnano desc"
					+ "    )aa"
					+ ") "
					+ "where rnum>=? and rnum<=?";
		}else {
			sql="select * from "
					+ "("
					+ "    select aa.*,rownum rnum from"
					+ "    ("
					+ "	      select * from qna where "+ field + " like '%"+ keyword + "%'"
					+ "	      order by qnano desc"
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
			ArrayList<QnaDto> list=new ArrayList<QnaDto>();
			QnaDao dao=QnaDao.getInstance();
			while(rs.next()) {
				int qnaNo=rs.getInt("qnano");
				int mbrNo=rs.getInt("mbrNo");
				int itemNo=rs.getInt("itemNo");
				String qnaCat=rs.getString("qnacat");
				String qnaTitle=rs.getString("qnatitle");
				String qnaCon=rs.getString("qnacon");
				String qnaFile=rs.getString("qnafile");
				boolean secret=rs.getBoolean("secret");
				Date qnaWdate=rs.getDate("qnawdate");
				Date qnaMdate=rs.getDate("qnamdate");
				boolean qnaDel=rs.getBoolean("qnadel");
				QnaDto dto=new QnaDto(qnaNo, mbrNo, itemNo, qnaCat, qnaTitle, qnaCon, qnaFile, secret, qnaWdate, qnaMdate, qnaDel);
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
			String sql="select count(*) from qna";
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
	
	public int insert(QnaDto dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql="insert into qna values(seq_board.nextval,?,?,?,?,?,current_date,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,dto.getQnaNo());
			pstmt.setInt(2,dto.getMbrNo());
			pstmt.setInt(3,dto.getItemNo());
			pstmt.setString(4,dto.getQnaCat());
			pstmt.setString(5,dto.getQnaTitle());
			pstmt.setString(6,dto.getQnaCon());
			pstmt.setString(7, dto.getQnaFile());
			pstmt.setBoolean(8, dto.isSecret());
			pstmt.setDate(9, dto.getQnaWdate());
			pstmt.setDate(10, dto.getQnaMdate());
			pstmt.setBoolean(11, dto.isQnaDel());
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
}
