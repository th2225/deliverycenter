package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MenuVO;

public class MenuDAO {

	// 1. ���� ����
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = [url];
	String user = [user];
	String password = [password];
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public MenuDAO() {

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

	// �޴���� �����ֱ�(���Կ�) - StoreMenuSetting
	public ArrayList<MenuVO> selectMenuByStore(String id) {
		sb.setLength(0);
		sb.append("SELECT M.Mno, M.Mname, M.Mprice, M.Msoldout, M.Mdetail ");
		sb.append("FROM STORE S, MENU M ");
		sb.append("WHERE M.Sno = S.Sno AND S.Sid = ? ");

		ArrayList<MenuVO> list = new ArrayList<MenuVO>();

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			// 6. ����(select --> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ���� ó�� ==> select �� �� ���� �ۼ�
			while (rs.next()) {
				int mno = rs.getInt("mno");
				String mname = rs.getString("mname");
				int mprice = rs.getInt("mprice");
				String msoldout = rs.getString("msoldout");
				String mdetail = rs.getString("mdetail");

				MenuVO vo = new MenuVO(mno, mname, mprice, msoldout, mdetail);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// �޴���ȸ - StoreMenuSetting
	public MenuVO selectOne(int mno, String sid) {
		sb.setLength(0);
		sb.append("SELECT * FROM MENU ");
		sb.append("WHERE Mno = ? ");
		sb.append("and Sno = (SELECT Sno FROM STORE WHERE Sid = ?) ");

		MenuVO vo = null;
		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, mno);
			pstmt.setString(2, sid);

			// 6. ����(select --> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ���� ó�� ==> select �� �� ���� �ۼ�
			while (rs.next()) {
				int mno2 = rs.getInt("mno");
				String mname = rs.getString("mname");
				int mprice = rs.getInt("mprice");
				String msoldout = rs.getString("msoldout");
				String mdetail = rs.getString("mdetail");

				vo = new MenuVO(mno2, mname, mprice, msoldout, mdetail);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			vo = null;
		}
		return vo;
	}

	// �޴��߰� - StoreMenuSetting
	public void insertOne(int sno, String mname, int mprice, String mdetail) {
		sb.setLength(0);
		sb.append("INSERT INTO MENU(Mno, Sno, Mname, Mprice, Msoldout, Mdetail ) ");
		sb.append("VALUES(null, ?, ?, ?, 'N', ? ) ");

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, sno);
			pstmt.setString(2, mname);
			pstmt.setInt(3, mprice);
			pstmt.setString(4, mdetail); // �������� NULL�ֱ�

			// 6. ����(select --> ResultSet)
			pstmt.executeUpdate();
			System.out.println("�޴� �߰� ����");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�޴� �߰� ����");
		}
	}

	// �޴����� - StoreMenuSetting
	public void updateOne(String mname, int mprice, String mdetail, String msoldout, int mno, String sid) {
		sb.setLength(0);
		sb.append("UPDATE MENU ");
		sb.append("SET Mname = ? , ");
		sb.append("Mprice = ? , ");
		sb.append("Mdetail = ? , ");
		sb.append("Msoldout = ? ");
		sb.append("WHERE Mno = ? ");
		sb.append("and Sno = (SELECT Sno FROM STORE WHERE Sid = ?) ");

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, mname);
			pstmt.setInt(2, mprice);
			pstmt.setString(3, mdetail);
			pstmt.setString(4, msoldout);
			pstmt.setInt(5, mno);
			pstmt.setString(6, sid);

			// 6. ����(select --> ResultSet)
			pstmt.executeUpdate();
			System.out.println("�޴� ���� ����");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�޴� ���� ����");
		}
	}

	// �޴����� - StoreMenuSetting
	public void deleteOne(int mno, String sid) {
		sb.setLength(0);
		sb.append("DELETE FROM MENU ");
		sb.append("WHERE Mno = ? ");
		sb.append("AND Sno = (SELECT Sno FROM STORE WHERE Sid = ?) ");

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, mno);
			pstmt.setString(2, sid);

			// 6. ����(select --> ResultSet)
			pstmt.executeUpdate();
			System.out.println("�޴� ���� ����");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�޴� ���� ����");
		}
	}

	// �޴����� - StoreInfo
	public void deleteAll(String sid) {
		sb.setLength(0);
		sb.append("DELETE FROM MENU ");
		sb.append("WHERE Sno = (SELECT Sno FROM STORE WHERE Sid = ?) ");

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, sid);

			// 6. ����(select --> ResultSet)
			pstmt.executeUpdate();
			System.out.println("�޴� ���� ����");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�޴� ���� ����");
		}
	}

	// ���� �޴���� �����ֱ�(�մԿ�) - CustomerMenu
	public ArrayList<MenuVO> showMenuByStore(int sno) {
		sb.setLength(0);
		sb.append("SELECT Mno, Mname, Mprice, Msoldout, Mdetail ");
		sb.append("FROM MENU ");
		sb.append("WHERE Sno = ? ");

		ArrayList<MenuVO> list = new ArrayList<MenuVO>();

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, sno);

			// 6. ����(select --> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ���� ó�� ==> select �� �� ���� �ۼ�
			while (rs.next()) {
				int mno = rs.getInt("mno");
				String mname = rs.getString("mname");
				int mprice = rs.getInt("mprice");
				String msoldout = rs.getString("msoldout");
				String mdetail = rs.getString("mdetail");

				MenuVO vo = new MenuVO(mno, mname, mprice, msoldout, mdetail);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
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
