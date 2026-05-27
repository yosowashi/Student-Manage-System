package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import scoremanager.LoginAction;
import scoremanager.LoginExecuteAction;
import scoremanager.LogoutAction;
import scoremanager.main.StudentCreateAction;
import scoremanager.main.StudentCreateExecuteAction;
@WebServlet("*.action")
public class FrontController extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		execute(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		execute(req,res);
	}
	private void execute(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath().substring(1);
		
        String name = path.replace(".action","");
        try {
	        if("LoginAction".equals(name)) {
				LoginAction loginAction = new LoginAction();
				loginAction.execute(req, res);
	        }
	        if("LoginExecuteAction".equals(name)) {
	        	LoginExecuteAction loginExecuteAction = new LoginExecuteAction();
	        	loginExecuteAction.execute(req, res);
	        }
	        if("LogoutAction".equals(name)) {
	        	LogoutAction logoutAction = new LogoutAction();
	        	logoutAction.execute(req, res);
	        }
	        if("StudentCreateAction".equals(name)) {
	        	StudentCreateAction studentCreateAction = new StudentCreateAction();
	        	studentCreateAction.execute(req, res);
	        }
	        if("StudentCreateExecuteAction".equals(name)) {
	        	StudentCreateExecuteAction studentCreateExecuteAction = new StudentCreateExecuteAction();
	        	studentCreateExecuteAction.execute(req, res);
	        }
        }catch (Exception e) {
        	try {
				req.getRequestDispatcher("/main/error.jsp").forward(req, res);
				e.printStackTrace();
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
        	return;
        }
	}
}
 