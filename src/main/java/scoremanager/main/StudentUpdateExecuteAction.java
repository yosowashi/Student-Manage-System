package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 入力値を取得
        String no       = req.getParameter("no");
        String name     = req.getParameter("name");
        String entYear  = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String isAttend = req.getParameter("isAttend");

        // バリデーション（空チェック）
        if (name == null || name.isEmpty() ||
            entYear == null || entYear.isEmpty() ||
            classNum == null || classNum.isEmpty()) {

            req.setAttribute("error", "このフィールドを入力してください");
            req.getRequestDispatcher("/main/student_update.jsp").forward(req, res);
            return;
        }

        // Schoolをセッションから取得
        School school = (School) req.getSession().getAttribute("school");

        // Studentオブジェクトに値をセット
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(Integer.parseInt(entYear));
        student.setClassNum(classNum);
        student.setAttend(isAttend != null);
        student.setSchool(school);

        // DAOで保存（UPDATE）
        StudentDao studentDao = new StudentDao();
        studentDao.save(student);

        // 完了画面へ遷移
        req.getRequestDispatcher("/jsp/student_update_done.jsp").forward(req, res);
    }
}