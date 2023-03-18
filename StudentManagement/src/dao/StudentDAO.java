package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
import utils.DBHelper;

public class StudentDAO {

	private StudentDAO() {
	}

	public static StudentDAO getInstance() {
		return new StudentDAO();
	}

	public int getTotal() {
		int total = 0;
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select count(*) from student";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}

			System.out.println("total:" + total);

			DBHelper.closeConnection(c, s, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public boolean add(Student student) {

		try {

			Connection c = DBHelper.getInstance().getConnection();

			String sql = "insert into student values(null,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getStuNumber());
			ps.setString(3, student.getMajor());

			boolean result = ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				long id = rs.getLong(1);
				student.setId(id);
			}

			DBHelper.closeConnection(c, ps, rs);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Student student) {
		try {

			Connection c = DBHelper.getInstance().getConnection();

			String sql = "update student set name= ?, stu_number = ? , major = ? where id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getStuNumber());
			ps.setString(3, student.getMajor());
			ps.setLong(4, student.getId());

			boolean result = ps.execute();

			DBHelper.closeConnection(c, ps, null);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean delete(int id) {
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "delete from student where id = " + id;

			boolean result = s.execute(sql);

			DBHelper.closeConnection(c, s, null);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Student get(int id) {
		Student student = null;
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select * from student where id = " + id;

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				student = new Student();
				String name = rs.getString("name");
				String stuNumber = rs.getString("stu_number");
				String major = rs.getString("major");
				student.setName(name);
				student.setStuNumber(stuNumber);
				student.setMajor(major);
				student.setId(id);
			}

			DBHelper.closeConnection(c, s, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	public List<Student> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Student> list(int start, int count) {
		List<Student> students = new ArrayList<Student>();

		try {

			Connection c = DBHelper.getInstance().getConnection();

			String sql = "select * from student order by id desc limit ?,? ";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student student = new Student();
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String stuNumber = rs.getString("stu_number");
				String major = rs.getString("major");
				student.setName(name);
				student.setStuNumber(stuNumber);
				student.setMajor(major);
				student.setId(id);
				students.add(student);
			}
			DBHelper.closeConnection(c, ps, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

}
