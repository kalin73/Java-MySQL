import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class Student {

	private JFrame StFrame;
	private JButton btnShowConsDates;
	private JButton btnShowExamDates;
	private JButton btnChangePass;
	private JButton btnPasswordChange;
	private JTable tableExams;
	private JTable tableConsultations;
	private DefaultTableModel modelExams;
	private DefaultTableModel modelCons;
	private JPasswordField passwordOldPass;
	private JPasswordField passwordNewPass;
	private JTabbedPane tabbedPane;
	private JComboBox cmbSem;

	LogIn log = new LogIn();
	Methods mt = new Methods();
	private JTextField textSearchExams;
	private JTextField textSearchCons;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void studentUI(String[] array) {
		String[] studentInfo = array;
		final String fNumber = studentInfo[0];
		final String name = studentInfo[1];
		final String fname = studentInfo[2];
		final String degree = studentInfo[3];
		final String spec = studentInfo[4];
		final String course = studentInfo[5];

		StFrame = new JFrame();
		StFrame.setUndecorated(true);
		StFrame.setVisible(true);
		StFrame.setBounds(570, 280, 835, 490);
		StFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StFrame.getContentPane().setLayout(null);

		modelExams = new DefaultTableModel();
		String[] columns = { "Дисциплина", "Пеподавател", "Дата", "Час", "Зала" };
		modelExams.setColumnIdentifiers(columns);

		modelCons = new DefaultTableModel();
		String[] columns2 = { "№", "Пеподавател", "Дата", "Час", "Зала" };
		modelCons.setColumnIdentifiers(columns2);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 255));
		panel.setBounds(0, 0, 835, 490);
		StFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(new Color(51, 153, 255));
		panelHeader.setBounds(185, 0, 650, 45);
		panel.add(panelHeader);
		panelHeader.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(51, 153, 255));
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(185, 11, 650, 479);
		panel.add(tabbedPane);

		JPanel panelExams = new JPanel();
		panelExams.setBackground(new Color(255, 255, 255));
		panelExams.setBorder(null);
		tabbedPane.addTab("New tab", null, panelExams, null);
		panelExams.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-1, 142, 649, 309);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(null);
		panelExams.add(scrollPane);

		tableExams = new JTable();
		tableExams.setShowVerticalLines(false);
		tableExams.setRowHeight(25);
		tableExams.setIntercellSpacing(new Dimension(0, 0));
		tableExams.setFocusable(false);
		tableExams.setShowGrid(false);
		tableExams.setBorder(null);
		tableExams.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tableExams.getTableHeader().setOpaque(false);
		tableExams.setRowHeight(25);
		tableExams.getTableHeader().setBackground(new Color(255, 255, 255));
		tableExams.setModel(modelExams);
		tableExams.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableExams.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableExams.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableExams.getColumnModel().getColumn(3).setPreferredWidth(10);
		tableExams.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tableExams.getColumnModel().getColumn(4).setPreferredWidth(10);
		tableExams.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		scrollPane.setViewportView(tableExams);

		RowSorter<DefaultTableModel> sortExams = new TableRowSorter<DefaultTableModel>(modelExams);
		tableExams.setRowSorter(sortExams);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 102, 204));
		panel_1.setBounds(-1, 92, 648, 50);
		panelExams.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTableHeader = new JLabel(
				"Изпитни дати за " + course + " курс " + degree + ", специалност " + spec.toUpperCase());
		lblTableHeader.setForeground(new Color(255, 255, 255));
		lblTableHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableHeader.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblTableHeader.setBounds(10, 11, 628, 35);
		panel_1.add(lblTableHeader);

		JLabel lblSearchEx_1_1 = new JLabel("Търсене");
		lblSearchEx_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchEx_1_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSearchEx_1_1.setBounds(-1, 45, 75, 45);
		panelExams.add(lblSearchEx_1_1);

		textSearchExams = new JTextField();
		textSearchExams.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = textSearchExams.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelExams);
				tableExams.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		textSearchExams.setFont(new Font("Calibri", Font.PLAIN, 15));
		textSearchExams.setColumns(10);
		textSearchExams.setBorder(null);
		textSearchExams.setBounds(84, 55, 296, 20);
		panelExams.add(textSearchExams);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(84, 75, 296, 14);
		panelExams.add(separator_1);

		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(
				new ImageIcon("C:\\Users\\Kalin\\Documents\\Eclipse\\Diplomna_rabota\\icons\\icons8_search_24px.png"));
		lblSearch.setBounds(380, 43, 29, 42);
		panelExams.add(lblSearch);

		JLabel lblWelcome1 = new JLabel("Здравейте");
		lblWelcome1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblWelcome1.setBounds(0, 19, 134, 31);
		panelExams.add(lblWelcome1);

		JLabel lblWelcome1_1 = new JLabel(name + " " + fname);
		lblWelcome1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcome1_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblWelcome1_1.setBounds(125, 19, 423, 31);
		panelExams.add(lblWelcome1_1);

		JPanel panelConsultations = new JPanel();
		panelConsultations.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("New tab", null, panelConsultations, null);
		panelConsultations.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBackground(new Color(255, 255, 255));
		scrollPane_1.setBounds(-1, 142, 649, 309);
		panelConsultations.add(scrollPane_1);

		tableConsultations = new JTable();
		tableConsultations.setShowGrid(false);
		tableConsultations.setRowHeight(25);
		tableConsultations.setIntercellSpacing(new Dimension(0, 0));
		tableConsultations.setFocusable(false);
		tableConsultations.setBorder(null);
		tableConsultations.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tableConsultations.getTableHeader().setOpaque(false);
		tableConsultations.setRowHeight(25);
		tableConsultations.getTableHeader().setBackground(new Color(255, 255, 255));
		tableConsultations.setModel(modelCons);
		tableConsultations.getColumnModel().getColumn(0).setPreferredWidth(1);
		tableConsultations.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableConsultations.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableConsultations.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableConsultations.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tableConsultations.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		scrollPane_1.setViewportView(tableConsultations);

		RowSorter<DefaultTableModel> sorteCons = new TableRowSorter<DefaultTableModel>(modelCons);
		tableConsultations.setRowSorter(sorteCons);

		JPanel panelTableHeadCons = new JPanel();
		panelTableHeadCons.setLayout(null);
		panelTableHeadCons.setBackground(new Color(51, 102, 204));
		panelTableHeadCons.setBounds(-1, 92, 648, 50);
		panelConsultations.add(panelTableHeadCons);

		JLabel lblTableHeadCons = new JLabel("Дати за консултации");
		lblTableHeadCons.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableHeadCons.setForeground(Color.WHITE);
		lblTableHeadCons.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblTableHeadCons.setBounds(0, 11, 648, 33);
		panelTableHeadCons.add(lblTableHeadCons);

		JLabel lblSearchCons = new JLabel("Търсене");
		lblSearchCons.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchCons.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSearchCons.setBounds(-1, 45, 75, 45);
		panelConsultations.add(lblSearchCons);

		textSearchCons = new JTextField();
		textSearchCons.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = textSearchCons.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelCons);
				tableConsultations.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		textSearchCons.setFont(new Font("Calibri", Font.PLAIN, 15));
		textSearchCons.setColumns(10);
		textSearchCons.setBorder(null);
		textSearchCons.setBounds(84, 55, 296, 20);
		panelConsultations.add(textSearchCons);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBackground(Color.WHITE);
		separator_1_1.setBounds(84, 75, 296, 14);
		panelConsultations.add(separator_1_1);

		JLabel lblSearch2 = new JLabel("");
		lblSearch2.setIcon(
				new ImageIcon("C:\\Users\\Kalin\\Documents\\Eclipse\\Diplomna_rabota\\icons\\icons8_search_24px.png"));
		lblSearch2.setBounds(380, 43, 29, 42);
		panelConsultations.add(lblSearch2);

		JLabel lblWelcome1_2 = new JLabel("Здравейте");
		lblWelcome1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome1_2.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblWelcome1_2.setBounds(0, 19, 134, 31);
		panelConsultations.add(lblWelcome1_2);

		JLabel lblWelcome1_1_1 = new JLabel(name + " " + fname);
		lblWelcome1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcome1_1_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblWelcome1_1_1.setBounds(125, 19, 423, 31);
		panelConsultations.add(lblWelcome1_1_1);

		JPanel panelPasswordChange = new JPanel();
		panelPasswordChange.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("New tab", null, panelPasswordChange, null);
		panelPasswordChange.setLayout(null);

		JLabel lblPassChange = new JLabel("Смяна на парола");
		lblPassChange.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblPassChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassChange.setBounds(218, 103, 200, 35);
		panelPasswordChange.add(lblPassChange);

		passwordOldPass = new JPasswordField();
		passwordOldPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordOldPass.setBounds(218, 158, 206, 20);
		panelPasswordChange.add(passwordOldPass);

		JLabel lblOldPass = new JLabel("Стара парола");
		lblOldPass.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblOldPass.setBounds(217, 141, 110, 14);
		panelPasswordChange.add(lblOldPass);

		passwordNewPass = new JPasswordField();
		passwordNewPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordNewPass.setBounds(218, 215, 206, 20);
		panelPasswordChange.add(passwordNewPass);

		JLabel lblNewPass = new JLabel("Нова парола");
		lblNewPass.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewPass.setBounds(218, 199, 125, 14);
		panelPasswordChange.add(lblNewPass);

		btnChangePass = new JButton("Промяна");
		btnChangePass.setForeground(new Color(255, 255, 255));
		btnChangePass.setBackground(new Color(51, 153, 255));
		btnChangePass.setBorder(null);
		btnChangePass.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mt.changePassword(passwordOldPass, fNumber, passwordNewPass);
			}
		});
		btnChangePass.setBounds(256, 257, 134, 35);
		panelPasswordChange.add(btnChangePass);

		JLabel lblExit = new JLabel("");
		lblExit.setBounds(608, 0, 32, 32);
		panelHeader.add(lblExit);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(StFrame, "Искате ли да затворите програмата?", "Затваряне",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}
		});
		lblExit.setIcon(
				new ImageIcon("C:\\Users\\Kalin\\Documents\\Eclipse\\Diplomna_rabota\\icons\\icons8_Close_30px.png"));

		JLabel lblHeader = new JLabel("ИНФОРМАЦИОННА СИСТЕМА");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 23));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(130, 0, 393, 43);
		panelHeader.add(lblHeader);

		JLabel lblLogout = new JLabel("");
		lblLogout.setBounds(29, 372, 32, 32);
		panel.add(lblLogout);
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (JOptionPane.showConfirmDialog(StFrame, "Искате ли да излезете?", "Излизане",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					StFrame.setVisible(false);
					LogIn.frame.setVisible(true);
				}

			}
		});
		lblLogout.setIcon(new ImageIcon(
				"C:\\Users\\Kalin\\Documents\\Eclipse\\Diplomna_Rabota\\icons\\icons8_logout_rounded_left_32px.png"));
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblLogout_2 = new JLabel("Излизане");
		lblLogout_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(StFrame, "Искате ли да излезете?", "Излизане",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					StFrame.setVisible(false);
					LogIn.frame.setVisible(true);
				}
			}
		});
		lblLogout_2.setForeground(new Color(255, 255, 255));
		lblLogout_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout_2.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 19));
		lblLogout_2.setBounds(50, 375, 100, 32);
		panel.add(lblLogout_2);


		btnShowExamDates = new JButton("Изпитни дати");
		btnShowExamDates.setBorder(null);
		btnShowExamDates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String semester = cmbSem.getSelectedItem().toString();
				mt.getExams(degree, modelExams, course, spec, semester);
				mt.setColor(btnShowExamDates);
				mt.resetColor(btnShowConsDates, btnPasswordChange);
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnShowExamDates.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 19));
		btnShowExamDates.setForeground(new Color(255, 255, 255));
		btnShowExamDates.setBackground(new Color(51, 153, 255));
		btnShowExamDates.setBounds(0, 141, 185, 45);
		mt.setColor(btnShowExamDates);
		panel.add(btnShowExamDates);

		btnShowConsDates = new JButton("Консултации");
		btnShowConsDates.setBorder(null);
		btnShowConsDates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mt.showConsultations(modelCons);
				mt.setColor(btnShowConsDates);
				mt.resetColor(btnShowExamDates, btnPasswordChange);
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnShowConsDates.setForeground(new Color(255, 255, 255));
		btnShowConsDates.setBackground(new Color(51, 153, 255));
		btnShowConsDates.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 19));
		btnShowConsDates.setBounds(0, 217, 185, 45);
		panel.add(btnShowConsDates);

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setBounds(72, 11, 41, 94);
		panel.add(lblIcon);
		lblIcon.setIcon(new ImageIcon(
				"C:\\Users\\Kalin\\Documents\\Eclipse\\Diplomna_rabota\\icons\\icons8_information_60px_1.png"));
		btnPasswordChange = new JButton("Промяна на парола");
		btnPasswordChange.setBorder(null);
		btnPasswordChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				mt.setColor(btnPasswordChange);
				mt.resetColor(btnShowExamDates, btnShowConsDates);
			}
		});
		btnPasswordChange.setForeground(new Color(255, 255, 255));
		btnPasswordChange.setBackground(new Color(51, 153, 255));
		btnPasswordChange.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnPasswordChange.setBounds(10, 316, 165, 32);
		panel.add(btnPasswordChange);

		JLabel lblSem = new JLabel("Семестър");
		lblSem.setBackground(new Color(51, 153, 255));
		lblSem.setForeground(Color.WHITE);
		lblSem.setBounds(0, 110, 106, 20);
		panel.add(lblSem);
		lblSem.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		lblSem.setHorizontalAlignment(SwingConstants.CENTER);

		String[] semester = { "зимен", "летен" };
		cmbSem = new JComboBox(semester);
		cmbSem.setBounds(102, 107, 75, 22);
		panel.add(cmbSem);
		cmbSem.setFont(new Font("Calibri", Font.BOLD, 15));
	}
}
