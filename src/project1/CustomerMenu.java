
package project1;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import dao.MenuDAO;
import dao.StoreDAO;
import vo.MenuVO;

@SuppressWarnings("serial")
public class CustomerMenu extends JFrame implements ActionListener, MouseListener {
	RoundedButtonW rbtnOrder, rbtnBacktoMenu, rbtnCart;
	JButton jbtnBack;
	JButton[] jbtnlist;
	JLabel jlbTitle, jlbUserId, jlbStoreName, jlbPrice;
	JTextArea jtaOrderList;
	JPanel jpTop, jpCenter, jpBottom, jpButtonList, jpOrderList;
	JScrollPane jspList;
	ImageIcon imgBack;

	CardLayout cl;

	String id = null;
	String category = null;
	String sname = null;
	int sno = 0;
	int priceSum = 0;
	int oderCount = 0;

	public CustomerMenu(String id, int sno, String category) {
		this.id = id;
		this.sno = sno;
		this.category = category;

		// â ������ sno�� ���� �̸� ã�ƿ� ���� ����
		StoreDAO dao = new StoreDAO();
		sname = dao.findSname(sno);

		// ������Ʈ �ʱ�ȭ
		setLayout(null);
		jpTop = new JPanel(); // ��� ����
		jpTop.setLayout(null);

		jpCenter = new JPanel(); // ��ũ�� �г� �� �ڸ�
		cl = new CardLayout();
		jpCenter.setLayout(cl);

		jpBottom = new JPanel(); // ��ũ�� �г� �� �ڸ�
		jpBottom.setLayout(null);// �ڷ�, ��ٱ���, �ֹ��ϱ� �� �ڸ�

		jpButtonList = new JPanel(); // ��ư����Ʈ �� �ڸ�
		jpButtonList.setLayout(new GridLayout(0, 1));

		jpOrderList = new JPanel(); // �ֹ� ���̴� �г�
		jpOrderList.setLayout(new BorderLayout());

		jtaOrderList = new JTextArea(); // �ֹ� ���

		jspList = new JScrollPane(jpButtonList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		jbtnBack = new JButton();
		rbtnCart = new RoundedButtonW("카트보기");
		rbtnBacktoMenu = new RoundedButtonW("메뉴보기");
		rbtnOrder = new RoundedButtonW("주문하기");

		jlbUserId = new JLabel(id + "님", SwingConstants.RIGHT);
		jlbTitle = new JLabel("배달의 중앙", SwingConstants.CENTER);
		jlbStoreName = new JLabel("점포명 : " + sname, SwingConstants.CENTER);
		jlbPrice = new JLabel("", SwingConstants.RIGHT); // �ֹ� �հ�


		// ����â���� ��ư ������ �޴�����Ʈ ����
		MenuDAO daoM = new MenuDAO();
		ArrayList<MenuVO> list = daoM.showMenuByStore(sno);
		dao.close();

		jbtnlist = new JButton[list.size()];

		for (int i = 0; i < list.size(); i++) {
			jbtnlist[i] = new JButton(
					"<html>" + ("메뉴명 : " + list.get(i).getMname() + ",  가격 : " + list.get(i).getMprice() + "\n설명 : "
							+ list.get(i).getMdetail()).replaceAll("\\n", "<br>") + "<html>");
			jbtnlist[i].addActionListener(this);
			jbtnlist[i].setBackground(Color.white);
			if("Y".equals(list.get(i).getMsoldout())) {
				jbtnlist[i].setEnabled(false);
			}
			jpButtonList.add(jbtnlist[i]);
		}

		// ��Ʈ ����
		Font f = new Font("고딕체", Font.BOLD, 20);
		Font f1 = new Font("고딕체", Font.BOLD, 15);
		Font f2 = new Font("고딕체", Font.PLAIN, 15);
		jlbTitle.setFont(f);
		jlbPrice.setFont(f);
		jlbUserId.setFont(f1);
		jlbStoreName.setFont(f2);
		rbtnBacktoMenu.setFont(f1);
		rbtnCart.setFont(f1);
		rbtnOrder.setFont(f1);

		// �̹��� ����
		imgBack = new ImageIcon("src/images/arrow.jpg");
		jbtnBack.setIcon(imgBack);

		// ���� ����
		jpTop.setBackground(Color.white);
		jpCenter.setBackground(Color.white);
		jpBottom.setBackground(Color.white);

		// ���� ����
		jbtnBack.setBorderPainted(false);

		// ������Ʈ ��ġ
		jbtnBack.setBounds(30, 20, 50, 50);
		jlbTitle.setBounds(135, 30, 150, 30);
		jlbUserId.setBounds(290, 30, 100, 30);
		jlbStoreName.setBounds(90, 90, 250, 30);

		jpTop.setBounds(0, 0, 450, 130);
		jpCenter.setBounds(0, 130, 440, 370);
		jpBottom.setBounds(0, 500, 440, 150);

		rbtnBacktoMenu.setBounds(61, 30, 93, 30);
		rbtnCart.setBounds(173, 30, 93, 30);
		rbtnOrder.setBounds(286, 30, 93, 30);

		// �̺�Ʈ �߰�
		jbtnBack.addActionListener(this);
		rbtnBacktoMenu.addActionListener(this);
		rbtnCart.addActionListener(this);
		rbtnOrder.addActionListener(this);
		
		jbtnBack.addMouseListener(this);
		rbtnBacktoMenu.addMouseListener(this);
		rbtnCart.addMouseListener(this);
		rbtnOrder.addMouseListener(this);

		// ������Ʈ ����
		jpTop.add(jbtnBack);
		jpTop.add(jlbUserId);
		jpTop.add(jlbTitle);
		jpTop.add(jlbStoreName);

		jpOrderList.add(jtaOrderList, "Center");
		jpOrderList.add(jlbPrice, "South");

		jpCenter.add(jspList, "BacktoMenu");
		jpCenter.add(jpOrderList, "Cart");

		jpBottom.add(rbtnBacktoMenu);
		jpBottom.add(rbtnCart);
		jpBottom.add(rbtnOrder);

		add(jpTop);
		add(jpCenter);
		add(jpBottom);

		setTitle("배달의 중앙 by snippets");
		setBounds(0, 0, 450, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	// �������� �����ϸ� �ּ�ó��
	public static void main(String[] args) {
		 new CustomerMenu("imsolo", 2, "�ѽ�");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		Object obj = e.getSource();

		if (clicked == jbtnBack) { // <-

			int result = JOptionPane.showConfirmDialog(this, "창을 나가면 카트가 초기화 됩니다. 계속 진행하시겠습니까?", "확인",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				jtaOrderList.setText("");
				new CustomerMenuList(id, category);
				this.dispose();
			}

		} else if (obj == rbtnBacktoMenu) { // �޴����

			cl.show(jpCenter, "BacktoMenu");

		} else if (obj == rbtnCart) { // īƮ����

			cl.show(jpCenter, "Cart");

		} else if (obj == rbtnOrder) { // �ֹ��ϱ�
			String finalOrder = jtaOrderList.getText();
			 new CustomerOrder(id, sno, finalOrder, oderCount, priceSum, category);
			// �ֹ��ϱ� ������ ���� �� �Ѱ��ְ� ��� �ʱ�ȭ
			oderCount = 0;
			priceSum = 0;
			jtaOrderList.setText("");
			jlbPrice.setText("");
			this.dispose();

		} else {
			// ��ư���� ���ʿ��� ��ȣ ���� �� �޴���� ���� �ؽ�Ʈ ��������
			String newchar = clicked.getText().replace("<br>", "@");
			String newchar2 = newchar.replace("<html>", "");
			int findAt = newchar2.indexOf("@");
			String newcharFinal = newchar2.substring(0, findAt);

			// ���ݸ� ���� ���� �հ� �����
			int findColon = newchar2.indexOf(",");
			String price = newchar2.substring(findColon + 8, findColon + 13);
			String priceRemoveSeol = price.replace("설", "");
			String priceRemoveAt = priceRemoveSeol.replace("@", "");
			priceSum += Integer.parseInt(priceRemoveAt);

			jtaOrderList.append("\n      " + newcharFinal);
			jlbPrice.setText("합계 : " + priceSum + " 원   ");
			oderCount++;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();
		((Component) obj).setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
