package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.BranchDto;
import com.betamall.util.JdbcUtil;

public class BranchDao {
	
private static BranchDao instance = new BranchDao();
	
	private BranchDao() {}
	
	public static BranchDao getInstance() {
		return instance;
	}
	
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT COUNT(*) FROM BRANCH";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
	public int insert(BranchDto brDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "INSERT INTO BRANCH(BRNO, BRNAME, BRADDR, BRTEL, BRDATE, BRIMG) "
					            + "VALUES(SEQ_BRANCH.NEXTVAL,?,?,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1,brDto.getBrName());
			pstmt.setString(2,brDto.getBrAddr());
			pstmt.setString(3,brDto.getBrTel());
			pstmt.setDate(4,brDto.getBrDate());
			pstmt.setString(5,brDto.getBrImg());
			return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con);
			}
		}
	
	public int update(BranchDto brDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "UPDATE BRANCH SET BRNAME = ?, BRADDR = ?, BRTEL = ?, BRDATE = ?, BRIMG = ? WHERE BRNO = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1,brDto.getBrName());
			pstmt.setString(2,brDto.getBrAddr());
			pstmt.setString(3,brDto.getBrTel());
			pstmt.setDate(4,brDto.getBrDate());
			pstmt.setString(5,brDto.getBrImg());
			pstmt.setInt(6,brDto.getBrNo());
			return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con);
			}
		}
	
	public int delete(int brNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "DELETE FROM BRANCH WHERE BRNO = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1,brNo);
			return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con);
			}
		}
	
	public BranchDto select(int brNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM BRANCH WHERE BRNO = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, brNo);
			rs = pstmt.executeQuery();
			BranchDto brDto = null;
			if(rs.next()) {
				brDto = new BranchDto(
						rs.getInt("BRNO"),
						rs.getString("BRNAME"),
						rs.getString("BRADDR"),
						rs.getString("BRTEL"),
						rs.getDate("BRDATE"),
						rs.getString("BRIMG")
					);
			}
			return brDto;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
	public BranchDto selectByBrName(String brName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM BRANCH WHERE BRNAME = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, brName);
			rs = pstmt.executeQuery();
			BranchDto brDto = null;
			if(rs.next()) {
				brDto = new BranchDto(
						rs.getInt("BRNO"),
						rs.getString("BRNAME"),
						rs.getString("BRADDR"),
						rs.getString("BRTEL"),
						rs.getDate("BRDATE"),
						rs.getString("BRIMG")
					);
			}
			return brDto;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
	public ArrayList<BranchDto> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM BRANCH ORDER BY BRNAME";
			pstmt =con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<BranchDto> brDtos = new ArrayList<BranchDto>();
			while(rs.next()) {
				brDtos.add(
						new BranchDto(
								rs.getInt("BRNO"),
								rs.getString("BRNAME"),
								rs.getString("BRADDR"),
								rs.getString("BRTEL"),
								rs.getDate("BRDATE"),
								rs.getString("BRIMG")
							)
						);
			}
			return brDtos;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
	public ArrayList<BranchDto> selectWoMgr() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM (SELECT BR.*, MGRNO "
					   + 				   "FROM BRANCH BR LEFT OUTER JOIN MGR "
					   + 				   "ON(BR.BRNO = MGR.BRNO)) "
					   + "WHERE MGRNO IS NULL";
			pstmt =con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<BranchDto> brDtos = new ArrayList<BranchDto>();
			while(rs.next()) {
				brDtos.add(
						new BranchDto(
								rs.getInt("BRNO"),
								rs.getString("BRNAME"),
								rs.getString("BRADDR"),
								rs.getString("BRTEL"),
								rs.getDate("BRDATE"),
								rs.getString("BRIMG")
							)
						);
			}
			return brDtos;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
	public ArrayList<BranchDto> selectFromTo(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT *"
					   + "FROM (SELECT BRANCH.*, ROW_NUMBER() OVER (ORDER BY BRNO) AS ROW_NUM "
					   +       "FROM BRANCH)"
					   +       "WHERE ROW_NUM BETWEEN ? AND ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<BranchDto> brDtos = new ArrayList<BranchDto>();
			while(rs.next()) {
				brDtos.add(
						new BranchDto(
								rs.getInt("BRNO"),
								rs.getString("BRNAME"),
								rs.getString("BRADDR"),
								rs.getString("BRTEL"),
								rs.getDate("BRDATE"),
								rs.getString("BRIMG")
							)
						);
			}
			return brDtos;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
}