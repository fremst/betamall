package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.ManagerDto;
import com.betamall.util.JdbcUtil;


public class ManagerDao {
	
private static ManagerDao instance = new ManagerDao();
	
	private ManagerDao() {}
	
	public static ManagerDao getInstance() {
		return instance;
	}
	
	public int insert(ManagerDto mgrDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "INSERT INTO MGR(MGRNO, BRNO, MGRID, MGRPWD, MGRNAME, MGRTEL, MGREMAIL, MGRIMG) "
					            + "VALUES(SEQ_MGR.NEXTVAL,?,?,?,?,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1,mgrDto.getBrNo());
			pstmt.setString(2,mgrDto.getMgrId());
			pstmt.setString(3,mgrDto.getMgrPwd());
			pstmt.setString(4,mgrDto.getMgrName());
			pstmt.setString(5,mgrDto.getMgrTel());
			pstmt.setString(6,mgrDto.getMgrEmail());
			pstmt.setString(7,mgrDto.getMgrImg());
			return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			} finally {
				JdbcUtil.close(pstmt);
				JdbcUtil.close(con);
			}
		}
	
	public ArrayList<ManagerDto> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM MGR ORDER BY MGRNO";
			pstmt =con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ManagerDto> mgrDtos = new ArrayList<ManagerDto>();
			while(rs.next()) {
				mgrDtos.add(
						new ManagerDto(
								rs.getInt("MGRNO"),
								rs.getInt("BRNO"),
								rs.getString("MGRID"),
								rs.getString("MGRPWD"),
								rs.getString("MGRNAME"),
								rs.getString("MGRTEL"),
								rs.getString("MGREMAIL"),
								rs.getString("MGRIMG")
							)
						);
			}
			return mgrDtos;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
}
