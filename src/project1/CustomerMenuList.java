package project1;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import dao.StoreDAO;
import vo.StoreVO;

@SuppressWarnings("serial")
public class CustomerMenuList extends JFrame implements ActionListener, MouseListener {
	RoundedButtonW rbnKo, rbnBn, rbnJp, rbnCn, rbnWs, rbnDs;
	JButton jbtnBack;
	JButton[] jbtnlist;
	JTextArea jtaNoStore;
	JLabel jlbTitle, jlbUserId;
	JPanel jp, jpButtonList;
	JScrollPane jspList;
	ImageIcon imgBack;

	String id = null;
	int[] arrSno;
	String category;

	public CustomerMenuList(String id, String category) {
		this.id = id;
		this.category = category;

		// ������Ʈ �ʱ�ȭ
		jp = new JPanel();
		jp.setLayout(null);

		jpButtonList = new JPanel();
		jpButtonList.setLayout(new GridLayout(0, 1));

		jspList = new JScrollPane(jpButtonList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		rbnKo = new RoundedButtonW("한식");
		rbnBn = new RoundedButtonW("분식");
		rbnJp = new RoundedButtonW("일식");
		rbnCn = new RoundedButtonW("중식");
		rbnWs = new RoundedButtonW("양식");
		rbnDs = new RoundedButtonW("디저트");

		jbtnBack = new JButton();

		jlbUserId = new JLabel(id + "님", SwingConstants.RIGHT);
		jlbTitle = new JLabel(category, SwingConstants.CENTER);

		jtaNoStore = new JTextArea("입점된 점포가 없습니다");

		// ����â���� ��ư ������ ���Ը���Ʈ ����
		StoreDAO dao = new StoreDAO();
		ArrayList<StoreVO> list = dao.selectCategory(category);

		jbtnlist = new JButton[list.size()];
		arrSno = new int[list.size()];

		for (int i = 0; i < list.size(); i++) {
			jbtnlist[i] = new JButton("<html>" + (list.get(i).getSname() + "\n배달팁 : " + list.get(i).getSdeliveryfee()
					+ ", 최소주문금액 : " + list.get(i).getSminoprice()).replaceAll("\\n", "<br>") + "<html>");
			jbtnlist[i].addActionListener(this);
			jbtnlist[i].setBackground(Color.white);
			jpButtonList.add(jbtnlist[i]);
			jbtnlist[i].setName(i + "");
			arrSno[i] = list.get(i).getSno();
		}

		// ��Ʈ ����
		Font f = new Font("고딕체", Font.BOLD, 20);
		Font f1 = new Font("고딕체", Font.BOLD, 15);
		jlbTitle.setFont(f);
		jlbUserId.setFont(f1);

		// �̹��� ����
		imgBack = new ImageIcon("src/images/arrow.jpg");
		jbtnBack.setIcon(imgBack);

		// ������Ʈ ��ġ
		jbtnBack.setBounds(30, 20, 50, 50);
		jlbTitle.setBounds(135, 30, 150, 30);
		jlbUserId.setBounds(290, 30, 100, 30);

		jp.setBounds(0, 0, 440, 650);

		rbnKo.setBounds(25, 80, 61, 35);
		rbnBn.setBounds(90, 80, 61, 35);
		rbnJp.setBounds(155, 80, 61, 35);
		rbnCn.setBounds(220, 80, 61, 35);
		rbnWs.setBounds(285, 80, 61, 35);
		rbnDs.setBounds(350, 80, 61, 35);

		jspList.setBounds(25, 140, 385, 450);

		// ���� ����
		jbtnBack.setBorderPainted(false);

		// ���� ����
		jp.setBackground(Color.white);

		// �̺�Ʈ ó��
		jbtnBack.addActionListener(this);
		rbnKo.addActionListener(this);
		rbnBn.addActionListener(this);
		rbnJp.addActionListener(this);
		rbnCn.addActionListener(this);
		rbnWs.addActionListener(this);
		rbnDs.addActionListener(this);
		
		jbtnBack.addMouseListener(this);
		rbnKo.addMouseListener(this);
		rbnBn.addMouseListener(this);
		rbnJp.addMouseListener(this);
		rbnCn.addMouseListener(this);
		rbnWs.addMouseListener(this);
		rbnDs.addMouseListener(this);

		// ������Ʈ ����
		jp.add(jspList);
		jp.add(jbtnBack);
		jp.add(jlbUserId);
		jp.add(jlbTitle);
		jp.add(rbnKo);
		jp.add(rbnBn);
		jp.add(rbnJp);
		jp.add(rbnCn);
		jp.add(rbnWs);
		jp.add(rbnDs);
		add(jp);

		setTitle("배달의 중앙 by snippets");
		setBounds(0, 0, 450, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CustomerMenuList("imsolo", "한식");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object clicked = e.getSource();
		JButton button = (JButton) e.getSource();

		if (clicked == jbtnBack) {

			new CustomerMain(id);
			this.dispose();

		} else if (clicked == rbnKo) {

			jpButtonList.removeAll();
			jlbTitle.setText(rbnKo.getText());

			StoreDAO dao = new StoreDAO();
			ArrayList<StoreVO> list = dao.selectCategory(rbnKo.getText());

			jbtnlist = new JButton[list.size()];
			arrSno = new int[list.size()];

			for (int i = 0; i < list.size(); i++) {
				jbtnlist[i] = new JButton(
						"<html>" + (list.get(i).getSname() + "\n배달팁 : " + list.get(i).getSdeliveryfee() + ", 최소주문금액 : "
								+ list.get(i).getSminoprice()).replaceAll("\\n", "<br>") + "<html>");
				jbtnlist[i].addActionListener(this);
				jbtnlist[i].setBackground(Color.white);
				jpButtonList.add(jbtnlist[i]);
				jbtnlist[i].setName(i + "");
				jpButtonList.repaint();
				arrSno[i] = list.get(i).getSno();
			}

		} else if (clicked == rbnBn) {

			jpButtonList.removeAll();
			jlbTitle.setText(rbnBn.getText());
			StoreDAO dao = new StoreDAO();
			ArrayList<StoreVO> list = dao.selectCategory(rbnBn.getText());

			jbtnlist = new JButton[list.size()];
			arrSno = new int[list.size()];

			for (int i = 0; i < list.size(); i++) {
				arrSno[i] = 0;
				jbtnlist[i] = new JButton(
						"<html>" + (list.get(i).getSname() + "\n배달팁 : " + list.get(i).getSdeliveryfee() + ", 최소주문금액 : "
								+ list.get(i).getSminoprice()).replaceAll("\\n", "<br>") + "<html>");
				jbtnlist[i].addActionListener(this);
				jbtnlist[i].setBackground(Color.white);
				jpButtonList.add(jbtnlist[i]);
				jbtnlist[i].setName(i + "");
				jpButtonList.repaint();
				arrSno[i] = list.get(i).getSno();
			}

		} else if (clicked == rbnJp) {

			jpButtonList.removeAll();
			jlbTitle.setText(rbnJp.getText());
			StoreDAO dao = new StoreDAO();
			ArrayList<StoreVO> list = dao.selectCategory(rbnJp.getText());

			jbtnlist = new JButton[list.size()];
			arrSno = new int[list.size()];

			for (int i = 0; i < list.size(); i++) {
				arrSno[i] = 0;
				arrSno[i] = 0;
				jbtnlist[i] = new JButton(
						"<html>" + (list.get(i).getSname() + "\n배달팁 : " + list.get(i).getSdeliveryfee() + ", 최소주문금액 : "
								+ list.get(i).getSminoprice()).replaceAll("\\n", "<br>") + "<html>");
				jbtnlist[i].addActionListener(this);
				jbtnlist[i].setBackground(Color.white);
				jpButtonList.add(jbtnlist[i]);
				jbtnlist[i].setName(i + "");
				jpButtonList.repaint();
				arrSno[i] = list.get(i).getSno();
			}
		} else if (clicked == rbnCn) {
			jpButtonList.removeAll();
			jlbTitle.setText(rbnCn.getText());
			StoreDAO dao = new StoreDAO();
			ArrayList<StoreVO> list = dao.selectCategory(rbnCn.getText());

			jbtnlist = new JButton[list.size()];
			arrSno = new int[list.size()];

			for (int i = 0; i < list.size(); i++) {
				arrSno[i] = 0;
				jbtnlist[i] = new JButton(
						"<html>" + (list.get(i).getSname() + "\n배달팁 : " + list.get(i).getSdeliveryfee() + ", 최소주문금액 : "
								+ list.get(i).getSminoprice()).replaceAll("\\n", "<br>") + "<html>");
				jbtnlist[i].addActionListener(this);
				jbtnlist[i].setBackground(Color.white);
				jpButtonList.add(jbtnlist[i]);
				jbtnlist[i].setName(i + "");
				jpButtonList.repaint();
				arrSno[i] = list.get(i).getSno();
			}
		} else if (clicked == rbnWs) {
			jpButtonList.removeAll();
			jlbTitle.setText(rbnWs.getText());
			StoreDAO dao = new StoreDAO();
			ArrayList<StoreVO> list = dao.selectCategory(rbnWs.getText());

			jbtnlist = new JButton[list.size()];
			arrSno = new int[list.size()];

			for (int i = 0; i < list.size(); i++) {
				arrSno[i] = 0;
				jbtnlist[i] = new JButton(
						"<html>" + (list.get(i).getSname() + "\n배달팁 : " + list.get(i).getSdeliveryfee() + ", 최소주문금액 : "
								+ list.get(i).getSminoprice()).replaceAll("\\n", "<br>") + "<html>");
				jbtnlist[i].addActionListener(this);
				jbtnlist[i].setBackground(Color.white);
				jpButtonList.add(jbtnlist[i]);
				jbtnlist[i].setName(i + "");
				jpButtonList.repaint();
				arrSno[i] = list.get(i).getSno();
			}
		} else if (clicked == rbnDs) {

			// ������ �߰��ϸ� �ּ�
			jlbTitle.setText(rbnDs.getText());
			jpButtonList.removeAll();
			jpButtonList.repaint();

			// ������ �߰��ϸ� �ּ� ����
//			jpButtonList.removeAll();
//			jlbTitle.setText(rbnDs.getText());
//			StoreDAO dao = new StoreDAO();
//			ArrayList<StoreVO> list = dao.selectCategory(rbnDs.getText());
//
//			jbtnlist = new JButton[list.size()];
//			arrSno = new int[list.size()];
//
//			for (int i = 0; i < list.size(); i++) {
//				arrSno[i] = 0;
//				jbtnlist[i] = new JButton(
//						"<html>" + (list.get(i).getSname() + "\n배달팁 : " + list.get(i).getSdeliveryfee() + ", 최소주문금액 : "
//								+ list.get(i).getSminoprice()).replaceAll("\\n", "<br>") + "<html>");
//				jbtnlist[i].addActionListener(this);
//				jbtnlist[i].setBackground(Color.white);
//				jpButtonList.add(jbtnlist[i]);
//				jbtnlist[i].setName(i + "");
//				jpButtonList.repaint();
//				arrSno[i] = list.get(i).getSno();
//			}

		} else {

			int nth = Integer.parseInt(button.getName());
			new CustomerMenu(id, arrSno[nth], category);
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
		Object obj = e.getSource();
		((Component) obj).setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
