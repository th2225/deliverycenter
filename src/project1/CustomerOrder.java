package project1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.CustomerDAO;
import dao.OrderedDAO;
import vo.CustomerVO;

@SuppressWarnings("serial")
public class CustomerOrder extends JFrame implements ActionListener, MouseListener {
	JLabel jlbOprice, jlbOpricetot, jlbCaddress, jlbCphone, jlbPay, jlbCname, jlbOrder, jlbAmount1, jlbAmount2;
	JTextField jtfCaddress, jtfCphone;
	JCheckBox jcbCard, jcbCash;
	RoundedButtonW jbtnOrder;
	JScrollPane jsp;
	JTextArea jta;
	JPanel jp;
	ButtonGroup bg;
	ImageIcon imgBack;
	JButton jbtnBack;

	String id = null;
	String odetail = null;
	String category = null;
	String caddress = null;
	String cphone = null;
	String opayment = null;
	String oaddress = null;
	String ophone = null;
	String orderlist = null;
	int sno = 0;
	int oamount = 0;
	int oprice = 0;
	int cno = 0;

	Font font = new Font("고딕체", Font.BOLD, 15);
	Font fonta = new Font("고딕체", Font.BOLD, 20);
	Font f = new Font("고딕체", Font.BOLD, 20);

	public CustomerOrder(String id, int sno, String orderlist, int oamount, int oprice, String category) {
		this.id = id;
		this.sno = sno;
		this.orderlist = orderlist;
		this.oamount = oamount;
		this.oprice = oprice;
		this.category = category;

		// ����â���� �Ѿ���� �ٷ� �����ؼ� ������ ������
		CustomerDAO dao = new CustomerDAO();
		CustomerVO vo = dao.findCnoCaddressCphone(id);

		cno = vo.getCno();
		caddress = vo.getCaddress();
		cphone = vo.getCphone();

		// ������Ʈ �ʱ�ȭ
		jp = new JPanel();
		jp.setBackground(Color.white);
		jp.setLayout(null);

		jta = new JTextArea(orderlist);
		jta.setEditable(false);

		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(50, 100, 340, 150);

		jlbAmount1 = new JLabel("총 수량");
		jlbAmount1.setBounds(49, 280, 150, 30);
		jlbAmount1.setFont(fonta);

		jlbAmount2 = new JLabel(oamount + " 개", SwingConstants.RIGHT);
		jlbAmount2.setBounds(270, 280, 120, 30);
		jlbAmount2.setFont(fonta);

		jlbOprice = new JLabel("총 결제금액");
		jlbOprice.setBounds(50, 330, 150, 30);
		jlbOprice.setFont(fonta);

		jlbOpricetot = new JLabel(oprice + " 원", SwingConstants.RIGHT);
		jlbOpricetot.setBounds(240, 330, 150, 30);
		jlbOpricetot.setFont(fonta);

		jlbCaddress = new JLabel("주소");
		jlbCaddress.setBounds(50, 380, 150, 30);
		jlbCaddress.setFont(fonta);

		jtfCaddress = new JTextField(caddress);
		jtfCaddress.setBounds(150, 380, 250, 30);
		jtfCaddress.setFont(font);

		jlbCphone = new JLabel("연락처");
		jlbCphone.setBounds(50, 430, 150, 30);
		jlbCphone.setFont(fonta);

		jtfCphone = new JTextField(cphone);
		jtfCphone.setBounds(150, 430, 250, 30);
		jtfCphone.setFont(font);

		jlbPay = new JLabel("결제수단");
		jlbPay.setBounds(50, 480, 150, 30);
		jlbPay.setFont(fonta);

		jcbCard = new JCheckBox("카드");
		jcbCard.setBounds(230, 460, 60, 70);
		jcbCard.setFont(font);
		jcbCard.setBackground(Color.white);

		jcbCash = new JCheckBox("현장결제");
		jcbCash.setBounds(300, 460, 100, 70);
		jcbCash.setFont(font);
		jcbCash.setBackground(Color.white);

		// ī��, ������� �ߺ�üũ �ȵǰ�
		ButtonGroup bg = new ButtonGroup();
		bg.add(jcbCard);
		bg.add(jcbCash);

		jbtnOrder = new RoundedButtonW("주문접수");
		jbtnOrder.setBounds(250, 530, 150, 40);
		jbtnOrder.setFont(font);
		jbtnOrder.addActionListener(this);
		jbtnOrder.addMouseListener(this);

		jbtnBack = new JButton();
		jbtnBack.setBounds(30, 20, 50, 50);
		jbtnBack.setFont(font);
		imgBack = new ImageIcon("src/images/arrow.jpg");
		jbtnBack.setIcon(imgBack);
		jbtnBack.setBorderPainted(false);
		
		// �������� �̺�Ʈ ó��
		jbtnBack.addActionListener(this);
		jbtnBack.addMouseListener(this);

		jlbCname = new JLabel(id, SwingConstants.RIGHT);
		jlbCname.setBounds(290, 30, 100, 30);
		jlbCname.setFont(font);

		jlbOrder = new JLabel("주문하기", SwingConstants.CENTER);
		jlbOrder.setBounds(135, 30, 150, 30);
		jlbOrder.setFont(f);

		jp.add(jlbOprice);
		jp.add(jlbOpricetot);
		jp.add(jlbCaddress);
		jp.add(jtfCaddress);
		jp.add(jlbCphone);
		jp.add(jtfCphone);
		jp.add(jlbPay);
		jp.add(jlbAmount1);
		jp.add(jlbAmount2);
		jp.add(jcbCard);
		jp.add(jcbCash);
		jp.add(jbtnOrder);
		jp.add(jbtnBack);
		jp.add(jlbCname);
		jp.add(jlbOrder);
		jp.add(jsp);

		add(jp);

		setTitle("배달의 중앙 by snippets");
		setBounds(0, 0, 450, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	// �������� �ٸ�â�� ����� �ּ�
	public static void main(String[] args) {
		new CustomerOrder("imsolo", 2, "치킨 세마리", 3, 60000, "한식");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnBack) {

			// System.out.println("�������� ��ư ����");
			int result = JOptionPane.showConfirmDialog(this, "창을 나가면 주문이 초기화 됩니다. 계속 진행하시겠습니까?", "확인",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				new CustomerMenu(id, sno, category);
				this.dispose();
			}

		} else if (obj == jbtnOrder) {

			int result = JOptionPane.showConfirmDialog(this, "주문하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if (jcbCard.isSelected()) {
					opayment = "카드";
					oaddress = jtfCaddress.getText();
					ophone = jtfCphone.getText();
					OrderedDAO dao = new OrderedDAO();					
					dao.insertOrder(cno, sno, oamount, oprice, opayment, oaddress, ophone, orderlist); 
					dao.close();
					JOptionPane.showConfirmDialog(this, "주문이 완료되었습니다.", "확인",
							JOptionPane.PLAIN_MESSAGE);
					new CustomerMain(id);
					this.dispose();
					
				} else if (jcbCash.isSelected()) {
					opayment = "현장결제";
					oaddress = jtfCaddress.getText();
					ophone = jtfCphone.getText();
					OrderedDAO dao = new OrderedDAO();
					dao.insertOrder(cno, sno, oamount, oprice, opayment, oaddress, ophone, orderlist); 
					dao.close();
					JOptionPane.showConfirmDialog(this, "주문이 완료되었습니다.", "확인",
							JOptionPane.PLAIN_MESSAGE);
					new CustomerMain(id);
					this.dispose();
					
				} else {
					JOptionPane.showConfirmDialog(this, "결제수단을 선택해주세요", "확인", JOptionPane.PLAIN_MESSAGE);
				}

			}

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
