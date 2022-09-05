package project1;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.MenuDAO;
import dao.StoreDAO;
import vo.MenuVO;

@SuppressWarnings("serial")
public class StoreMenuSetting extends JFrame implements ActionListener, MouseListener {

	JPanel jp1, jp2, jp2a, jp2b;
	JTextField jtfMenuName1, jtfMenuSoldout, jtfMenuExplain1, jtfMenuPrice1, jtfMenuName2, jtfMenuNo2, jtfMenuExplain2,
			jtfMenuPrice2;
	JTextArea jta1, jta2;

	RoundedButtonW rbtnDelete, rbtnSave, rbtnUpdate, rbtnSearch, rbtnMenuManage, rbtnMenuShow;

	JButton jbtnBack, jbtnDesc1, jbtnDesc2;
	JLabel jlbUserId, jlbTitle, jlbMenuAdd, jlbMenuUD;
	JLabel jlbMenuName1, jlbMenuSoldout, jlbMenuExplain1, jlbMenuPrice1, jlbMenuName2, jlbMenuNo2, jlbMenuExplain2,
			jlbMenuPrice2;
	JScrollPane jsp;
	JTable jtb;
	ImageIcon imgBack, imgDesc;

	String header[] = { "메뉴번호", "메뉴이름", "메뉴가격", "품절여부", "설명" };
	Object[][] contents;

	CardLayout cl;

	String id = null;
	int sno = 0;
	int mno = 0;

