package project1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.CustomerDAO;
import dao.StoreDAO;
import vo.CustomerVO;
import vo.StoreVO;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener, FocusListener, MouseListener {
	RoundedButtonW jbtnLogin, jbtnRegister, jbtnFindIdPw;
	JLabel jlbLogo, jlbStore, jlbCustomer, jlbTeamLogo;
	JTextField jtfId;
	JPasswordField jtfPw;
	JCheckBox jcbStore, jcbCustomer;
	JPanel jp;
	ImageIcon logo;
	ButtonGroup bg;

	public Login() {
		// ������Ʈ �ʱ�ȭ
		jp = new JPanel();
		jp.setBackground(Color.white);
		jp.setLayout(null);

		jbtnLogin = new RoundedButtonW("Log In");
		jbtnRegister = new RoundedButtonW("회원가입");
		jbtnFindIdPw = new RoundedButtonW("ID/PW 찾기");

		jlbLogo = new JLabel();
		jlbTeamLogo = new JLabel("ⓒsnippets");

		jtfId = new JTextField("아이디");
		jtfPw = new JPasswordField("비밀번호");
		jtfPw.setEchoChar((char) 0);

		jcbStore = new JCheckBox("가게", false);
		jcbCustomer = new JCheckBox("손님", false);
		bg = new ButtonGroup();

		logo = new ImageIcon("src/images/logo.png");

		Font f = new Font("고딕체", Font.BOLD, 16);

		// ������Ʈ ����
		jlbLogo.setBounds(75, 100, 300, 150);
		jcbStore.setBounds(140, 250, 80, 30);
		jcbCustomer.setBounds(235, 250, 80, 30);
		jtfId.setBounds(100, 300, 160, 45);
		jtfPw.setBounds(100, 360, 160, 45);
		jbtnLogin.setBounds(270, 310, 80, 80);
		jbtnRegister.setBounds(105, 420, 100, 30);
		jbtnFindIdPw.setBounds(225, 420, 120, 30);
		jlbTeamLogo.setBounds(175, 560, 100, 30);

		// �ΰ� �̹��� ����
		jlbLogo.setIcon(logo);

		// ��Ʈ����
		jtfId.setFont(f);
		jtfPw.setFont(f);
		jcbStore.setFont(f);
		jcbCustomer.setFont(f);

		// ���� ����
		jcbStore.setBackground(Color.white);
		jcbCustomer.setBackground(Color.white);

		// ���ڻ� ����
		jtfId.setForeground(Color.LIGHT_GRAY);
		jtfPw.setForeground(Color.LIGHT_GRAY);

		// �̺�Ʈ �߰�
		jtfId.addFocusListener(this);
		jtfPw.addFocusListener(this);

		jbtnLogin.addActionListener(this);
		jbtnRegister.addActionListener(this);
		jbtnFindIdPw.addActionListener(this);
		jcbStore.addActionListener(this);
		jcbCustomer.addActionListener(this);
		
		jbtnLogin.addMouseListener(this);
		jbtnRegister.addMouseListener(this);
		jbtnFindIdPw.addMouseListener(this);

		// ��ư ����
		bg.add(jcbStore);
		bg.add(jcbCustomer);

		jp.add(jlbLogo);
		jp.add(jcbStore);
		jp.add(jcbCustomer);
		jp.add(jtfId);
		jp.add(jtfPw);
		jp.add(jbtnLogin);
		jp.add(jbtnRegister);
		jp.add(jbtnFindIdPw);
		jp.add(jlbTeamLogo);

		add(jp);

		// ������â ����, ��ġ, ũ��, �ݱ�, ���̰�
		setTitle("배달의 중앙 by snippets");
		setBounds(0, 0, 450, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object clicked = e.getSource();

		if (clicked == jbtnLogin) { // �α��ι�ư Ŭ��

			if (jcbStore.isSelected()) { // ���� üũ

				StoreDAO sdao = new StoreDAO();
				StoreVO svo = sdao.selectOne(jtfId.getText());

				if (null == svo) { // ���̵����
					JOptionPane.showConfirmDialog(this, "아이디 또는 비밀번호가 맞지 않습니다", "확인", JOptionPane.PLAIN_MESSAGE);
				} else if (svo.getSid().equals(jtfId.getText())
						&& svo.getSpw().equals(new String(jtfPw.getPassword()))) { // ���̵�, ��� ��ġ => �α���
					new StoreMain(jtfId.getText());
					this.dispose();
				} else { // ��й�ȣ ����ġ => ����� Ʋ�ȴ��� Ư������ �ʱ� ���� ��� �޼��� �Ȱ��� ���
					JOptionPane.showConfirmDialog(this, "아이디 또는 비밀번호가 맞지 않습니다", "확인", JOptionPane.PLAIN_MESSAGE);
				}

			} else if (jcbCustomer.isSelected()) { // �մ� üũ

				CustomerVO cvo = new CustomerDAO().selectOne(jtfId.getText());

				if (null == cvo) {
					JOptionPane.showConfirmDialog(this, "아이디 또는 비밀번호가 맞지 않습니다", "확인", JOptionPane.PLAIN_MESSAGE);
				} else if (cvo.getCid().equals(jtfId.getText())
						&& cvo.getCpw().equals(new String(jtfPw.getPassword()))) {
					new CustomerMain(jtfId.getText());
					this.dispose();
				} else {
					JOptionPane.showConfirmDialog(this, "아이디 또는 비밀번호가 맞지 않습니다", "확인", JOptionPane.PLAIN_MESSAGE);
				}
			}

		} else if (clicked == jbtnRegister) {
			new CreateAccount();
			this.dispose();

		} else if (clicked == jbtnFindIdPw) {
			JOptionPane.showConfirmDialog(this, "고객센터로 문의바랍니다. 02-000-000", "확인", JOptionPane.PLAIN_MESSAGE);
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == jtfId) {
			jtfId.setText("");
			jtfId.setForeground(Color.black);
		} else if (e.getSource() == jtfPw) {
			jtfPw.setText("");
			jtfPw.setEchoChar('*');
			jtfPw.setForeground(Color.black);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (jtfId.getText().isEmpty()) {
			jtfId.setForeground(Color.LIGHT_GRAY);
			jtfId.setText("아이디");
		} else if (jtfPw.getPassword().length == 0) {
			jtfPw.setForeground(Color.LIGHT_GRAY);
			jtfPw.setEchoChar((char) 0);
			jtfPw.setText("비밀번호");
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
