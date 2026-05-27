package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;


public class ClassNumDao extends Dao {
	public ClassNum get(String class_num,School school) throws Exception {
		Connection con = getConnection();
		String sql = "SELECT * FROM class_num WHERE class_num = ? AND school_cd = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, class_num);
		ps.setString(1, school.getCd());
		ResultSet rs = ps.executeQuery();
		ClassNum classnum = null;
		if(rs.next()) {
			classnum = new ClassNum();
			classnum.setClass_num(rs.getString("class_num"));
		}
		return classnum;
	}
	public List<String> filter(School school) throws Exception {
		List<String> list = new ArrayList<>();
		Connection con = getConnection();
		String sql = "SELECT class_num FROM class_num WHERE school_cd = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, school.getCd());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			list.add(rs.getString("class_num"));
		}
		return list;
	}
	public boolean save(ClassNum classNum) throws Exception {
		Connection con = getConnection();
		String sql = "INSERT INTO class_num(class_num, school_cd) VALUES (?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, classNum.getClass_num());
		ps.setString(2, classNum.getSchool().getCd());
		int count = ps.executeUpdate();
		return count > 0;

	}
	public boolean save(ClassNum classNum, String newClassNum) throws Exception {
		Connection con = getConnection();
		String sql = "UPDATE class_num SET class_num = ? WHERE school_cd = ? AND class_num = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, newClassNum);
		ps.setString(2, classNum.getSchool().getCd());
		ps.setString(3, classNum.getClass_num());
		int count = ps.executeUpdate();
		return count > 0;
	}
}
