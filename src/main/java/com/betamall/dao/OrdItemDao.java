package com.betamall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.OrdItemDto;
import com.betamall.util.JdbcUtil;

public class OrdItemDao {

	private static OrdItemDao instance = new OrdItemDao();
	
	private OrdItemDao() {}
	
	public static OrdItemDao getInstance() {
		return instance;
	}
	
	public int insert(OrdItemDto ordItemDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "INSERT INTO ORDITEM(ORDNO, ITEMNO, ORDCNT, REVIEW, RATE, REVDATE) "
					   + "VALUES(?, ?, ?, NULL, NULL, NULL)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordItemDto.getOrdNo());
			pstmt.setInt(2, ordItemDto.getItemNo());
			pstmt.setInt(3, ordItemDto.getOrdCnt());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
	
	public ArrayList<OrdItemDto> selectByOrdNo(int ordNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM ORDITEM WHERE ORDNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordNo);
			rs = pstmt.executeQuery();
			ArrayList<OrdItemDto> ordItemDtos = new ArrayList<OrdItemDto>();
			while (rs.next()) {
				ordItemDtos.add(
						new OrdItemDto(
								rs.getInt("ORDNO"),
								rs.getInt("ITEMNO"),
								rs.getInt("ORDCNT"),
								rs.getString("REVIEW"),
								rs.getInt("RATE"),
								rs.getDate("REVDATE")
								)
						);
			}
			return ordItemDtos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	public int getTotPmt(int ordNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT SUM(PRICE * ORDCNT) "
					   + "FROM ORDITEM INNER JOIN ITEM USING(ITEMNO) "
					   + "WHERE ORDNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return 0;
	}

	public int reviewinsert(OrdItemDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "update orditem set review=?, rate=?, revdate=current_date where ordno=? and itemNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getReview());
			pstmt.setInt(2, dto.getRate());
			pstmt.setInt(3, dto.getOrdNo());
			pstmt.setInt(4, dto.getItemNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
	
	public int reviewdelete(int ordNo, int itemNo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getCon();
			String sql = "update orditem set review=null, rate=null where ordno=? and itemNo=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, ordNo);
			pstmt.setInt(2, itemNo);
			return pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}		
	}
	
	public Date checkreview(int ordNo, int itemNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "select revdate from orditem where ordno=? and itemNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordNo);
			pstmt.setInt(2, itemNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getDate("revdate");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return null;
	}
	
	public ArrayList<OrdItemDto> list(int itemNo){
		String sql=	"select * from orditem where itemno=? and review is not null order by revdate desc";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			rs=pstmt.executeQuery();
			ArrayList<OrdItemDto> list=new ArrayList<OrdItemDto>();
			OrderDao dao=OrderDao.getInstance();
			MemberDao mdao=MemberDao.getInstance();
			while(rs.next()) {
				int ordNo=rs.getInt("ordno");
				int ordCnt=rs.getInt("ordCnt");
				String review=rs.getString("review");
				int	rate=rs.getInt("rate");
				Date revDate=rs.getDate("revDate");
				String mbrId=mdao.select(dao.select(ordNo).getMbrNo()).getMbrId();
				OrdItemDto dto=new OrdItemDto(ordNo, itemNo, ordCnt, review, rate, revDate, mbrId) ;
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
	
	public OrdItemDto select(int ordNo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			String sql="select * from orditem where ordno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, ordNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int itemNo=rs.getInt("itemNo");
				int ordCnt=rs.getInt("ordCnt");
				String review=rs.getString("review");
				int rate=rs.getInt("rate");
				Date revDate=rs.getDate("revDate");
				OrdItemDto dto=new OrdItemDto(ordNo, itemNo, ordCnt, review, rate, revDate);
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
	
	public ArrayList<OrdItemDto> mylist(int mbrNo){
		String sql=	"SELECT * FROM ORDITEM OI INNER JOIN \"ORDER\" OD ON OI.ORDNO=OD.ORDNO WhERE MBRNO=? AND REVIEW IS NOT NULL ORDER BY REVDATE DESC";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mbrNo);
			rs=pstmt.executeQuery();
			ArrayList<OrdItemDto> list=new ArrayList<OrdItemDto>();
			OrderDao dao=OrderDao.getInstance();
			MemberDao mdao=MemberDao.getInstance();
			while(rs.next()) {
				int ordNo=rs.getInt("ordno");
				int itemNo=rs.getInt("itemno");
				int ordCnt=rs.getInt("ordCnt");
				String review=rs.getString("review");
				int	rate=rs.getInt("rate");
				Date revDate=rs.getDate("revDate");
				String mbrId=mdao.select(dao.select(ordNo).getMbrNo()).getMbrId();
				OrdItemDto dto=new OrdItemDto(ordNo, itemNo, ordCnt, review, rate, revDate, mbrId) ;
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
}