	public StoreMenuSetting(String id) {
		// �ٸ� â���� id ���� ���� �� ����
		this.id = id;

		// â ������ id�� sno ã�� �� ����
		StoreDAO dao = new StoreDAO();
		sno = dao.findSno(id);
		dao.close();

		// ������Ʈ �߱�ȭ
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp2a = new JPanel();
		jp2b = new JPanel();

		setLayout(null);
		cl = new CardLayout();
		jp1.setLayout(null); // (��ȣ��), �޴�����
		jp2.setLayout(cl); // jp2a, jp2b ���� �г�
		jp2a.setLayout(null); // �޴��߰�,����,����
		jp2b.setLayout(null); // �޴�����Ʈ ����

		rbtnMenuManage = new RoundedButtonW("메뉴 관리");
		rbtnMenuShow = new RoundedButtonW("메뉴 보기");
		rbtnDelete = new RoundedButtonW("메뉴 삭제");
		rbtnSave = new RoundedButtonW("메뉴 추가");
		rbtnUpdate = new RoundedButtonW("메뉴 수정");
		rbtnSearch = new RoundedButtonW("메뉴 조회");

		jtfMenuName1 = new JTextField();
		jtfMenuExplain1 = new JTextField();
		jtfMenuPrice1 = new JTextField();
		jtfMenuNo2 = new JTextField();
		jtfMenuName2 = new JTextField();
		jtfMenuExplain2 = new JTextField();
		jtfMenuPrice2 = new JTextField();
		jtfMenuSoldout = new JTextField();

		jta1 = new JTextArea();
		jta2 = new JTextArea();

		jbtnBack = new JButton();
		jbtnDesc1 = new JButton();
		jbtnDesc2 = new JButton();

		jlbUserId = new JLabel(id + "님", SwingConstants.RIGHT);
		jlbTitle = new JLabel("배달의 중앙", SwingConstants.CENTER);
		jlbMenuAdd = new JLabel("메뉴 추가");
		jlbMenuUD = new JLabel("메뉴 수정/삭제");
		jlbMenuName1 = new JLabel("메뉴이름");
		jlbMenuName2 = new JLabel("메뉴이름");
		jlbMenuNo2 = new JLabel("메뉴번호");
		jlbMenuExplain1 = new JLabel("메뉴설명");
		jlbMenuExplain2 = new JLabel("메뉴설명");
		jlbMenuPrice1 = new JLabel("메뉴가격");
		jlbMenuPrice2 = new JLabel("메뉴가격");
		jlbMenuSoldout = new JLabel("품절여부");

		jtb = new JTable();
		jtb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jsp = new JScrollPane(jtb);
		jsp.setLayout(new ScrollPaneLayout());

		imgBack = new ImageIcon("src/images/arrow.jpg");
		imgDesc = new ImageIcon("src/images/desc.jpg");

		// �ؽ�Ʈ������ �ؽ�Ʈ, �����(���� ȣ��)
		jta1.setVisible(false);
		jta2.setVisible(false);
		jta1.append(" 추가하려는 메뉴 정보 입력 후 메뉴 추가 클릭");
		jta1.append("\n  * 가격은 숫자만 입력");
		jta1.append("\n  ** 성공 메세지가 없을 시 추가 되지 않은 것");
		jta1.append("\n  *** 메뉴이름,가격,품절여부 : 필수 / 메뉴설명 : 부가사항");
		jta2.append("  1.메뉴보기를 눌러 메뉴번호 확인");
		jta2.append("\n  2.메뉴번호 입력");
		jta2.append("\n  3.메뉴조회 클릭");
		jta2.append("\n  4-1.삭제 : 삭제하려는 메뉴가 맞는지 확인 후 메뉴삭제 클릭");
		jta2.append("\n  4-2.수정 : 수정사항 입력 후 메뉴수정 클릭");
		jta2.append("\n  5.성공 메세지 확인");
		jta2.append("\n  * 성공 메세지가 없을 시 삭제/수정이 되지 않은 것");
		jta2.append("\n  ** 메뉴이름,가격,품절여부 : 필수 / 메뉴설명 : 부가사항");
		jta2.append("\n     품절여부 : Y 또는 N");

		// �̹��� ����
		jbtnBack.setIcon(imgBack);
		jbtnDesc1.setIcon(imgDesc);
		jbtnDesc2.setIcon(imgDesc);

		// ��Ʈ ����
		Font f = new Font("고딕체", Font.BOLD, 15);
		Font f1 = new Font("고딕체", Font.BOLD, 20);
		Font f2 = new Font("고딕체", Font.PLAIN, 12);
		Font f3 = new Font("고딕체", Font.BOLD, 14);
		rbtnMenuManage.setFont(f);
		rbtnMenuShow.setFont(f);
		jlbMenuUD.setFont(f3);
		jlbMenuAdd.setFont(f3);
		rbtnDelete.setFont(f);
		rbtnSave.setFont(f);
		rbtnUpdate.setFont(f);
		rbtnSearch.setFont(f);
		jlbMenuName1.setFont(f2);
		jlbMenuName2.setFont(f2);
		jlbMenuNo2.setFont(f2);
		jlbMenuExplain1.setFont(f2);
		jlbMenuExplain2.setFont(f2);
		jlbMenuPrice1.setFont(f2);
		jlbMenuPrice2.setFont(f2);
		jlbMenuSoldout.setFont(f2);
		jlbTitle.setFont(f1);
		jlbUserId.setFont(f);

		// ��׶��� ���� ����
		jp1.setBackground(Color.white);
		jp2a.setBackground(Color.white);
		jp2b.setBackground(Color.white);
		jbtnDesc1.setBackground(Color.white);
		jbtnDesc2.setBackground(Color.white);
		jta1.setBackground(new Color(245, 245, 245));
		jta2.setBackground(new Color(245, 245, 245));
		jsp.getViewport().setBackground(Color.white);

		// ���� ����
		jbtnBack.setBorderPainted(false);
		jbtnDesc1.setBorderPainted(false);
		jbtnDesc2.setBorderPainted(false);

		// ������Ʈ ��ġ
		jbtnBack.setBounds(30, 20, 50, 50);
		jlbTitle.setBounds(135, 30, 150, 30);
		jlbUserId.setBounds(290, 30, 100, 30);
		rbtnMenuManage.setBounds(60, 90, 150, 30);
		rbtnMenuShow.setBounds(230, 90, 150, 30);

		jlbMenuAdd.setBounds(40, 20, 70, 30);
		jbtnDesc1.setBounds(370, 20, 30, 30);
		jta1.setBounds(100, 50, 310, 80);
		jlbMenuName1.setBounds(40, 50, 50, 30);
		jtfMenuName1.setBounds(100, 50, 300, 30);
		jlbMenuPrice1.setBounds(40, 85, 50, 30);
		jtfMenuPrice1.setBounds(100, 85, 300, 30);
		jlbMenuExplain1.setBounds(40, 120, 50, 30);
		jtfMenuExplain1.setBounds(100, 120, 300, 30);
		rbtnSave.setBounds(230, 165, 150, 30);

		jlbMenuUD.setBounds(40, 220, 100, 30);
		jbtnDesc2.setBounds(360, 220, 30, 30);
		jta2.setBounds(100, 240, 310, 165);
		jlbMenuNo2.setBounds(40, 250, 50, 30);
		jtfMenuNo2.setBounds(100, 250, 160, 30);
		jlbMenuSoldout.setBounds(280, 250, 50, 30);
		jtfMenuSoldout.setBounds(340, 250, 60, 30);
		jlbMenuName2.setBounds(40, 285, 50, 30);
		jtfMenuName2.setBounds(100, 285, 300, 30);
		jlbMenuPrice2.setBounds(40, 320, 50, 30);
		jtfMenuPrice2.setBounds(100, 320, 300, 30);
		jlbMenuExplain2.setBounds(40, 355, 50, 30);
		jtfMenuExplain2.setBounds(100, 355, 300, 30);
		rbtnSearch.setBounds(61, 400, 93, 30);
		rbtnUpdate.setBounds(173, 400, 93, 30);
		rbtnDelete.setBounds(286, 400, 93, 30);

		jsp.setBounds(15, 20, 400, 450);
		jp1.setBounds(0, 0, 450, 130);
		jp2.setBounds(0, 130, 450, 520);
		jp2a.setSize(450, 520);
		jp2b.setSize(450, 520);

		// �̺�Ʈ ����
		jbtnBack.addActionListener(this);
		rbtnMenuManage.addActionListener(this);
		rbtnMenuShow.addActionListener(this);
		rbtnSearch.addActionListener(this);
		rbtnSave.addActionListener(this);
		rbtnDelete.addActionListener(this);
		rbtnUpdate.addActionListener(this);

		jbtnDesc1.addMouseListener(this);
		jbtnDesc2.addMouseListener(this);

		// ��ư ����
		jp1.add(jbtnBack);
		jp1.add(jlbTitle);
		jp1.add(jlbUserId);
		jp1.add(rbtnMenuManage);
		jp1.add(rbtnMenuShow);

		jp2a.add(jlbMenuAdd);
		jp2a.add(jbtnDesc1);
		jp2a.add(jta1);
		jp2a.add(jlbMenuName1);
		jp2a.add(jtfMenuName1);
		jp2a.add(jlbMenuPrice1);
		jp2a.add(jtfMenuPrice1);
		jp2a.add(jlbMenuExplain1);
		jp2a.add(jtfMenuExplain1);
		jp2a.add(rbtnSave);

		jp2a.add(jlbMenuUD);
		jp2a.add(jbtnDesc2);
		jp2a.add(jta2);
		jp2a.add(jlbMenuNo2);
		jp2a.add(jtfMenuNo2);
		jp2a.add(jlbMenuSoldout);
		jp2a.add(jtfMenuSoldout);
		jp2a.add(jlbMenuName2);
		jp2a.add(jtfMenuName2);
		jp2a.add(jlbMenuPrice2);
		jp2a.add(jtfMenuPrice2);
		jp2a.add(jlbMenuExplain2);
		jp2a.add(jtfMenuExplain2);
		jp2a.add(rbtnDelete);
		jp2a.add(rbtnUpdate);
		jp2a.add(rbtnSearch);

		jp2b.add(jsp);

		jp2.add(jp2a, "menuEdit");
		jp2.add(jp2b, "menuShow");

		add(jp1);
		add(jp2);

		cl.show(jp2, "menuEdit");
		setTitle("배달의 중앙 by snippets");
		setBounds(0, 0, 450, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/////// �������� �ٸ�â�� ����� �ּ� �Ǵ� ����//////
	public static void main(String[] args) {
		new StoreMenuSetting("bestchic");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object clicked = e.getSource();
		MenuDAO dao = new MenuDAO();
		if (clicked == jbtnBack) {  // �ڷΰ��� ��ư
			new StoreMain(id);
			this.dispose();

		} else if (clicked == rbtnMenuManage) {  // �޴����� ��ư

			jlbTitle.setText("메뉴 관리");
			cl.show(jp2, "menuEdit");

		} else if (clicked == rbtnMenuShow) {  // �޴����� ��ư

			jlbTitle.setText("메뉴 보기");
			cl.show(jp2, "menuShow");
			ArrayList<MenuVO> list = dao.selectMenuByStore(id);
			dao.close();
			contents = new Object[list.size()][5];
			for (int i = 0; i < list.size(); i++) {

				contents[i][0] = list.get(i).getMno();
				contents[i][1] = list.get(i).getMname();
				contents[i][2] = list.get(i).getMprice();
				contents[i][3] = list.get(i).getMsoldout();
				contents[i][4] = list.get(i).getMdetail();

				jtb.setModel(new DefaultTableModel(contents, header) {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
			}

		} else if (clicked == rbtnSave) {  // �޴��߰� ��ư

			dao.insertOne(sno, jtfMenuName1.getText(), Integer.parseInt(jtfMenuPrice1.getText()),
					jtfMenuExplain1.getText());
			dao.close();
			JOptionPane.showConfirmDialog(this, "메뉴가 성공적으로 추가되었습니다.", "확인", JOptionPane.PLAIN_MESSAGE);

		} else if (clicked == rbtnSearch) {  // �޴���ȸ ��ư
			MenuVO vo = dao.selectOne(Integer.parseInt(jtfMenuNo2.getText()), id);
			dao.close();

			if (vo != null) { // �α����� ������� ���Կ� �Է��� ��ȣ�� �´� �޴��� ������ ������ 
				mno = vo.getMno();
				jtfMenuName2.setText(vo.getMname());
				jtfMenuPrice2.setText(vo.getMprice() + "");
				jtfMenuSoldout.setText(vo.getMsoldout());
				jtfMenuExplain2.setText(vo.getMdetail());

			} else { // ���Կ� �ִ� �޴��� ��ȣ�� �ƴ� �޴���ȣ �Է½� �ؽ�Ʈ�ʵ� �ʱ�ȭ
				jtfMenuName2.setText("");
				jtfMenuPrice2.setText("");
				jtfMenuSoldout.setText("");
				jtfMenuExplain2.setText("");
				jtfMenuNo2.setText("");
				jtfMenuNo2.requestFocus();
				JOptionPane.showConfirmDialog(this, "존재하지 않는 메뉴번호 입니다.", "확인", JOptionPane.PLAIN_MESSAGE);

			}

		} else if (clicked == rbtnUpdate) { // �޴����� ��ư

			if ("N".equals(jtfMenuSoldout.getText()) || "Y".equals(jtfMenuSoldout.getText())) { // ǰ������ ���Ŀ� �°� �� �Է��ߴ��� Ȯ��
				if (mno == Integer.parseInt(jtfMenuNo2.getText())) {  // ��ȸ�� �޴��� �����Ϸ��� �޴��� �������� Ȯ��(�޴��� ��ȸ�� �Ŀ��� ���� ����)

					dao.updateOne(jtfMenuName2.getText(), Integer.parseInt(jtfMenuPrice2.getText()),
							jtfMenuExplain2.getText(), jtfMenuSoldout.getText(), Integer.parseInt(jtfMenuNo2.getText()),
							id);
					dao.close();
					JOptionPane.showConfirmDialog(this, "메뉴가 성공적으로 수정되었습니다.", "확인", JOptionPane.PLAIN_MESSAGE);
				} else {
					jtfMenuNo2.setText("");
					jtfMenuNo2.requestFocus();
					JOptionPane.showConfirmDialog(this, "존재하지 않는 메뉴번호입니다. 다시 확인해주세요.", "확인",
							JOptionPane.PLAIN_MESSAGE);
				}
			} else {

				jtfMenuSoldout.setText("");
				jtfMenuSoldout.requestFocus();
				JOptionPane.showConfirmDialog(this, "품절여부를 Y 또는 N으로 입력해주세요.", "확인", JOptionPane.PLAIN_MESSAGE);

			}
		} else if (clicked == rbtnDelete) {  // �޴����� ��ư

			if (mno == Integer.parseInt(jtfMenuNo2.getText())) { // ��ȸ�� �޴��� �����Ϸ��� �޴��� �������� Ȯ��(�޴��� ��ȸ�� �Ŀ��� ���� ����)
				dao.deleteOne(Integer.parseInt(jtfMenuNo2.getText()), id);
				dao.close();
				jtfMenuName2.setText("");
				jtfMenuPrice2.setText("");
				jtfMenuSoldout.setText("");
				jtfMenuExplain2.setText("");
				jtfMenuNo2.setText("");
				jtfMenuNo2.requestFocus();
				JOptionPane.showConfirmDialog(this, "메뉴가 성공적으로 삭제되었습니다.", "확인", JOptionPane.PLAIN_MESSAGE);
			} else {
				jtfMenuNo2.setText("");
				jtfMenuNo2.requestFocus();
				JOptionPane.showConfirmDialog(this, "존재하지 않는 메뉴번호입니다. 다시 확인해주세요.", "확인", JOptionPane.PLAIN_MESSAGE);
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
		Object hover = e.getSource();
		if (hover == jbtnDesc1) {
			jta1.setVisible(true);
		} else if (hover == jbtnDesc2) {
			jta2.setVisible(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object hover = e.getSource();
		if (hover == jbtnDesc1) {
			jta1.setVisible(false);
		} else if (hover == jbtnDesc2) {
			jta2.setVisible(false);
		}
	}

}
