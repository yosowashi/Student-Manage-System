package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {
	String baseSql;
	
	public Student get(String no) throws Exception {
		Connection con = getConnection();
		String sql = "SELECT * FROM student WHERE no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, no);
		ResultSet rs = ps.executeQuery();
		Student student = null;
		if (rs.next()) {
			student = new Student();
			student.setNo(rs.getString("no"));
			student.setName(rs.getString("name"));
			student.setEntYear(rs.getInt("entyear"));
			student.setClassNum(rs.getString("classNum"));
			student.setAttend(rs.getBoolean("isAttend"));
		}
		return student;
	}
	
	private List<Student> postFilter(ResultSet resultSet, School school) throws Exception {
		List<Student> list = new ArrayList<>();
		while (resultSet.next()) {
			Student student = new Student();	
			student.setNo(resultSet.getString("no"));
	        student.setName(resultSet.getString("name"));
	        student.setEntYear(resultSet.getInt("entyear"));
	        student.setClassNum(resultSet.getString("classNum"));
	        student.setAttend(resultSet.getBoolean("isAttend"));
	        student.setSchool(school);
	        list.add(student);
		}
		return list;
	}
	
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		Connection con = getConnection();
		String sql = "SELECT * FROM student WHERE school_cd = ? AND entyear = ? AND classnum = ? AND isattend = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, school.getCd());
		ps.setInt(2, entYear);
		ps.setString(3, classNum);
		ps.setBoolean(4, isAttend);
		ResultSet rs = ps.executeQuery();
		return postFilter(rs, school);
	}
	
	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		Connection con = getConnection();
		String sql = "SELECT * FROM student WHERE school_cd = ? AND entyear = ? AND isattend = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, school.getCd());
		ps.setInt(2, entYear);
		ps.setBoolean(3, isAttend);
		ResultSet rs = ps.executeQuery();
		return postFilter(rs, school);
	}
	
	public List<Student> filter(School school, boolean isAttend) throws Exception {
		Connection con = getConnection();
		String sql = "SELECT * FROM student WHERE school_cd = ? AND isattend = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, school.getCd());
		ps.setBoolean(2, isAttend);
		ResultSet rs = ps.executeQuery();
		return postFilter(rs, school);
	}
	
	public boolean save(Student student) throws Exception {
		Connection con = getConnection();
		Student get = get(student.getNo());
		int count;
		if (get != null) {
			String sql = "UPDATE student SET name = ?, entyear = ?, classnum = ?, isattend = ?, school_cd = ? WHERE no = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getEntYear());
			ps.setString(3, student.getClassNum());
			ps.setBoolean(4, student.isAttend());
			ps.setString(5, student.getSchool().getCd());
			ps.setString(6, student.getNo());
			count = ps.executeUpdate();
		}
		else {
			String sql ="INSERT INTO student (no, name, entyear, classnum, isattend, school_cd) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student.getNo());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getEntYear());
			ps.setString(4, student.getClassNum());
			ps.setBoolean(5, student.isAttend());
			ps.setString(6, student.getSchool().getCd());
			count = ps.executeUpdate();
		}
		return count > 0;
	}
}
