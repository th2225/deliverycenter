package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.CustomerVO;
import vo.OrderedVO;

public class CustomerDAO {

	// 1. ���� ����
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = [url];
	String user = [user];
	String password = [password];
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	Boolean isUpdate = false;
	Boolean isDelete = false;

	public CustomerDAO() {

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

	// �α��ΰ˻�
	public CustomerVO selectOne(String id) {
		// 4. SQL�� �ۼ�
		sb.setLength(0);
		sb.append("SELECT Cid, Cpw FROM CUSTOMER WHERE Cid = ? ");

		CustomerVO vo = null;

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			// 6. ���� (select ---> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ����ó��
			while (rs.next()) {
				String cid = rs.getString("Cid");
				String cpw = rs.getString("Cpw");

				vo = new CustomerVO(cid, cpw);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// ���̵� �ߺ� �˻�
	public boolean idConfirm(String id) {
		boolean isExist = false;

		sb.setLength(0);
		sb.append("SELECT Cid from CUSTOMER WHERE Cid = ? ");

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
		sb.append("INSERT INTO CUSTOMER ");
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

	// �н����� ���� ���� - ����
	public boolean chWithPW(int cno, String cname, String cphone, String cemail, String caddress, String newPasswd) {
		// sb �ʱ�ȭ
		sb.setLength(0);

		sb.append("update CUSTOMER set Cname = ?, Cphone = ?, Cemail = ?, Caddress = ?, Cpw = ? ");
		sb.append("where cno = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, cname);
			pstmt.setString(2, cphone);
			pstmt.setString(3, cemail);
			pstmt.setString(4, caddress);
			pstmt.setString(5, newPasswd);
			pstmt.setInt(6, cno);

			if (pstmt.executeUpdate() != 0) {
				isUpdate = true;
//					System.out.println("������Ʈ ����");
				return isUpdate;
			} else {
//					System.out.println("������Ʈ ����");
				return isUpdate;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return isUpdate;
		}

	}

	// �н����� ���� ������ - ����
	public boolean chWithOutPW(int cno, String cname, String cphone, String cemail, String caddress) {
		// sb �ʱ�ȭ
		sb.setLength(0);

		sb.append("update CUSTOMER set Cname = ?, Cphone = ?, Cemail = ?, Caddress = ? ");
		sb.append("where cno = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, cname);
			pstmt.setString(2, cphone);
			pstmt.setString(3, cemail);
			pstmt.setString(4, caddress);
			pstmt.setInt(5, cno);

			if (pstmt.executeUpdate() != 0) {
				isUpdate = true;
//					System.out.println("������Ʈ ����");
				return isUpdate;
			} else {
//					System.out.println("������Ʈ ����");
				return isUpdate;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return isUpdate;
		}
	}

	// ȸ�� Ż�� - ����
	public boolean deleteCustomer(int cno) {
		sb.setLength(0);

		sb.append("delete from CUSTOMER where Cno = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, cno);

			if (pstmt.executeUpdate() == 1) {
				isDelete = true;
				
			}
			return isDelete;

		} catch (SQLException e) {
			e.printStackTrace();
			return isDelete;
		}

	}

	// OrderList ���
	public ArrayList<OrderedVO> customerOrderList(int cno) {
		ArrayList<OrderedVO> list = new ArrayList<>();
		sb.setLength(0);

//			SELECT S.SNAME, M.MNAME, Oamount, Oprice, Odate, Ostatus
//			from ORDERED O, MENU M, STORE S
//			WHERE S.SNO = O.SNO
//			AND M.MNO = O.MNO
//			AND O.CNO = 2;

		sb.append(
				"SELECT DISTINCT Ono, S.SNAME, Orderlist, Oamount, Oprice, Odate, Ostatus, Opayment, Oaddress, Ophone  from ORDERED O NATURAL JOIN STORE S ");
		sb.append("WHERE O.CNO = ?");

		try {
			pstmt = conn.prepareStatement(String.valueOf(sb));
			pstmt.setInt(1, cno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int ono = rs.getInt("Ono");
				String sname = rs.getString("S.SNAME");
				int oamount = rs.getInt("Oamount");
				int oprice = rs.getInt("Oprice");
				String odate = rs.getString("Odate");
				String ostatus = null;

				if (rs.getInt("Ostatus") == 1) {
					ostatus = "�ֹ��Ϸ�";
				} else if (rs.getInt("Ostatus") == 2) {
					ostatus = "�ֹ��Ϸ�";
				} else if (rs.getInt("Ostatus") == 3) {
					ostatus = "�ֹ��Ϸ�";
				}

				String opayment = rs.getString("Opayment");
				String oaddress = rs.getString("Oaddress");
				String ophone = rs.getString("Ophone");
				String orderlist = rs.getString("Orderlist");

				list.add(new OrderedVO(ono, sname, oamount, oprice, odate, ostatus, opayment, oaddress, ophone,
						orderlist));

			}

			return list;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	// CustomerOrder�� �ʿ��� �޼��� - ����
	public CustomerVO findCnoCaddressCphone(String id) {
		// 4. SQL�� �ۼ�
		sb.setLength(0);
		sb.append("SELECT Cno, Caddress, Cphone FROM CUSTOMER WHERE Cid = ? ");

		CustomerVO vo = null;

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			// 6. ���� (select ---> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ����ó��
			while (rs.next()) {
				int cno = rs.getInt("Cno");
				String caddress = rs.getString("Caddress");
				String cphone = rs.getString("Cphone");

				vo = new CustomerVO(cno, caddress, cphone);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// id�� cno ã�� - CustomerMain
	public int findCno(String id) {
		// 4. sql�� �ۼ�
		sb.setLength(0);
		sb.append("SELECT Cno FROM CUSTOMER where Cid = ? ");

		int cno = 0;

		try {
			// 5. ���� ��ü ����
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);

			// 6. ����(select --> ResultSet)
			rs = pstmt.executeQuery();

			// 7. ���ڵ庰�� ���� ó�� ==> select �� �� ���� �ۼ�
			rs.next();
			cno = rs.getInt("cno");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cno;
	}

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
