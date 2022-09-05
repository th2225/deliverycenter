package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import vo.OrderedVO;


public class OrderedDAO {

	// 1. ���� ����
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = [url];
	String user = [user];
	String password = [password];
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public OrderedDAO() {

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

	// CustomerOrder �� �ʿ��� �޼���
	public void insertOrder(int cno, String orderlist, int sno, int oamount, int oprice, String opayment, String oaddress,
			String ophone) {
		LocalDate now = LocalDate.now();
		String time = now.toString();

		sb.setLength(0);
		sb.append("INSERT INTO ORDERED (Ono, Cno, Orderlist, Sno, Oamount, Oprice, Odate,Ostatus, Opayment, Oaddress, Ophone )");
		sb.append("VALUES (null, ?, ?, ?, ?, ?, ?, 1, ?,?,? )");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, cno);
			pstmt.setString(2, orderlist);
			pstmt.setInt(3, sno);
			pstmt.setInt(4, oamount);
			pstmt.setInt(5, oprice);
			pstmt.setString(6, time);
			pstmt.setString(7, opayment);
			pstmt.setString(8, oaddress);
			pstmt.setString(9, ophone);

			pstmt.executeUpdate();
			System.out.println("�ֹ� ����");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�ֹ� ����");
		}

	}

	// OrderList ���
	public ArrayList<OrderedVO> storeOrderList(int sno){
		ArrayList<OrderedVO> list = new ArrayList<>();
		sb.setLength(0);

//		SELECT S.SNAME, M.MNAME, Oamount, Oprice, Odate, Ostatus
//		from ORDERED O, MENU M, STORE S
//		WHERE S.SNO = O.SNO
//		AND M.MNO = O.MNO
//		AND O.CNO = 2;

		
		sb.append("SELECT DISTINCT Ono, S.SNAME, Orderlist, Oamount, Oprice, Odate, Ostatus, Opayment, Oaddress, Ophone  from ORDERED O NATURAL JOIN STORE S ");
		sb.append("WHERE O.SNO = ?");

		try {
			pstmt = conn.prepareStatement(String.valueOf(sb));
			pstmt.setInt(1, sno);

			rs = pstmt.executeQuery();

			while(rs.next()){

				int ono = rs.getInt("Ono");
				String sname = rs.getString("S.SNAME");
				int oamount = rs.getInt("Oamount");
				int oprice = rs.getInt("Oprice");
				String odate = rs.getString("Odate");
				String ostatus = null;

				if(rs.getInt("Ostatus") == 1){
					ostatus = "�ֹ��Ϸ�";
				}else if(rs.getInt("Ostatus") == 2){
					ostatus = "�ֹ��Ϸ�";
				}else if(rs.getInt("Ostatus") == 3){
					ostatus = "�ֹ��Ϸ�";
				}
				
				String opayment = rs.getString("Opayment");
				String oaddress = rs.getString("Oaddress");
				String ophone = rs.getString("Ophone");
				String orderlist = rs.getString("Orderlist");
				
				list.add(new OrderedVO(ono, sname, oamount, oprice, odate, ostatus, opayment, oaddress, ophone, orderlist));

			}

			return list;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}
	
	//������ �߰� insertOrder
		public boolean insertOrder(int cno, int sno, int oamount, int oprice,  String opayment, String oaddress, String ophone, String orderlist) {

			boolean isInsert = false;
			try {
				// Odate �� �ش��ϴ� ���� ��¥ ���ϱ� => nowdate
				LocalDate now = LocalDate.now();
				String nowdate = now.toString();
				
				// 1. sb �ʱ�ȭ �� Cno �� ���ϱ�

//				System.out.println("Cno : "+cno);
//				System.out.println("Mno : "+mno);
//				System.out.println("Sno : "+sno);

				// 3. Ordered �� insert
				sb.setLength(0);
				
				// ������� Ono, Cno, Sno, Oamount, Oprice, Odate, Ostatus, Opayment, Oaddress, Ophone, Orderlist
				sb.append("INSERT INTO ORDERED (Ono, Cno, Sno, Oamount, Oprice, Odate, Opayment, Oaddress, Ophone, Orderlist, Ostatus)");
				sb.append("VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)");
				pstmt = conn.prepareStatement(String.valueOf(sb));
				
				pstmt.setInt(1, cno);
				pstmt.setInt(2, sno);
				pstmt.setInt(3, oamount);
				pstmt.setInt(4, oprice);
				pstmt.setString(5, nowdate);
				pstmt.setString(6, opayment);
				pstmt.setString(7, oaddress);
				pstmt.setString(8, ophone);
				pstmt.setString(9, orderlist);
				
				// ������ ���� Ȯ�� -> ������ ��� ��â ���� ���� �� �մϴ�.
				if (pstmt.executeUpdate() == 1){
					isInsert = true;
					System.out.println("Insert Complete");

					return isInsert;
				}

				System.out.println("Insert fail");
				return isInsert;


			} catch (SQLException e) {
				e.printStackTrace();
				return isInsert;
			}
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
