<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/9/15
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<h3>尊敬的用户，欢迎您使用登录功能</h3>
<form method="post" action="${pageContext.request.contextPath}/storeLogin">
    <input type="text" name="sName" placeholder="请输入用户名"/><br/>
    <input type="password" name="sPassword" placeholder="请输入密码"/><br/>
    <input type="submit" value="登录"/><br/>
</form>
</body>
</html>
