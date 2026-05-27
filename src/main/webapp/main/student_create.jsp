<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h2>学生登録</h2>
<form action="StudentCreateExecuteAction.action" method="post">
<label>入学年度</label><br>
<select name="ent_year">
<option value="2024">2024年度</option>
  <option value="2025">2025年度</option>
  <option value="2026" selected>2026年度</option>
  </select><br>
<label>学生番号</label><br>
<input type="text" name="no" required><br>
<label>氏名</label><br>
<input type="text" name="name"required><br>
<label>クラス</label><br>
<select name="class_num">
<c:forEach var="classNum" items="${classList}">
        <option value="${classNum}">
            ${classNum}
        </option>
    </c:forEach>
</select><br>
<input type="submit" name="end" value="登録して終了"></input>
</form>
</body>
</html>