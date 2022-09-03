import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogIn {

	protected static JFrame frame;
	private JTextField textFnumber;
	private JTextField textEmail;
	private JTabbedPane tabbedPane;
	private JPasswordField passwordFieldStudent;
	private JPasswordField passwordFieldTeacher;

	Methods mt = new Methods();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void loginForm() {

		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setBounds(600, 280, 758, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 234, 441);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(229, -61, 532, 513);
		frame.getContentPane().add(tabbedPane);

		JButton btnShowStudentLogin = new JButton("Студент");
		btnShowStudentLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnShowStudentLogin.setBackground(new Color(51, 204, 255));
		btnShowStudentLogin.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		btnShowStudentLogin.setBounds(0, 152, 234, 39);
		panel.add(btnShowStudentLogin);

		JButton btnShowTeachersLogin = new JButton("Преподавател");
		btnShowTeachersLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnShowTeachersLogin.setBackground(new Color(51, 204, 255));
		btnShowTeachersLogin.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		btnShowTeachersLogin.setBounds(0, 225, 234, 39);
		panel.add(btnShowTeachersLogin);

		JLabel lblNewLabel_4 = new JLabel("Вписване като");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_4.setBounds(52, 112, 148, 39);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("или");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_5.setBounds(97, 202, 46, 14);
		panel.add(lblNewLabel_5);

		JPanel panelLogInAsST = new JPanel();
		panelLogInAsST.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("New tab", null, panelLogInAsST, null);
		panelLogInAsST.setLayout(null);

		JLabel lblLogInAsSt = new JLabel("Вписване като студент");
		lblLogInAsSt.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblLogInAsSt.setBounds(123, 117, 273, 41);
		panelLogInAsST.add(lblLogInAsSt);

		JLabel lblFnumber = new JLabel("Факултетен номер");
		lblFnumber.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 17));
		lblFnumber.setBounds(175, 179, 160, 23);
		panelLogInAsST.add(lblFnumber);

		textFnumber = new JTextField();
		textFnumber.setFont(new Font("Calibri", Font.PLAIN, 15));
		textFnumber.setBorder(null);
		textFnumber.setBounds(175, 200, 154, 20);
		panelLogInAsST.add(textFnumber);
		textFnumber.setColumns(10);

		JLabel lblPassSt = new JLabel("Парола");
		lblPassSt.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 17));
		lblPassSt.setBounds(175, 237, 68, 23);
		panelLogInAsST.add(lblPassSt);

		JButton btnStudentLogin = new JButton("Вписване");
		btnStudentLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnStudentLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mt.login("students", "факултетенНомер", textFnumber, passwordFieldStudent, frame);
			}

		});
		btnStudentLogin.setForeground(new Color(255, 255, 255));
		btnStudentLogin.setBackground(new Color(30, 144, 255));
		btnStudentLogin.setBounds(158, 303, 191, 41);
		panelLogInAsST.add(btnStudentLogin);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(175, 221, 154, 2);
		panelLogInAsST.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(175, 278, 154, 2);
		panelLogInAsST.add(separator_1);

		passwordFieldStudent = new JPasswordField();
		passwordFieldStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordFieldStudent.setBorder(null);
		passwordFieldStudent.setBounds(175, 255, 154, 20);
		panelLogInAsST.add(passwordFieldStudent);

		JLabel lblExitSt = new JLabel("X");
		lblExitSt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblExitSt.setHorizontalAlignment(SwingConstants.CENTER);
		lblExitSt.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lblExitSt.setBounds(484, 35, 32, 41);
		panelLogInAsST.add(lblExitSt);

		JPanel panelLogInAsTe = new JPanel();
		panelLogInAsTe.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("New tab", null, panelLogInAsTe, null);
		panelLogInAsTe.setLayout(null);

		JLabel lblLogInAsTe = new JLabel("Вписване като преподавател");
		lblLogInAsTe.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblLogInAsTe.setBounds(105, 116, 319, 41);
		panelLogInAsTe.add(lblLogInAsTe);

		JLabel lblEmail = new JLabel("Имейл");
		lblEmail.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 17));
		lblEmail.setBounds(175, 179, 160, 23);
		panelLogInAsTe.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Calibri", Font.PLAIN, 15));
		textEmail.setColumns(10);
		textEmail.setBorder(null);
		textEmail.setBounds(175, 200, 154, 20);
		panelLogInAsTe.add(textEmail);

		JLabel lblPassTe = new JLabel("Парола");
		lblPassTe.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 17));
		lblPassTe.setBounds(175, 237, 68, 23);
		panelLogInAsTe.add(lblPassTe);

		JButton btnTeacherLogin = new JButton("Вписване");
		btnTeacherLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnTeacherLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mt.login("teachers", "имейл", textEmail, passwordFieldTeacher, frame);
			}
		});
		btnTeacherLogin.setForeground(Color.WHITE);
		btnTeacherLogin.setBackground(new Color(30, 144, 255));
		btnTeacherLogin.setBounds(158, 303, 191, 41);
		panelLogInAsTe.add(btnTeacherLogin);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(175, 221, 154, 2);
		panelLogInAsTe.add(separator_2);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBounds(175, 278, 154, 2);
		panelLogInAsTe.add(separator_1_1);

		passwordFieldTeacher = new JPasswordField();
		passwordFieldTeacher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordFieldTeacher.setBorder(null);
		passwordFieldTeacher.setBounds(175, 255, 154, 20);
		panelLogInAsTe.add(passwordFieldTeacher);

		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lblExit.setBounds(484, 35, 32, 41);
		panelLogInAsTe.add(lblExit);
	}
}
