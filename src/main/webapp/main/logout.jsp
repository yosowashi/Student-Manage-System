<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得点管理システム</title>
</head>
<body>
<h2>ログアウト</h2>
<p>右に何も表示されなかったらセッション削除成功${sessionScope.loginUser.id}</p>
<label><p>ログアウトしました</p></label>
<a href="${pageContext.request.contextPath}/LoginAction.action">ログイン</a>
<jsp:include page="/main/base/footer.jsp" />
</body>
</html>