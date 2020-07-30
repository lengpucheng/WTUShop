<%--
  Created by IntelliJ IDEA.
  User: lpc
  Date: 2020/7/29
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/doRegister" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    真实姓名：<input type="text" name="realname"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
