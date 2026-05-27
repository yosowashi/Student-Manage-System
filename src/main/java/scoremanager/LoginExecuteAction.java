package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;
public class LoginExecuteAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String pas = req.getParameter("password");
		TeacherDao teacherDao = null;
		Teacher teacher;
		teacherDao = new TeacherDao();
		try {
				teacher = teacherDao.login(id, pas);
				if(teacher != null) {
					HttpSession session = req.getSession();
					session.setAttribute("loginUser",teacher);
					req.getRequestDispatcher("/main/menu.jsp").forward(req, res);
					return;
				} else {
						req.setAttribute("loginError", "ログインに失敗しました。IDまたはパスワードが違います");
						req.getRequestDispatcher("/main/login.jsp").forward(req, res);
						return;}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
