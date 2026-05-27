package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class LogoutAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.getSession().invalidate();
		req.getRequestDispatcher("/main/logout.jsp").forward(req, res);
	}
}
