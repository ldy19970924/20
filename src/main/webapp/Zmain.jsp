<%--
  Created by IntelliJ IDEA.
  User: 李东盈
  Date: 2020/9/19
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/administrator/administratorLogin.jsp">管理员登录</a><br>
<a href="${pageContext.request.contextPath}/user/login.jsp">用户登录</a><br>
<a href="${pageContext.request.contextPath}/user/register.jsp">用户注册</a><br>
<a href="${pageContext.request.contextPath}/stores/storeLogin.jsp">农户登录</a><br>
<a href="${pageContext.request.contextPath}/stores/storeRegister.jsp">农户注册</a><br>
</body>
</html>
