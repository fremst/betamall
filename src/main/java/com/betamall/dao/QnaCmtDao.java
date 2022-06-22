package com.betamall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.QnaCmtDto;
import com.betamall.util.JdbcUtil;

public class QnaCmtDao {
	private static QnaCmtDao instance=new QnaCmtDao();
	private QnaCmtDao() {}
	public static QnaCmtDao getInstance() {
		return instance;
	}
	
	public ArrayList<QnaCmtDto> list(int qnaNo){
		String sql=	"select * from qnacmt where qnano=? order by qnacmtno";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qnaNo);
			rs=pstmt.executeQuery();
			ArrayList<QnaCmtDto> list=new ArrayList<QnaCmtDto>();
			while(rs.next()) {
				int qnaCmtNo=rs.getInt("qnacmtno");
				String qnaCmtCon=rs.getString("qnacmtcon");
				Date qnaCmtWdate=rs.getDate("qnacmtwdate");
				boolean qnaCmtDel=rs.getBoolean("qnacmtdel");
				QnaCmtDto dto=new QnaCmtDto(qnaCmtNo, qnaNo, qnaCmtCon, qnaCmtWdate, qnaCmtDel);
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
	
	public int delete(int qnaCmtNo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql="update qnacmt set qnacmtdel=? where qnacmtno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, qnaCmtNo);
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
	
	public int insert(QnaCmtDto dto) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql="insert into qnacmt values(seq_qnacmt.nextval, ?, ?, current_date, ?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,dto.getQnaNo());
			pstmt.setString(2,dto.getQnaCmtCon());
			pstmt.setBoolean(3, dto.isQnaCmtDel());
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
}
