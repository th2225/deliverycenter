package project1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.CustomerDAO;


@SuppressWarnings("serial")
public class CustomerMain extends JFrame implements ActionListener, MouseListener {
	JButton jbtnKo, jbtnBn, jbtnJp, jbtnCn, jbtnWs, jbtnDs, jbtnMy, jbtnMyPage, jbtnLogout, jbtnOrderlist;
	RoundedButtonW jlbKo, jlbBn, jlbJp, jlbCn, jlbWs, jlbDs;
	JLabel jlbTitle, jlbUserId;
	JPanel jp, jpMy, jpKo, jpBn, jpCn, jpJp, jpWs, jpDs;
	ImageIcon imgKo, imgBn, imgJp, imgCn, imgWs, imgCk, imgPz, imgDs, imgMy;
	
	boolean flag = true;
	String id = null;
	int cno = 0;

	public CustomerMain(String id) {
		this.id = id;
		
		CustomerDAO dao = new CustomerDAO();
		cno = dao.findCno(id);

		setLayout(null);

		// ������Ʈ �ʱ�ȭ
		jp = new JPanel();
		jp.setLayout(null);

		jpMy = new JPanel();
		jpMy.setLayout(null);
		jpMy.setVisible(false);

		jpKo = new JPanel();
		jpKo.setLayout(null);
		jpBn = new JPanel();
		jpBn.setLayout(null);
		jpJp = new JPanel();
		jpJp.setLayout(null);
		jpCn = new JPanel();
		jpCn.setLayout(null);
		jpWs = new JPanel();
		jpWs.setLayout(null);
		jpDs = new JPanel();
		jpDs.setLayout(null);

		jbtnKo = new JButton();
		jbtnBn = new JButton();
		jbtnJp = new JButton();
		jbtnCn = new JButton();
		jbtnWs = new JButton();
		jbtnDs = new JButton();
		jbtnMy = new JButton();

		jbtnMyPage = new JButton("내정보");
		jbtnOrderlist = new JButton("주문정보");
		jbtnLogout = new JButton("로그아웃");

		jlbTitle = new JLabel("배달의 중앙", SwingConstants.CENTER);
		jlbUserId = new JLabel(id + "님", SwingConstants.LEFT);
		jlbKo = new RoundedButtonW("한식");
		jlbBn = new RoundedButtonW("분식");
		jlbJp = new RoundedButtonW("일식");
		jlbCn = new RoundedButtonW("중식");
		jlbWs = new RoundedButtonW("양식");
		jlbDs = new RoundedButtonW("디저트");

		// �̹��� ����
		imgKo = new ImageIcon("src/images/korean.jpg");
		imgBn = new ImageIcon("src/images/snack.jpg");
		imgJp = new ImageIcon("src/images/japanese.jpg");
		imgCn = new ImageIcon("src/images/chinese.jpg");
		imgWs = new ImageIcon("src/images/western.jpg");
		imgDs = new ImageIcon("src/images/dessert.jpg");
		imgMy = new ImageIcon("src/images/usericon.jpg");
		jbtnMy.setIcon(imgMy);
		jbtnKo.setIcon(imgKo);
		jbtnBn.setIcon(imgBn);
		jbtnJp.setIcon(imgJp);
		jbtnCn.setIcon(imgCn);
		jbtnWs.setIcon(imgWs);
		jbtnDs.setIcon(imgDs);

		// ��Ʈ ����
		Font fTitle = new Font("고딕체", Font.BOLD, 20);
		Font fUserId = new Font("고딕체", Font.BOLD, 15);
		Font f = new Font("고딕체", Font.BOLD, 13);
		Font fCategory = new Font("고딕체", Font.BOLD, 15);
		jlbTitle.setFont(fTitle);
		jlbUserId.setFont(fUserId);
		jbtnMyPage.setFont(f);
		jbtnOrderlist.setFont(f);
		jlbKo.setFont(fCategory);
		jlbBn.setFont(fCategory);
		jlbJp.setFont(fCategory);
		jlbCn.setFont(fCategory);
		jlbWs.setFont(fCategory);
		jlbDs.setFont(fCategory);

		// ���� ����
		jp.setBackground(Color.white);
		jpMy.setBackground(Color.white);
		jpKo.setBackground(Color.white);
		jpBn.setBackground(Color.white);
		jpJp.setBackground(Color.white);
		jpCn.setBackground(Color.white);
		jpWs.setBackground(Color.white);
		jpDs.setBackground(Color.white);

		jbtnMy.setBackground(Color.white);
		jbtnMyPage.setBackground(Color.black);
		jbtnOrderlist.setBackground(Color.black);
		jbtnLogout.setBackground(Color.black);

		jlbKo.setBackground(Color.black);
		jlbBn.setBackground(Color.black);
		jlbJp.setBackground(Color.black);
		jlbCn.setBackground(Color.black);
		jlbWs.setBackground(Color.black);
		jlbDs.setBackground(Color.black);

		// ���ڻ� ����
		jbtnMyPage.setForeground(Color.white);
		jbtnOrderlist.setForeground(Color.white);
		jbtnLogout.setForeground(Color.white);

		jlbKo.setForeground(Color.white);
		jlbBn.setForeground(Color.white);
		jlbJp.setForeground(Color.white);
		jlbCn.setForeground(Color.white);
		jlbWs.setForeground(Color.white);
		jlbDs.setForeground(Color.white);

		// ���� ����
		jbtnMy.setBorderPainted(false);
		jbtnKo.setBorderPainted(false);
		jbtnBn.setBorderPainted(false);
		jbtnJp.setBorderPainted(false);
		jbtnCn.setBorderPainted(false);
		jbtnWs.setBorderPainted(false);
		jbtnDs.setBorderPainted(false);

		// ������Ʈ ��ġ
		jlbUserId.setBounds(40, 12, 100, 30);
		jlbTitle.setBounds(130, 10, 190, 30);
		jbtnMy.setBounds(375, 10, 24, 24);

		jbtnMyPage.setBounds(0, 0, 85, 27);
		jbtnOrderlist.setBounds(0, 27, 90, 27);
		jbtnLogout.setBounds(0, 54, 85, 27);

		jpKo.setBounds(100, 120, 100, 125);
		jpBn.setBounds(250, 120, 100, 125);
		jpJp.setBounds(100, 280, 100, 125);
		jpCn.setBounds(250, 280, 100, 125);
		jpWs.setBounds(100, 440, 100, 125);
		jpDs.setBounds(250, 440, 100, 125);

		jlbKo.setBounds(0, 0, 100, 25);
		jlbBn.setBounds(0, 0, 100, 25);
		jbtnKo.setBounds(0, 25, 100, 100);
		jbtnBn.setBounds(0, 25, 100, 100);

		jlbJp.setBounds(0, 0, 100, 25);
		jlbCn.setBounds(0, 0, 100, 25);
		jbtnJp.setBounds(0, 25, 100, 100);
		jbtnCn.setBounds(0, 25, 100, 100);

		jlbWs.setBounds(0, 0, 100, 25);
		jlbDs.setBounds(0, 0, 100, 25);
		jbtnWs.setBounds(0, 25, 100, 100);
		jbtnDs.setBounds(0, 25, 100, 100);

		jp.setBounds(0, 0, 440, 650);
		jpMy.setBounds(315, 38, 85, 81);

		// �̺�Ʈ ó��
		jbtnMy.addMouseListener(this);
		jbtnMyPage.addMouseListener(this);
		jbtnOrderlist.addMouseListener(this);
		jbtnLogout.addMouseListener(this);
		jbtnKo.addMouseListener(this);
		jbtnBn.addMouseListener(this);
		jbtnJp.addMouseListener(this);
		jbtnCn.addMouseListener(this);
		jbtnWs.addMouseListener(this);
		jbtnDs.addMouseListener(this);

		jbtnMy.addActionListener(this);
		jbtnMyPage.addActionListener(this);
		jbtnOrderlist.addActionListener(this);
		jbtnLogout.addActionListener(this);
		jbtnKo.addActionListener(this);
		jbtnBn.addActionListener(this);
		jbtnJp.addActionListener(this);
		jbtnCn.addActionListener(this);
		jbtnWs.addActionListener(this);
		jbtnDs.addActionListener(this);

		jlbKo.addActionListener(this);
		jlbBn.addActionListener(this);
		jlbJp.addActionListener(this);
		jlbCn.addActionListener(this);
		jlbWs.addActionListener(this);
		jlbDs.addActionListener(this);
		
		jlbKo.addMouseListener(this);
		jlbBn.addMouseListener(this);
		jlbJp.addMouseListener(this);
		jlbCn.addMouseListener(this);
		jlbWs.addMouseListener(this);
		jlbDs.addMouseListener(this);

		// ������Ʈ ����
		jpMy.add(jbtnMyPage);
		jpMy.add(jbtnOrderlist);
		jpMy.add(jbtnLogout);
		add(jpMy);

		jpKo.add(jlbKo);
		jpKo.add(jbtnKo);
		jpBn.add(jlbBn);
		jpBn.add(jbtnBn);
		jpJp.add(jlbJp);
		jpJp.add(jbtnJp);
		jpCn.add(jlbCn);
		jpCn.add(jbtnCn);
		jpWs.add(jlbWs);
		jpWs.add(jbtnWs);
		jpDs.add(jlbDs);
		jpDs.add(jbtnDs);

		jp.add(jlbUserId);
		jp.add(jlbTitle);
		jp.add(jbtnMy);
		jp.add(jpKo);
		jp.add(jpBn);
		jp.add(jpJp);
		jp.add(jpCn);
		jp.add(jpWs);
		jp.add(jpDs);

		add(jp);

		// ������â ����, ��ġ, ũ��, �ݱ�, ���̰�
		setTitle("배달의 중앙 by snippets");
		setBounds(0, 0, 450, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	////// �������� �ٸ�â�̶� ����� �ּ� �Ǵ� ����//////
	public static void main(String[] args) {
		new CustomerMain("imsolo");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object clicked = e.getSource();

		if (clicked == jbtnKo || clicked == jlbKo) {

			new CustomerMenuList(id, "한식");
			this.dispose();

		} else if (clicked == jbtnBn || clicked == jlbBn) {

			new CustomerMenuList(id, "분식");
			this.dispose();

		} else if (clicked == jbtnJp || clicked == jlbJp) {

			new CustomerMenuList(id, "일식");
			this.dispose();

		} else if (clicked == jbtnCn || clicked == jlbCn) {

			new CustomerMenuList(id, "중식");
			this.dispose();

		} else if (clicked == jbtnWs || clicked == jlbWs) {

			new CustomerMenuList(id, "양식");
			this.dispose();

		} else if (clicked == jbtnDs || clicked == jlbDs) {

			new CustomerMenuList(id, "디저트");
			this.dispose();

		} else if (clicked == jbtnMy) {

			if (flag == true) {
				jpMy.setVisible(true);
				flag = false;
			} else {
				jpMy.setVisible(false);
				flag = true;
			}

		} else if (clicked == jbtnMyPage) {

			new CustomerInfo(cno, id);
			this.dispose();

		} else if (clicked == jbtnOrderlist) {

			new CustomerOrderList(cno, id);
			this.dispose();

		} else if (clicked == jbtnLogout) {

			new Login();
			this.dispose();

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
		JButton mouse = (JButton) e.getSource();
		Object obj = e.getSource();
		((Component) obj).setCursor(new Cursor(Cursor.HAND_CURSOR));

		if (mouse == jbtnMyPage || mouse == jbtnOrderlist || mouse == jbtnLogout) {
			mouse.setBorderPainted(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton mouse = (JButton) e.getSource();
		mouse.setBorderPainted(false);
	}

}
