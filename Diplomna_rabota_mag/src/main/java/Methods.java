import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Methods {

	public Statement connection() throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "root");
		Statement stm = con.createStatement();
		return stm;
	}

	public boolean invalidChars(String uName, String pass) {
		boolean invalid = true;
		for (int i = 0; i < uName.length(); i++) {
			char letter = uName.charAt(i);

			if (letter == '\'' || letter == '"' || letter == '\\') {
				invalid = true;
				break;
			} else {
				invalid = false;
			}
		}
		for (int i = 0; i < pass.length(); i++) {
			char letter = pass.charAt(i);

			if (letter == '\'' || letter == '"' || letter == '\\') {
				invalid = true;
				break;
			}
		}
		return invalid;
	}

	public void login(String table, String uname, JTextField textF, JPasswordField passwordF, JFrame frame) {
		Student st = new Student();
		Teacher te = new Teacher();
		try {
			String username = textF.getText();
			char[] pass = passwordF.getPassword();
			String password = String.valueOf(pass);

			if (invalidChars(username, password)) {
				JOptionPane.showMessageDialog(null,
						"��������� �� ���������: ������� (\"), �������� (') � ������� ��������� �����(\\)");
			} else {
				String[] stdInfo = studentInfo(username);
				String[] teachInfo = teacherInfo(username);
				String sql = String.format("select * from %s where %s='%s' and ������='%s'", table, uname, username,
						password);
				ResultSet rs = connection().executeQuery(sql);
				if (rs.next()) {

					if (table.equals("students")) {

						frame.setVisible(false);
						st.studentUI(stdInfo);

					} else {
						frame.setVisible(false);
						te.teacherUI(teachInfo);
					}
				} else {
					JOptionPane.showMessageDialog(null, "������ �����!");
					textF.setText("");
					passwordF.setText("");
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public String[] studentInfo(String fNumber) {
		String[] info = new String[6];
		try {

			String sql = String.format("select * from students where ���������������='%s'", fNumber);
			ResultSet rs = connection().executeQuery(sql);

			info[0] = fNumber;
			if (rs.next()) {
				info[1] = rs.getString("���");
				info[2] = rs.getString("�������");
				info[3] = rs.getString("������");
				info[4] = rs.getString("�����������");
				info[5] = rs.getString("����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return info;

	}

	public void getExams(String degree, DefaultTableModel model, String course, String spec, String sem) {

		int count = model.getRowCount();
		if (count > 0) {
			for (int i = count - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		String sql, sql2;
		String table1, table2, idCow;
		String id, sub, teacher, date, hour, room;
		try {
			if (degree.equals("���������")) {
				table1 = "bakalavar_subjects";
				table2 = "bakalavarexams";
				idCow = "id����������";
			} else {
				table1 = "magistar_subjects";
				table2 = "magistarexams";
				idCow = "id����������_���";
			}
			sql = String.format("select * from %s where ����='%s' and �����������='%s' and ��������='%s'", table1,
					course, spec, sem);
			ResultSet rs = connection().executeQuery(sql);
			while (rs.next()) {
				id = rs.getString("id����������");
				sub = rs.getString("����������");
				sql2 = String.format("select * from %s where %s='%s'", table2, idCow, id);
				ResultSet rs2 = connection().executeQuery(sql2);
				if (rs2.next()) {
					teacher = rs2.getString("������������");
					date = rs2.getString("����");
					hour = rs2.getString("���");
					room = rs2.getString("����");
					String[] row = { sub, teacher, date, hour, room };
					model.addRow(row);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public String[] teacherInfo(String email) {

		String[] info = new String[2];
		try {
			String sql = String.format("select * from teachers where �����='%s'", email);
			ResultSet rs = connection().executeQuery(sql);

			while (rs.next()) {
				info[0] = rs.getString("���");
				info[1] = rs.getString("�������");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	public void changePassword(JPasswordField passwordOldPass, String fNumber, JPasswordField passwordNewPass) {

		char[] oldPassword = passwordOldPass.getPassword();
		String oldPass = String.valueOf(oldPassword);
		char[] newPassword = passwordNewPass.getPassword();
		String newPass = String.valueOf(newPassword);
		try {
			String sql = String.format("select * from students where ���������������='%s' and ������='%s'", fNumber,
					oldPass);
			ResultSet rs = connection().executeQuery(sql);

			if (rs.next()) {
				sql = String.format("update students set ������='%s' where ���������������='%s'", newPass, fNumber);
				connection().executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "��������� �� �������� � �������!");
			} else {
				JOptionPane.showMessageDialog(null, "������ ����� ������!");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public String getSubject(String degree, String spec, String course, String sem, String subject) {
		String id = null;
		try {
			if (degree.equals("���������")) {

				String sql = String.format(
						"select * from bakalavar_subjects where �����������='%s' and ����='%s' and ��������='%s' and ����������='%s'",
						spec, course, sem, subject);
				ResultSet rs = connection().executeQuery(sql);
				if (rs.next()) {
					id = rs.getString("id����������");
				}
			} else {
				String sql = String.format(
						"select * from magistar_subjects where �����������='%s' and ����='%s' and ��������='%s'", spec,
						course, sem, subject);
				ResultSet rs = connection().executeQuery(sql);
				if (rs.next()) {
					id = rs.getString("id����������");
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return id;
	}

	public void showConsultations(DefaultTableModel model) {
		int count = model.getRowCount();
		if (count > 0) {
			for (int i = count - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		try {

			String sql = String.format("select * from consultations");
			ResultSet rs = connection().executeQuery(sql);
			String id, teacher, date, hour, room;
			while (rs.next()) {

				id = rs.getString("id�����������");
				teacher = rs.getString("������������");
				date = rs.getString("����");
				hour = rs.getString("���");
				room = rs.getString("����");

				String[] row = { id, teacher, date, hour, room };
				model.addRow(row);

			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}

	public void addExam(String sem, String degree, String spec, String course, String subject, String teacherName,
			String teacherFname, String date, String hour, String room) {

		String sql = null;

		String idSubj = getSubject(degree, spec, course, sem, subject);
		try {
			if (degree.equals("���������")) {
				sql = String.format(
						"insert into bakalavarexams(id����������,������������,����,���,����)values('%s','���. %s %s','%s','%s','%s')",
						idSubj, teacherName, teacherFname, date, hour, room);
			} else {

				sql = String.format(
						"insert into magistarexams(id����������_���,������������,����,���,����)values('%s','���. %s %s','%s','%s','%s')",
						idSubj, teacherName, teacherFname, date, hour, room);
			}
			connection().executeUpdate(sql);

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "���� �������� ���� �� ������!\n���������� ������ � ����-��-��");
		}
	}

	public void addConsultation(String teacherName, String teacherFname, String date, String hour, String room) {

		try {

			String sql = String.format(
					"insert into consultations(������������,����,���,����)values('���. %s %s','%s','%s','%s')",
					teacherName, teacherFname, date, hour, room);
			connection().executeUpdate(sql);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "���� ������� ���� � ���");
		}

	}

	public boolean validate(String table, String id, String key, String name, String fname) {
		boolean valid = false;
		String teacher = String.format("���. %s %s", name, fname);
		try {

			String sql = String.format("select * from %s where %s='%s'", table, key, id);
			ResultSet rs = connection().executeQuery(sql);
			if (rs.next()) {

				if (rs.getString("������������").equals(teacher)) {
					valid = true;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return valid;
	}


	public void refreshTable(String degree, DefaultTableModel model) {
		int count = model.getRowCount();

		if (count > 0) {
			for (int i = count - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		String sql, sql2;
		String table1, table2, idCow;
		String id, idSbj, spec, course, sub, sem, teacher, date, hour, room;
		try {
			if (degree.equals("���������")) {
				table1 = "bakalavar_subjects";
				table2 = "bakalavarexams";
				idCow = "id����������";
			} else {
				table1 = "magistar_subjects";
				table2 = "magistarexams";
				idCow = "id����������_���";
			}
			sql = String.format("select * from %s", table2);
			ResultSet rs = connection().executeQuery(sql);
			while (rs.next()) {
				id = rs.getString("id�����");
				idSbj = rs.getString(idCow);
				teacher = rs.getString("������������");
				date = rs.getString("����");
				hour = rs.getString("���");
				room = rs.getString("����");
				sql2 = String.format("select * from %s where %s='%s'", table1, "id����������", idSbj);
				ResultSet rs2 = connection().executeQuery(sql2);
				if (rs2.next()) {
					spec = rs2.getString("�����������");
					course = rs2.getString("����");
					sem = rs2.getString("��������");
					sub = rs2.getString("����������");
					String[] row = { id, spec, course, sub, sem, teacher, date, hour, room };
					model.addRow(row);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void deleteRow(String id, String table, String key) {

		try {
			String sql = String.format("delete from %s where %s='%s'", table, key, id);
			connection().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateCons(String date, String hour, String room, String id) {

		try {
			String sql = String.format(
					"update consultations set ����='%s', ���='%s', ����='%s' where id�����������='%s'", date, hour,
					room, id);
			connection().executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "������� �������");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "���� �������� ���� �� ������!\n���������� ������ � ����-��-��");
		}
	}

	public void updateExams(String degree, String spec, String course, String sem, String subj, String date,
			String hour, String room, String id) {

		String sql = null;
		String idsubj = getSubject(degree, spec, course, sem, subj);
		try {

			if (degree.equals("���������")) {
				sql = String.format(
						"update bakalavarexams set id����������='%s', ����='%s', ���='%s', ����='%s' where id�����='%s'",
						idsubj, date, hour, room, id);
			} else {

				sql = String.format(
						"update magistarexams set id����������_���='%s', ����='%s', ���='%s', ����='%s' where id�����='%s'",
						idsubj, date, hour, room, id);
			}
			connection().executeUpdate(sql);

			JOptionPane.showMessageDialog(null, "������� �������");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "���� �������� ���� �� ������!\n���������� ������ � ����-��-��");
		}

	}

	public void setColor(JButton button) {

		button.setBackground(new Color(72, 175, 237));
	}

	public void resetColor(JButton button1, JButton button2) {

		button1.setBackground(new Color(51, 153, 255));
		button2.setBackground(new Color(51, 153, 255));
	}
}