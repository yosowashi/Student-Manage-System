package scoremanager.main;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		Teacher teacher = (Teacher) session.getAttribute("loginUser");
		School school = teacher.getSchool();
		ClassNumDao classNumDao = new ClassNumDao();
		List<String> classList;
		try {
			classList = classNumDao.filter(school);
			System.out.println("classList = " + classList);
			System.out.println("school_cd = [" + school.getCd() + "]");
			req.setAttribute("classList", classList);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		req.getRequestDispatcher("/main/student_create.jsp").forward(req, res);
	}
}
