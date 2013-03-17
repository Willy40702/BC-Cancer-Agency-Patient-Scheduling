<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Notification Change Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<h3>Username : ${username}</h3>	
	<h3>This is where you change your notification settings.</h3>
	
	<form action="setnotification" method="post">
	I want to receive notifications by email: <input type="checkbox" name="notification"> <br>
	<input type="submit" value="Submit">
	</form>
	
	<form action="setemail" method="post">
	Update email: <input type="text" name="email"> <br>
	<input type="submit" value="Submit">
	</form>
	
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
</body>
</html>