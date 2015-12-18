<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/logout.action" method="post">  
	${LOGIN_MESSAGE}
 	<input type="submit" value="退出"/>
 </form> 
</body>
</html>