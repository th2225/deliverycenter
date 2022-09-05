package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.StoreVO;

public class StoreDAO {

	// 1. ���� ����
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = [url];
	String user = [user];
	String password = [password];
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public StoreDAO() {

		try {
			// 2. JDBC ����̹� �ε��Ǿ� �ִ��� ���� üũ
			Class.forName(driver);
			// 3. ����(Connection)
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("db ���� ����");
			e.printStackTrace();
		}
	}

	// ���̵� �ߺ� �˻�
	public boolean idConfirm(String id) {
		boolean isExist = false;

		sb.setLength(0);
		sb.append("SELECT Sid from STORE WHERE Sid = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				isExist = true;
			} else {
				isExist = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}
	
	// ȸ������
		public void register(String id, String pw, String name, String address, String phone, String pwq, String pwa,
				String email) {
			sb.setLength(0);
			sb.append("INSERT INTO STORE(Sno, Sid, Spw, Sname, Saddress, Sphone, Spw_question, Spw_answer, Semail) ");
			sb.append("VALUES(null, ?, ?, ?, ?, ?, ?, ?, ? ) ");

			
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setString(4, address);
				pstmt.setString(5, phone);
				pstmt.setString(6, pwq);
				pstmt.setString(7, pwa);
				pstmt.setString(8, email);
				
				pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	// ȸ�� Ż�� -- �ν�
	public int AccountDelete(String sid) {
		int result = 0;
		try {
			String SQL = "DELETE FROM STORE WHERE Sid = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sid);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// ȸ�� ���� ����(������Ʈ) -- �ν�
	public int AccountUpdate(String sid, String scategory, String sname, String sphone, String semail, String spw,
			String saddress, String sruntime, String sofftime, String soffday, int sdeliveryfee, int sminoprice) {
		int result = 0;
		try {
			String SQL = "UPDATE STORE SET Scategory = ?, Sname = ?, Sphone = ?, Semail = ?, Spw = ?, Saddress = ?, Sruntime = ?, Sofftime = ?, Soffday = ?, Sdeliveryfee = ?, Sminoprice = ? WHERE Sid = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, scategory);
			pstmt.setString(2, sname);
			pstmt.setString(3, sphone);
			pstmt.setString(4, semail);
			pstmt.setString(5, spw);
			pstmt.setString(6, saddress);
			pstmt.setString(7, sruntime);
			pstmt.setString(8, sofftime);
			pstmt.setString(9, soffday);
			pstmt.setInt(10, sdeliveryfee);
			pstmt.setInt(11, sminoprice);
			pstmt.setString(12, sid);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// ����� ���� �������� Ȯ���ϰ� �����̸� ����Ǿ� �ִ� �� �ֱ����� �ʿ��� �޼��� -- �ν�
	public List<StoreVO> blankCheck(String sid) {
		List<StoreVO> result = new ArrayList<StoreVO>();

		try {
			String SQL = "SELECT Scategory, Sname, Sphone, Semail, Spw, Saddress, Sruntime, Sofftime, Soffday, Sdeliveryfee, Sminoprice FROM STORE WHERE Sid = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sid);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				StoreVO vo = new StoreVO();
				vo.setScategory(rs.getString(1));
				vo.setSname(rs.getString(2));
				vo.setSphone(rs.getString(3));
				vo.setSemail(rs.getString(4));
				vo.setSpw(rs.getString(5));
				vo.setSaddress(rs.getString(6));
				vo.setSruntime(rs.getString(7));
				vo.setSofftime(rs.getString(8));
				vo.setSoffday(rs.getString(9));
				vo.setSdeliveryfee(rs.getInt(10));
				vo.setSminoprice(rs.getInt(11));

				result.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public StoreVO selectOne(String id) {
		// 4. sql�� �ۼ�
		sb.setLength(0);
		sb.append("SELECT Sid, Spw FROM STORE where Sid = ? ");

		StoreVO vo = null;

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			// 6. ����(select --> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ���� ó�� ==> select �� �� ���� �ۼ�
			if (rs.next()) {
				String sid = rs.getString("sid");
				String spw = rs.getString("spw");

				vo = new StoreVO(sid, spw);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// id�� sno ã�� - StoreMenuSetting
	public int findSno(String id) {
		// 4. sql�� �ۼ�
		sb.setLength(0);
		sb.append("SELECT Sno FROM STORE where Sid = ? ");

		int sno = 0;

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			// 6. ����(select --> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ���� ó�� ==> select �� �� ���� �ۼ�
			rs.next();
			sno = rs.getInt("sno");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sno;
	}

	// ���� ����Ʈ ��� - CustomerMenuList
	public ArrayList<StoreVO> selectCategory(String category) {
		// 4. sql�� �ۼ�
		sb.setLength(0);
		sb.append("SELECT Sno, Sname, Sminoprice, Sdeliveryfee FROM STORE where Scategory = ? ");

		ArrayList<StoreVO> list = new ArrayList<StoreVO>();

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, category);

			// 6. ����(select --> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ���� ó�� ==> select �� �� ���� �ۼ�
			while (rs.next()) {
				int sno = rs.getInt("sno");
				String sname = rs.getString("sname");
				int sminoprice = rs.getInt("sminoprice");
				int sdeliveryfee = rs.getInt("sdeliveryfee");

				StoreVO vo = new StoreVO(sno, sname, sminoprice, sdeliveryfee);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	// sno�� sname ã��(�մ�) - CustomerMenu
	public String findSname(int sno) {
		// 4. sql�� �ۼ�
		sb.setLength(0);
		sb.append("SELECT Sname FROM STORE where Sno = ? ");

		String sname = null;

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, sno);

			// 6. ����(select --> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ���� ó�� ==> select �� �� ���� �ۼ�
			rs.next();
			sname = rs.getString("sname");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sname;
	}

	// �ڿ��ݳ�
	public void close() {
		try {
			// 8. �ڿ��ݳ�
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
