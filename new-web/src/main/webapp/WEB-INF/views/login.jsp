<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập</title>
</head>
<body>
<c:if test="${param.incorrectAccount != null}">
    <p> Tài khoản không tồn tại trong hệ thống </p>
</c:if>
<c:if test="${param.accessDenied != null}">
    <p> Bạn không có quyền truy cập trang này </p>
</c:if>
<form action="<c:url value="/j_spring_security_check"/>" method="POST">
    <input type="text" id="username" placeholder="Tên đăng nhập" name="j_username" required/>
    <input type="password" id="password" placeholder="Mật khẩu" name="j_password" required/>
    <button type="submit">
        <span class="bigger-110">Đăng nhập</span>
    </button>
</form>
</body>
</html>