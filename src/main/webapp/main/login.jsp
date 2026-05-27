<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/main/base/header.jsp" />
<body>
<h2>ログイン画面</h2>
<p>${requestScope.loginError}</p>
<form action="${pageContext.request.contextPath}/LoginExecuteAction.action" method="post">
ID<input type="text" name="id" required><br>
パスワード<input type="text" name="password" required><br>
<label><input type="checkbox" name="chk_d_ps">パスワードを表示</label><br>
<input type="submit" name="login" value="ログイン"></input>
</form>
<jsp:include page="/main/base/footer.jsp" />
</body>
</html>