<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/main/base/header.jsp" />
<body>
<h1>メニュー画面</h1>
<p>${sessionScope.loginUser.id}</p>
<a href="${pageContext.request.contextPath}/StudentCreateAction.action">学生登録</a>
<jsp:include page="/main/base/footer.jsp" />
</body>
</html>