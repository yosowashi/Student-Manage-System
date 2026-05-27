package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {
	public Teacher get(String id) throws Exception {
		Connection con = getConnection();
		String sql = "SELECT * FROM teacher WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		Teacher teacher = null;
		if(rs.next()) {
			teacher = new Teacher();
			teacher.setId(rs.getString("id"));
			teacher.setName(rs.getString("name"));
			teacher.setPassword(rs.getString("password"));
		}
		return teacher;
	}
	
	public Teacher login(String id, String password) throws Exception {
		Connection con = getConnection();
		String sql = "SELECT * FROM teacher WHERE id = ? AND password = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		Teacher teacher = null;
		if(rs.next()) {
			teacher = new Teacher();
			teacher.setId(rs.getString("id"));
			teacher.setPassword(rs.getString("password"));
			String schoolCd = rs.getString("school_cd");
	        SchoolDao schoolDao = new SchoolDao();
	        School school = schoolDao.get(schoolCd);
	        teacher.setSchool(school);
		}
		return teacher;
	}
}

