package com.betamall.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.MemberDto;
import com.betamall.util.JdbcUtil;

public class MemberDao {
    public static MemberDao instance = new MemberDao();

    private MemberDao() {
    }

    public static MemberDao getInstance() {
        return instance;
    }
    
    public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT COUNT(*) FROM MEMBER";
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

    public int insert(MemberDto dto) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "INSERT INTO MEMBER VALUES(SEQ_MEMBER.NEXTVAL,?,?,?,?,?,?,?,CURRENT_DATE,?,0)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dto.getMbrName());
            pstmt.setString(2, dto.getMbrTel());
            pstmt.setString(3, dto.getMbrAdr());
            pstmt.setString(4, dto.getMbrEmail());
            pstmt.setString(5, dto.getMbrId());
            pstmt.setString(6, dto.getMbrPwd());
            pstmt.setDate(7, dto.getMbrBd());
            pstmt.setString(8, dto.getMbrGrade());
            return pstmt.executeUpdate();
        } catch (SQLException s) {
            s.printStackTrace();
            return -1;
        } finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }

    public int delete(String mbrId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "DELETE FROM MEMBER WHERE MBRID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mbrId);
            int n = pstmt.executeUpdate();
            return n;
        } catch (SQLException se) {
            se.printStackTrace();
            return -1;
        } finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }

    public int update(MemberDto mbrDto) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "UPDATE MEMBER SET MBRPWD=?,MBRTEL=?,MBRADR=?,MBREMAIL=? WHERE MBRID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mbrDto.getMbrPwd());
            pstmt.setString(2, mbrDto.getMbrTel());
            pstmt.setString(3, mbrDto.getMbrAdr());
            pstmt.setString(4, mbrDto.getMbrEmail());
            pstmt.setString(5, mbrDto.getMbrId());
            return pstmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            return -1;
        } finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }

    public MemberDto select(String mbrId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT * FROM MEMBER WHERE MBRID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mbrId);
            rs = pstmt.executeQuery();
            MemberDto mbrDto = null;
            if (rs.next()) {
                mbrDto = new MemberDto(
                        rs.getInt("MBRNO"),
                        rs.getString("MBRNAME"),
                        rs.getString("MBRTEL"),
                        rs.getString("MBRADR"),
                        rs.getString("MBREMAIL"),
                        rs.getString("MBRID"),
                        rs.getString("MBRPWD"),
                        rs.getDate("MBRBD"),
                        rs.getDate("REGDATE"),
                        rs.getString("GRADE"),
                        rs.getInt("TOTAMT"));
                return mbrDto;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }

    public ArrayList<MemberDto> selectAll() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT * FROM MEMBER ORDER BY MBRNO";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ArrayList<MemberDto> mbrDtos = new ArrayList<>();
            while (rs.next()) {
                mbrDtos.add(
                        new MemberDto(
                                rs.getInt("MBRNO"),
                                rs.getString("MBRNAME"),
                                rs.getString("MBRTEL"),
                                rs.getString("MBRADR"),
                                rs.getString("MBREMAIL"),
                                rs.getString("MBRID"),
                                rs.getString("MBRPWD"),
                                rs.getDate("MBRBD"),
                                rs.getDate("REGDATE"),
                                rs.getString("GRADE"),
                                rs.getInt("TOTAMT")));
            }
            return mbrDtos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }
    
    public ArrayList<MemberDto> memberInfo() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT M.MBRNO, MEM.MBRNAME, MEM.MBRID, MEM.MBRTEL, MEM.MBRADR, MEM.MBREMAIL, MEM.MBRBD, MEM.REGDATE, MEM.GRADE, MEM.TOTAMT, C.COND "
            		+ "FROM COUPON C JOIN MBRCOUPON M ON(C.CPNNO = M.CPNNO) JOIN MEMBER MEM ON (M.MBRNO=MEM.MBRNO) ORDER BY M.MBRNO;";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ArrayList<MemberDto> mbrInfoList = new ArrayList<>();
            while (rs.next()) {
            	mbrInfoList.add(
                        new MemberDto(
                                rs.getInt("MBRNO"),
                                rs.getString("MBRNAME"),
                                rs.getString("MBRID"),
                                rs.getString("MBRTEL"),
                                rs.getString("MBRADR"),
                                rs.getString("MBREMAIL"),
                                rs.getDate("MBRBD"),
                                rs.getDate("REGDATE"),
                                rs.getString("GRADE"),
                                rs.getInt("TOTAMT"),
                                rs.getString("COND")));
            }
            return mbrInfoList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }
    
    public ArrayList<MemberDto> selectFromTo(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT M.MBRNO, MEM.MBRNAME, MEM.MBRID, MEM.MBRTEL, MEM.MBRADR, MEM.MBREMAIL, MEM.MBRBD, MEM.REGDATE, MEM.GRADE, MEM.TOTAMT, C.COND "
					   + "FROM(SELECT MEMBER.*, ROW_NUMBER() OVER (ORDER BY MBRNO) AS ROW_NUM "
					   + 	  "FROM (SELECT * "
					   + 	  "FROM MEMBER MEM INNER JOIN BRANCH BR "
					   +      "ON (BR.BRNO = MGR.BRNO)) MGRBR) "
					   + "WHERE ROW_NUM BETWEEN ? AND ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<MemberDto> mbrInfoList = new ArrayList<>();
			while(rs.next()) {
//				mbrInfoList.add(
//						new ManagerInfoDto(
//								rs.getInt("MGRNO"),
//								rs.getString("MGRID"),
//								rs.getString("MGRIMG"),
//								rs.getString("BRNAME"),
//								rs.getString("BRTEL"),
//								rs.getString("BRADDR"),
//								rs.getString("MGRNAME"),
//								rs.getString("MGRTEL"),
//								rs.getString("MGREMAIL")
//							)
//						);
			}
			return mbrInfoList;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
    }
    
    public MemberDto isMember(String id, String pwd) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT * FROM MEMBER WHERE MBRID=? AND MBRPWD=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int num = rs.getInt("MBRNO");
                String name = rs.getString("MBRNAME");
                String tel = rs.getString("MBRTEL");
                String addr = rs.getString("MBRADR");
                String email = rs.getString("MBREMAIL");
                id = rs.getString("MBRID");
                pwd = rs.getString("MBRPWD");
                Date bd = rs.getDate("MBRBD");
                Date regdate = rs.getDate("REGDATE");
                String grade = rs.getString("GRADE");
                int totamt = rs.getInt("TOTAMT");
                MemberDto dto = new MemberDto(num, name, tel, addr, email, id, pwd, bd, regdate, grade, totamt);
                return dto;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }

    public int checkId(String id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = -1; // 오류
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT * FROM MEMBER WHERE MBRID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = 1;// 아이디가 존재할 경우
            } else {
                result = 0;// 사용가능한 아이디
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return result;
    }

    public String searchId(String email, String tel) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String id = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT MBRID FROM MEMBER WHERE MBREMAIL=? AND MBRTEL=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, tel);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getString("MBRID");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return id;
    }

    public String searchPwd(String id, String email, String tel) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String pwd = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT MBRPWD FROM MEMBER WHERE MBRID=? AND MBREMAIL=? AND MBRTEL=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, tel);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pwd = rs.getString("MBRPWD");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return pwd;
    }

}
