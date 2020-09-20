<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/9/15
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>电话登录</title>
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
</head>
<body>
<h3>尊敬的用户，欢迎使用手机登录</h3>
<form action="<c:url value='StoreLoginByPhoneServlet'/>">
    <p>
        手机号：<input type="text" name="phone" id="phone"/>
        <input type="button" value="获取验证码" id="code"/>
    </p>
    <p>验证码:<input type="text" name="code"/></p>
    <p><input type="submit" value="登录"/></p>
</form>
</body>
<script type="text/javascript">
    let time = 60;
    $("#code").click(
        function (){
            let tm = setInterval(function (){
                time--;
                if(time == 0){
                    $("#code").removeAttr("disabled");
                    $("#code").val("获取验证码");
                    clearInterval(tm);
                }else{
                    $("#code").attr("disabled","true");
                    $("#code").val(time+"以后重新发送");
                }

            },1000);
            $.ajax({
                "type":"GET",
                "url":"/CodeServlet",
                "data":{"phone":$("#phone").val()}
        });

        }

    );
</script>
</html>
