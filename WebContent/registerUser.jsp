<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <% if(session.getAttribute("userID") == null){
	   response.sendRedirect("login.jsp");
	   }
   %>
	   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>test</h1>
	<h1>User Registration Form</h1>
	<form action = "registerUserController" method = "post">
		Name: <input type = "text" name = "name"/><br/>
		Email: <input type = "email" name = "email"/><br/>
		Country: <select name = "country">
				 	<option value = "Malaysia">Malaysia</option>
				 	<option value = "Singapore">Singapore</option>
				 	<option value = "Thailand">Thailand</option>
				 </select>
	    <br/><button type = "submit" name = "action" value = "register">Register</button><br/>
	</form>
	<br/><h1>Search User</h1>
	<form action = "viewUserController">
		ID: <input type = "text" name = "id"/><br/>
		<button type = "submit" name = "action" value = viewUser>Search User</button>
	</form><br/>
	<form action = "viewListUserController">
		<button type = "submit">View List User</button>
	</form>
	<h1>test</h1><br/><br/>
	<a href="registerUserController?action=logout"><h3>Logout</h3></a>

</body>
</html>