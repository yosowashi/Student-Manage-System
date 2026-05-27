package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.StudentDao;

public class StudentCreateExecuteAction {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	System.out.println("ここまで来てる");
    	String no = req.getParameter("no");
    	String name = req.getParameter("name");

		String entYearStr = req.getParameter("ent_year");
		
		// 入力チェック（追加）
		if(entYearStr == null || entYearStr.isEmpty()) {
		    System.out.println("entYearエラー");
		    req.getRequestDispatcher("/main/student_create.jsp").forward(req, res);
		    return;
		}
		
		// 数値変換
		int entYear;
		try {
		    entYear = Integer.parseInt(entYearStr);
		} catch (NumberFormatException e) {
		    System.out.println("entYear変換エラー");
		    req.getRequestDispatcher("/main/student_create.jsp").forward(req, res);
		    return;
		}

    	String classNum = req.getParameter("class_num");
    	
    	//入力値チェック
    	if(no == null || no.isEmpty()) {
    		System.out.println("noエラー");
    		req.getRequestDispatcher("/main/student_create.jsp").forward(req, res);
    		return;
    	}
    	if(name == null || name.isEmpty()) {
    		System.out.println("nameエラー");
    		req.getRequestDispatcher("/main/student_create.jsp").forward(req, res);
    		return;
    	}
    	if(classNum == null || classNum.isEmpty()) {
    		System.out.println("classNumエラー");
    		req.getRequestDispatcher("/main/student_create.jsp").forward(req, res);
    		return;
    	}
    	
    	StudentDao studentDao = new StudentDao();
    	Student student = studentDao.get(no);
    	Student savestudent = new Student();
    	School school = (School) req.getSession().getAttribute("school");
    	savestudent.setSchool(school);
    	//重複チェック
    	if(student != null) {
    		System.out.println("エラー");
    		req.getRequestDispatcher("/main/student_create.jsp").forward(req, res);
    		return;
    	}
    	//登録
    	else{
    		savestudent.setNo(no);
    		savestudent.setName(name);
    		savestudent.setEntYear(entYear);
    		savestudent.setClassNum(classNum);
    		savestudent.setSchool(school);

		try {
		    studentDao.save(savestudent);
		    System.out.println("保存成功");
		} catch (Exception e) {
		    e.printStackTrace();  // ←これ超重要
		    throw e;
		}

    		req.getRequestDispatcher("/main/student_create_done.jsp").forward(req, res);
    	}
    }
}
