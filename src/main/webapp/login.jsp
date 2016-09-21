<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="expires" content="0"/>
    <title></title>
</head>
<body>
登录页面<br/>
<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
    <input type="text" name="j_username" value="ligeng"/>
    <input type="text" name="j_password" value="123456"/>
    <input type="submit" value="登陆"/>
</form>

<br/>
<input value="asdsfregnverdgmvtrhmngtr" type="button" onclick="test()">
</body>
</html>
