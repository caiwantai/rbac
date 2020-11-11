<%--
  Created by IntelliJ IDEA.
  User: tagei
  Date: 2020/11/6
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="css/login.css">

</head>
<body>
<div class="container">
    <div class="logo">
    </div>
    <div class="login share">
        <div class="login-box">
            <div class="hdline">登录</div>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="text" name="username" placeholder="用户名">
                <input type="password" name="password" placeholder="密码">
                <input type="submit" value="登录" class="login-bt">
            </form>

        </div>
    </div>

</div>
</body>
</html>
