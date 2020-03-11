<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<h1>User Info</h1>
	<form action="registerUserController" method = "post">
		ID: <input type = "text" name = "id" value = <c:out value = "${user.id}"></c:out> readonly/><br/>
		Name: <input type = "text" name = "name" value = <c:out value = "${user.name}"></c:out>/><br/>
		Email:<input type = "email" name = "email" value = <c:out value = "${user.email}"></c:out>/><br/>
		Country: <select name = "country">
				 	<option value = "Malaysia">Malaysia</option>
				 	<option value = "Singapore">Singapore</option>
				 	<option value = "Thailand">Thailand</option>
				 </select>
	    <br/><button type = "submit" name = "action" value = "updateW">Update</button><br/>
	</form>	
</body>
</html>