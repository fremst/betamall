package com.betamall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
					+ "	      select * from qna q inner join member m on q.mbrno=m.mbrno where "+ field + " like '%"+ keyword + "%'"
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
			MemberDao dao=MemberDao.getInstance();
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
				QnaDto dto=new QnaDto(qnaNo, mbrNo, itemNo, qnaCat, qnaTitle, qnaCon, qnaFile, secret, qnaWdate, qnaMdate, qnaDel, dao.select(mbrNo).getMbrId());
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
			String sql="insert into qna values(seq_qna.nextval,?,?,?,?,?,?,?,current_date,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,dto.getMbrNo());
			pstmt.setObject(2,dto.getItemNo());
			pstmt.setString(3,dto.getQnaCat());
			pstmt.setString(4,dto.getQnaTitle());
			pstmt.setString(5,dto.getQnaCon());
			pstmt.setString(6, dto.getQnaFile());
			pstmt.setBoolean(7, dto.isSecret());
			pstmt.setDate(8, dto.getQnaMdate());
			pstmt.setBoolean(9, dto.isQnaDel());
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
	
	public QnaDto select(int qnaNo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			String sql="select * from qna where qnano=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qnaNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int mbrNo=rs.getInt("mbrno");
				int itemNo=rs.getInt("itemno");
				String qnaCat=rs.getString("qnacat");
				String qnaTitle=rs.getString("qnatitle");
				String qnaCon=rs.getString("qnacon");
				String qnaFile=rs.getString("qnafile");
				boolean secret=rs.getBoolean("secret");
				Date qnaWdate=rs.getDate("qnawdate");
				Date qnaMdate=rs.getDate("qnamdate");
				boolean qnaDel=rs.getBoolean("qnadel");
				QnaDto dto=new QnaDto(qnaNo, mbrNo, itemNo, qnaCat, qnaTitle, qnaCon, qnaFile, secret, qnaWdate, qnaMdate, qnaDel);
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
	
	public int delete(int qnaNo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql="update qna set qnadel=? where qnano=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, qnaNo);
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
	
	public int update(QnaDto dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql="update qna set itemno=?, qnacat=?, qnatitle=?, qnacon=?, qnafile=?, secret=?, qnamdate=current_date where qnano=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setObject(1, dto.getItemNo());
			pstmt.setString(2, dto.getQnaCat());
			pstmt.setString(3, dto.getQnaTitle());
			pstmt.setString(4, dto.getQnaCon());
			pstmt.setString(5, dto.getQnaFile());
			pstmt.setBoolean(6, dto.isSecret());
			pstmt.setInt(7, dto.getQnaNo());
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
}
