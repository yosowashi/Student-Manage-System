package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 学生番号を取得
        String no = req.getParameter("no");

        // 学生データを取得
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(no);

        // SchoolデータをSessionから取得
        School school = (School) req.getSession().getAttribute("school");

        // クラス一覧を取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNums = classNumDao.filter(school);

        // JSPに渡す
        req.setAttribute("student", student);
        req.setAttribute("classNums", classNums);

        req.getRequestDispatcher("/main/student_update.jsp").forward(req, res);
    }
}