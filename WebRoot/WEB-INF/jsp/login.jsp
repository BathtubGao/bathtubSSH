<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body>
	<form action="login.action" method="post">  
		${LOGIN_MESSAGE }
        Name:<input type="text" name="username"/><br/>  
        Password:<input type="password" name="password"/><br/>  
        <input type="submit" value="Submit"/>   
    </form>
</body>
</html>