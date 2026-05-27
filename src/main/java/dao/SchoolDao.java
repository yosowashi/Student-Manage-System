package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends Dao {
	public School get(String cd) throws Exception {
		Connection con = getConnection();
		//sqlの準備
		String sql = "SELECT * FROM school WHERE cd = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		//?の値
		ps.setString(1, cd);
		//sql実行
		ResultSet rs = ps.executeQuery();
		//objectに保存
		School school = null;
		if (rs.next()) {
			school = new School();
			school.setCd(rs.getString("cd"));
			school.setName(rs.getString("name"));
		}
		return school;
	}
}
