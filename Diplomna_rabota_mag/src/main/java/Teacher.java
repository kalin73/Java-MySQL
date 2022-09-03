import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;

public class Teacher {
	private JFrame TeFrame;
	private JTextField textSpec;
	private JTextField textDate;
	private JTextField textHour;
	private JTextField textRoom;
	private JTextField textSubj;
	private JTextField textSelectId;
	private JTextField textIdCons;
	private JTextField textSelectId2;
	private JTextField textCdate;
	private JTextField textChour;
	private JTextField textCroom;
	private JTextField textSearchEx;
	private JComboBox cmbCourse;
	private JComboBox cmbDegree;
	private JComboBox cmbDegree2;
	private JComboBox cmbSem;
	private DefaultTableModel modelExamsBak;
	private DefaultTableModel modelExamsMag;
	private DefaultTableModel modelCons;
	private JTable tableBakExams;
	private JTable tableMagExams;
	private JTable tableConsultations;

	Student st = new Student();
	Methods mt = new Methods();
	LogIn log = new LogIn();
	private JTextField textCons;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void teacherUI(String[] array) {

		String[] teacherinfo = array;
		final String teacherName = teacherinfo[0];
		final String teacherFname = teacherinfo[1];

		TeFrame = new JFrame();
		TeFrame.getContentPane().setBackground(new Color(51, 153, 255));
		TeFrame.setUndecorated(true);
		TeFrame.setVisible(true);
		TeFrame.setBounds(510, 260, 1042, 513);
		TeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TeFrame.getContentPane().setLayout(null);

		JTabbedPane tabbedPaneInfo = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneInfo.setBackground(new Color(255, 255, 255));
		tabbedPaneInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		tabbedPaneInfo.setBounds(0, 35, 1041, 478);

		TeFrame.getContentPane().add(tabbedPaneInfo);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		modelExamsBak = new DefaultTableModel();
		String[] columnsBak = { "№", "Специалност", "Курс", "Дисциплина", "Семестър", "Пеподавател", "Дата", "Час",
				"Зала" };
		modelExamsBak.setColumnIdentifiers(columnsBak);

		modelExamsMag = new DefaultTableModel();
		String[] columnsMag = { "№", "Специалност", "Курс", "Дисциплина", "Семестър", "Пеподавател", "Дата", "Час",
				"Зала" };
		modelExamsMag.setColumnIdentifiers(columnsMag);

		modelCons = new DefaultTableModel();
		String[] columnsCons = { "№", "Пеподавател", "Дата", "Час", "Зала" };
		modelCons.setColumnIdentifiers(columnsCons);

		JPanel panelExams = new JPanel();
		panelExams.setBackground(new Color(255, 255, 255));
		tabbedPaneInfo.addTab("Изпити", null, panelExams, null);
		panelExams.setLayout(null);

		JLabel lblSpec = new JLabel("Специалност");
		lblSpec.setBounds(10, 50, 126, 45);
		lblSpec.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpec.setFont(new Font("Calibri", Font.BOLD, 15));
		panelExams.add(lblSpec);

		JLabel lblCourse = new JLabel("Курс");
		lblCourse.setBounds(10, 106, 126, 45);
		lblCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourse.setFont(new Font("Calibri", Font.BOLD, 15));
		panelExams.add(lblCourse);

		JLabel lblDeg = new JLabel("Степен");
		lblDeg.setBounds(10, 162, 126, 45);
		lblDeg.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeg.setFont(new Font("Calibri", Font.BOLD, 15));
		panelExams.add(lblDeg);

		JLabel lblSubj = new JLabel("Дисциплина");
		lblSubj.setBounds(10, 218, 126, 45);
		lblSubj.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubj.setFont(new Font("Calibri", Font.BOLD, 15));
		panelExams.add(lblSubj);

		JLabel lblDate = new JLabel("Дата");
		lblDate.setBounds(10, 274, 126, 45);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Calibri", Font.BOLD, 15));
		panelExams.add(lblDate);

		textSpec = new JTextField();
		textSpec.setFont(new Font("Calibri", Font.PLAIN, 15));
		textSpec.setBounds(146, 53, 122, 35);
		textSpec.setColumns(10);
		panelExams.add(textSpec);

		textDate = new JTextField();
		textDate.setFont(new Font("Calibri", Font.PLAIN, 15));
		textDate.setBounds(146, 277, 122, 35);
		textDate.setColumns(10);
		panelExams.add(textDate);

		JButton btnAddExam = new JButton("Добавяне на изпит");
		btnAddExam.setBackground(new Color(51, 153, 255));
		btnAddExam.setForeground(new Color(255, 255, 255));
		btnAddExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sem = cmbSem.getSelectedItem().toString();
				String spec = textSpec.getText();
				String course = cmbCourse.getSelectedItem().toString();
				String degree = cmbDegree.getSelectedItem().toString();
				String subject = textSubj.getText();
				String date = textDate.getText();
				String hour = textHour.getText();
				String room = textRoom.getText();

				mt.addExam(sem, degree, spec, course, subject, teacherName, teacherFname, date, hour, room);
				mt.refreshTable("бакалавър", modelExamsBak);
				mt.refreshTable("магистър", modelExamsMag);

			}
		});
		btnAddExam.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnAddExam.setBounds(341, 339, 183, 45);
		panelExams.add(btnAddExam);

		JButton btnRemoveExam = new JButton("Премахване на изпит");
		btnRemoveExam.setForeground(new Color(255, 255, 255));
		btnRemoveExam.setBackground(new Color(51, 153, 255));
		btnRemoveExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbDegree2.getSelectedItem().toString().equals("Бакалавър") && mt.validate("bakalavarexams",
						textSelectId.getText(), "idизпит", teacherName, teacherFname)) {

					if (JOptionPane.showConfirmDialog(TeFrame, "Искате ли да премахнете изпита?", "Премахване на изпит",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						mt.deleteRow(textSelectId.getText(), "bakalavarexams", "idизпит");
						mt.refreshTable("бакалавър", modelExamsBak);
						JOptionPane.showMessageDialog(null, "Успешно премахване");
					}
				} else if (cmbDegree2.getSelectedItem().toString().equals("Магистър")
						&& mt.validate("magistarexams", textSelectId.getText(), "idизпит", teacherName, teacherFname)) {
					if (JOptionPane.showConfirmDialog(TeFrame, "Искате ли да премахнете изпита?", "Премахване на изпит",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						mt.deleteRow(textSelectId.getText(), "magistarexams", "idизпит");
						mt.refreshTable("магистър", modelExamsMag);
						JOptionPane.showMessageDialog(null, "Успешно премахване");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Не можете да премахвате чужди изпити!");
				}
			}
		});
		btnRemoveExam.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnRemoveExam.setBounds(657, 339, 183, 45);
		panelExams.add(btnRemoveExam);

		JButton btnChange = new JButton("Промяна");
		btnChange.setForeground(new Color(255, 255, 255));
		btnChange.setBackground(new Color(51, 153, 255));
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sem = cmbSem.getSelectedItem().toString();
				String id = textSelectId.getText();
				String spec = textSpec.getText();
				String course = cmbCourse.getSelectedItem().toString();
				String degree = cmbDegree.getSelectedItem().toString();
				String subject = textSubj.getText();
				String date = textDate.getText();
				String hour = textHour.getText();
				String room = textRoom.getText();

				if (degree.equals("Бакалавър") && mt.validate("bakalavarexams", textSelectId.getText(), "idизпит",
						teacherName, teacherFname)) {
					mt.updateExams(degree, spec, course, sem, subject, date, hour, room, id);
					mt.refreshTable("бакалавър", modelExamsBak);
				} else if (degree.toString().equals("Магистър")
						&& mt.validate("magistarexams", textSelectId.getText(), "idизпит", teacherName, teacherFname)) {
					mt.updateExams(degree, spec, course,sem, subject, date, hour, room, id);
					mt.refreshTable("магистър", modelExamsMag);
					
				} else {
					JOptionPane.showMessageDialog(null, "Не можете да променяте чужди изпити!");
				}
			}
		});
		btnChange.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnChange.setBounds(341, 395, 183, 45);
		panelExams.add(btnChange);

		String[] courses = { "1", "2", "3", "4" };
		cmbCourse = new JComboBox(courses);
		cmbCourse.setFont(new Font("Calibri", Font.BOLD, 15));
		cmbCourse.setForeground(new Color(255, 255, 255));
		cmbCourse.setBackground(new Color(51, 153, 255));
		cmbCourse.setBounds(168, 111, 57, 31);
		panelExams.add(cmbCourse);

		String[] degrees = { "Бакалавър", "Магистър" };
		cmbDegree = new JComboBox(degrees);
		cmbDegree.setFont(new Font("Calibri", Font.BOLD, 15));
		cmbDegree.setBackground(new Color(51, 153, 255));
		cmbDegree.setForeground(new Color(255, 255, 255));
		cmbDegree.setBounds(146, 171, 100, 31);
		panelExams.add(cmbDegree);

		cmbDegree2 = new JComboBox(degrees);
		cmbDegree2.setFont(new Font("Calibri", Font.BOLD, 15));
		cmbDegree2.setForeground(new Color(255, 255, 255));
		cmbDegree2.setBackground(new Color(51, 153, 255));
		cmbDegree2.setBounds(901, 344, 100, 31);
		panelExams.add(cmbDegree2);
		
		String[] semester = { "зимен", "летен" };
		cmbSem = new JComboBox(semester);
		cmbSem.setForeground(Color.WHITE);
		cmbSem.setFont(new Font("Calibri", Font.BOLD, 15));
		cmbSem.setBackground(new Color(51, 153, 255));
		cmbSem.setBounds(157, 5, 89, 31);
		panelExams.add(cmbSem);

		JLabel lblHour = new JLabel("Час");
		lblHour.setBounds(10, 330, 126, 45);
		lblHour.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour.setFont(new Font("Calibri", Font.BOLD, 15));
		panelExams.add(lblHour);

		JLabel lblRoom = new JLabel("Зала");
		lblRoom.setBounds(10, 388, 126, 45);
		lblRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoom.setFont(new Font("Calibri", Font.BOLD, 15));
		panelExams.add(lblRoom);

		textHour = new JTextField();
		textHour.setFont(new Font("Calibri", Font.PLAIN, 15));
		textHour.setBounds(146, 334, 122, 33);
		textHour.setColumns(10);
		panelExams.add(textHour);

		textRoom = new JTextField();
		textRoom.setFont(new Font("Calibri", Font.PLAIN, 15));
		textRoom.setBounds(146, 391, 122, 35);
		textRoom.setColumns(10);
		panelExams.add(textRoom);

		UIManager.put("TabbedPane.selected", new Color(80, 180, 230));
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setFont(new Font("Calibri", Font.BOLD, 15));
		tabbedPane_3.setForeground(new Color(255, 255, 255));
		tabbedPane_3.setBackground(new Color(51, 153, 255));
		tabbedPane_3.setBounds(293, 0, 743, 270);
		panelExams.add(tabbedPane_3);

		JPanel panelBakExams = new JPanel();
		tabbedPane_3.addTab("Изпити-бакалавър", null, panelBakExams, null);
		panelBakExams.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 738, 240);
		panelBakExams.add(scrollPane);

		tableBakExams = new JTable();
		mt.refreshTable("бакалавър", modelExamsBak);
		tableBakExams.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRows = tableBakExams.getSelectedRow();
				textSelectId.setText(modelExamsBak.getValueAt(selectedRows, 0).toString());
				textSelectId2.setText(modelExamsBak.getValueAt(selectedRows, 0).toString());
				textSpec.setText(modelExamsBak.getValueAt(selectedRows, 1).toString());
				cmbDegree.setSelectedItem("Бакалавър");
				cmbDegree2.setSelectedItem("Бакалавър");
				cmbSem.setSelectedItem(modelExamsBak.getValueAt(selectedRows, 4).toString());
				cmbCourse.setSelectedItem(modelExamsBak.getValueAt(selectedRows, 2).toString());
				textSubj.setText(modelExamsBak.getValueAt(selectedRows, 3).toString());
				textDate.setText(modelExamsBak.getValueAt(selectedRows, 6).toString());
				textHour.setText(modelExamsBak.getValueAt(selectedRows, 7).toString());
				textRoom.setText(modelExamsBak.getValueAt(selectedRows, 8).toString());
			}
		});
		tableBakExams.setShowVerticalLines(false);
		tableBakExams.setRowHeight(25);
		tableBakExams.setIntercellSpacing(new Dimension(0, 0));
		tableBakExams.setFocusable(false);
		tableBakExams.setShowGrid(false);
		tableBakExams.setBorder(null);
		tableBakExams.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tableBakExams.getTableHeader().setOpaque(false);
		tableBakExams.setRowHeight(25);
		tableBakExams.getTableHeader().setBackground(new Color(255, 255, 255));
		tableBakExams.setModel(modelExamsBak);
		tableBakExams.getColumnModel().getColumn(0).setPreferredWidth(23);
		tableBakExams.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableBakExams.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableBakExams.getColumnModel().getColumn(3).setPreferredWidth(160);
		tableBakExams.getColumnModel().getColumn(4).setPreferredWidth(60);
		tableBakExams.getColumnModel().getColumn(5).setPreferredWidth(130);
		tableBakExams.getColumnModel().getColumn(7).setPreferredWidth(70);
		tableBakExams.getColumnModel().getColumn(8).setPreferredWidth(50);
		tableBakExams.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableBakExams.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableBakExams.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableBakExams.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		tableBakExams.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		tableBakExams.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
		scrollPane.setViewportView(tableBakExams);

		RowSorter<DefaultTableModel> sorteBakExams = new TableRowSorter<DefaultTableModel>(modelExamsBak);
		tableBakExams.setRowSorter(sorteBakExams);

		JPanel panelMagExams = new JPanel();
		tabbedPane_3.addTab("Изпити-магистър", null, panelMagExams, null);
		panelMagExams.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 738, 240);
		panelMagExams.add(scrollPane_1);

		tableMagExams = new JTable();
		mt.refreshTable("магистър", modelExamsMag);
		tableMagExams.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRows = tableMagExams.getSelectedRow();
				cmbDegree.setSelectedItem("Магистър");
				cmbDegree2.setSelectedItem("Магистър");
				cmbCourse.setSelectedItem("1");
				cmbSem.setSelectedItem(modelExamsMag.getValueAt(selectedRows, 4).toString());
				textSelectId.setText(modelExamsMag.getValueAt(selectedRows, 0).toString());
				textSelectId2.setText(modelExamsMag.getValueAt(selectedRows, 0).toString());
				textSpec.setText(modelExamsMag.getValueAt(selectedRows, 1).toString());
				textSubj.setText(modelExamsMag.getValueAt(selectedRows, 3).toString());
				textDate.setText(modelExamsMag.getValueAt(selectedRows, 6).toString());
				textHour.setText(modelExamsMag.getValueAt(selectedRows, 7).toString());
				textRoom.setText(modelExamsMag.getValueAt(selectedRows, 8).toString());
			}
		});
		tableMagExams.setShowVerticalLines(false);
		tableMagExams.setRowHeight(25);
		tableMagExams.setIntercellSpacing(new Dimension(0, 0));
		tableMagExams.setFocusable(false);
		tableMagExams.setShowGrid(false);
		tableMagExams.setBorder(null);
		tableMagExams.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tableMagExams.getTableHeader().setOpaque(false);
		tableMagExams.setRowHeight(25);
		tableMagExams.getTableHeader().setBackground(new Color(255, 255, 255));
		tableMagExams.setModel(modelExamsMag);
		tableMagExams.getColumnModel().getColumn(0).setPreferredWidth(23);
		tableMagExams.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableMagExams.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableMagExams.getColumnModel().getColumn(3).setPreferredWidth(160);
		tableMagExams.getColumnModel().getColumn(4).setPreferredWidth(60);
		tableMagExams.getColumnModel().getColumn(5).setPreferredWidth(130);
		tableMagExams.getColumnModel().getColumn(7).setPreferredWidth(70);
		tableMagExams.getColumnModel().getColumn(8).setPreferredWidth(50);
		tableMagExams.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tableMagExams.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tableMagExams.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableMagExams.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		tableMagExams.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		tableMagExams.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
		scrollPane_1.setViewportView(tableMagExams);

		RowSorter<DefaultTableModel> sorteMagExams = new TableRowSorter<DefaultTableModel>(modelExamsMag);
		tableMagExams.setRowSorter(sorteMagExams);

		JButton btnUpdateExamsTable = new JButton("Обнови таблиците");
		btnUpdateExamsTable.setForeground(new Color(255, 255, 255));
		btnUpdateExamsTable.setBackground(new Color(51, 153, 255));
		btnUpdateExamsTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mt.refreshTable("бакалавър", modelExamsBak);
				mt.refreshTable("магистър", modelExamsMag);
			}
		});
		btnUpdateExamsTable.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnUpdateExamsTable.setBounds(657, 395, 183, 45);
		panelExams.add(btnUpdateExamsTable);

		textSubj = new JTextField();
		textSubj.setFont(new Font("Calibri", Font.PLAIN, 14));
		textSubj.setColumns(10);
		textSubj.setBounds(124, 221, 158, 35);
		panelExams.add(textSubj);

		textSelectId = new JTextField();
		textSelectId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textSelectId.setText("");
			}
		});
		textSelectId.setText("№");
		textSelectId.setHorizontalAlignment(SwingConstants.CENTER);
		textSelectId.setFont(new Font("Calibri", Font.PLAIN, 15));
		textSelectId.setColumns(10);
		textSelectId.setBounds(850, 344, 41, 35);
		panelExams.add(textSelectId);
		textSelectId2 = new JTextField();
		textSelectId2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textSelectId2.setText("");
			}
		});
		textSelectId2.setText("№");
		textSelectId2.setHorizontalAlignment(SwingConstants.CENTER);
		textSelectId2.setFont(new Font("Calibri", Font.PLAIN, 15));
		textSelectId2.setColumns(10);
		textSelectId2.setBounds(534, 400, 41, 35);
		panelExams.add(textSelectId2);

		JLabel lblSearchEx = new JLabel("Търсене");
		lblSearchEx.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchEx.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSearchEx.setBounds(293, 281, 126, 45);
		panelExams.add(lblSearchEx);

		textSearchEx = new JTextField();
		textSearchEx.setBorder(null);
		textSearchEx.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = textSearchEx.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelExamsBak);
				TableRowSorter<DefaultTableModel> tr2 = new TableRowSorter<DefaultTableModel>(modelExamsMag);
				tableBakExams.setRowSorter(tr);
				tableMagExams.setRowSorter(tr2);
				tr.setRowFilter(RowFilter.regexFilter(search));
				tr2.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		textSearchEx.setFont(new Font("Calibri", Font.PLAIN, 15));
		textSearchEx.setColumns(10);
		textSearchEx.setBounds(393, 288, 296, 26);
		panelExams.add(textSearchEx);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		separator.setBounds(393, 314, 296, 14);
		panelExams.add(separator);

		JLabel lblSearchIcon = new JLabel("");
		lblSearchIcon.setIcon(
				new ImageIcon("C:\\Users\\Kalin\\Documents\\Eclipse\\Diplomna_rabota\\icons\\icons8_search_24px.png"));
		lblSearchIcon.setBounds(699, 281, 29, 42);
		panelExams.add(lblSearchIcon);

		JLabel lblSem = new JLabel("Семестър");
		lblSem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSem.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSem.setBounds(28, 7, 80, 26);
		panelExams.add(lblSem);

		JPanel panelConsultations = new JPanel();
		panelConsultations.setBackground(new Color(255, 255, 255));
		tabbedPaneInfo.addTab("Консултации", null, panelConsultations, null);
		panelConsultations.setLayout(null);

		JLabel lblConsDate = new JLabel("Дата");
		lblConsDate.setBounds(20, 30, 83, 45);
		lblConsDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsDate.setFont(new Font("Calibri", Font.BOLD, 15));
		panelConsultations.add(lblConsDate);

		textCdate = new JTextField();
		textCdate.setBounds(113, 40, 178, 20);
		textCdate.setColumns(10);
		panelConsultations.add(textCdate);

		JButton btnRemoveCons = new JButton("Премахване");
		btnRemoveCons.setForeground(new Color(255, 255, 255));
		btnRemoveCons.setBackground(new Color(51, 153, 255));
		btnRemoveCons.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnRemoveCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (mt.validate("consultations", textIdCons.getText(), "idконсултация", teacherName, teacherFname)) {

					if (JOptionPane.showConfirmDialog(TeFrame, "Искате ли да премахнете консултацията?",
							"Премахване на консултация", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						mt.deleteRow(textIdCons.getText(), "consultations", "idконсултация");
						mt.showConsultations(modelCons);
						JOptionPane.showMessageDialog(null, "Успешно премахване");

					}
				} else {
					JOptionPane.showMessageDialog(null, "Не можете да премахвате чужди консултации!");
				}
			}
		});
		btnRemoveCons.setBounds(200, 200, 149, 45);
		panelConsultations.add(btnRemoveCons);

		JButton btnAddCons = new JButton("Добавяне");
		btnAddCons.setForeground(new Color(255, 255, 255));
		btnAddCons.setBackground(new Color(51, 153, 255));
		btnAddCons.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnAddCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String date = textCdate.getText();
				String hour = textChour.getText();
				String room = textCroom.getText();
				mt.addConsultation(teacherName, teacherFname, date, hour, room);
				mt.showConsultations(modelCons);

			}
		});
		btnAddCons.setBounds(10, 200, 163, 45);
		panelConsultations.add(btnAddCons);

		JLabel lblConsHour = new JLabel("\u0427\u0430\u0441");
		lblConsHour.setBounds(20, 86, 83, 45);
		lblConsHour.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsHour.setFont(new Font("Calibri", Font.BOLD, 15));
		panelConsultations.add(lblConsHour);

		textChour = new JTextField();
		textChour.setBounds(113, 96, 178, 20);
		textChour.setColumns(10);
		panelConsultations.add(textChour);

		textIdCons = new JTextField();
		textIdCons.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textIdCons.setText("");
			}
		});
		textIdCons.setFont(new Font("Calibri", Font.PLAIN, 14));
		textIdCons.setText("\u2116");
		textIdCons.setHorizontalAlignment(SwingConstants.CENTER);
		textIdCons.setBounds(363, 240, 44, 29);
		panelConsultations.add(textIdCons);
		textIdCons.setColumns(10);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(422, 11, 573, 317);
		panelConsultations.add(scrollPane_2);

		tableConsultations = new JTable();
		mt.showConsultations(modelCons);
		tableConsultations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRows = tableConsultations.getSelectedRow();
				textIdCons.setText(modelCons.getValueAt(selectedRows, 0).toString());
				textCdate.setText(modelCons.getValueAt(selectedRows, 2).toString());
				textChour.setText(modelCons.getValueAt(selectedRows, 3).toString());
				textCroom.setText(modelCons.getValueAt(selectedRows, 4).toString());
			}
		});
		tableConsultations.setShowVerticalLines(false);
		tableConsultations.setRowMargin(0);
		tableConsultations.setRowHeight(25);
		tableConsultations.setIntercellSpacing(new Dimension(0, 0));
		tableConsultations.setFocusable(false);
		tableConsultations.setShowGrid(false);
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
		scrollPane_2.setViewportView(tableConsultations);

		RowSorter<DefaultTableModel> sorteCons = new TableRowSorter<DefaultTableModel>(modelCons);
		tableConsultations.setRowSorter(sorteCons);

		JLabel lblConsRoom = new JLabel("Зала");
		lblConsRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsRoom.setFont(new Font("Calibri", Font.BOLD, 15));
		lblConsRoom.setBounds(20, 142, 83, 45);
		panelConsultations.add(lblConsRoom);

		textCroom = new JTextField();
		textCroom.setColumns(10);
		textCroom.setBounds(113, 152, 178, 20);
		panelConsultations.add(textCroom);

		JButton btnChangeCons = new JButton("Промяна на консултация");
		btnChangeCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textIdCons.getText();
				String date = textCdate.getText();
				String hour = textChour.getText();
				String room = textCroom.getText();
				if (mt.validate("consultations", textIdCons.getText(), "idконсултация", teacherName, teacherFname)) {
					mt.updateCons(date, hour, room, id);
					mt.showConsultations(modelCons);
				} else {
					JOptionPane.showMessageDialog(null, "Не можете да променяте чужди консултации!");
				}
			}
		});
		btnChangeCons.setForeground(new Color(255, 255, 255));
		btnChangeCons.setBackground(new Color(51, 153, 255));
		btnChangeCons.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnChangeCons.setBounds(10, 270, 339, 45);
		panelConsultations.add(btnChangeCons);

		JLabel lblSearchCons = new JLabel("Търсене");
		lblSearchCons.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchCons.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSearchCons.setBounds(390, 339, 126, 45);
		panelConsultations.add(lblSearchCons);

		textCons = new JTextField();
		textCons.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = textCons.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelCons);
				tableConsultations.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		textCons.setFont(new Font("Calibri", Font.PLAIN, 15));
		textCons.setColumns(10);
		textCons.setBorder(null);
		textCons.setBounds(490, 346, 296, 26);
		panelConsultations.add(textCons);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(490, 372, 296, 14);
		panelConsultations.add(separator_1);

		JLabel lblSearchIcon2 = new JLabel("");
		lblSearchIcon2.setIcon(
				new ImageIcon("C:\\Users\\Kalin\\Documents\\Eclipse\\Diplomna_rabota\\icons\\icons8_search_24px.png"));
		lblSearchIcon2.setBounds(793, 339, 29, 42);
		panelConsultations.add(lblSearchIcon2);

		JLabel lblConsInfo = new JLabel("Информация за консултацията");
		lblConsInfo.setForeground(Color.GRAY);
		lblConsInfo.setFont(new Font("Calibri", Font.ITALIC, 14));
		lblConsInfo.setBounds(20, 5, 197, 19);
		panelConsultations.add(lblConsInfo);

		JLabel lblExit = new JLabel("");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(TeFrame, "Искате ли да затворите програмата?", "Затваряне",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		lblExit.setIcon(
				new ImageIcon("C:\\Users\\Kalin\\Documents\\Eclipse\\Diplomna_rabota\\icons\\icons8_Close_30px.png"));
		lblExit.setBounds(1000, 0, 32, 32);
		TeFrame.getContentPane().add(lblExit);

		JLabel lblLogout = new JLabel("");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(TeFrame, "Искате ли да излезете?", "Излизане",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					TeFrame.setVisible(false);
					LogIn.frame.setVisible(true);
				}
			}
		});
		lblLogout.setIcon(new ImageIcon(
				"C:\\Users\\Kalin\\Documents\\Eclipse\\Diplomna_rabota\\icons\\icons8_logout_rounded_left_32px.png"));
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setBounds(10, 0, 32, 32);
		TeFrame.getContentPane().add(lblLogout);

	}
}
