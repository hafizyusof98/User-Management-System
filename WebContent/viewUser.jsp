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
	Id: <c:out value = "${user.id }"/><br/>
	Name: <c:out value = "${user.name }"/><br/>
	Email: <c:out value = "${user.email }"/><br/>
	Country: <c:out value = "${user.country }"/><br/>
	
	<a href = "registerUserController?action=updateR&id=${user.id}">Update Info</a><br/>
	<a href = "registerUserController?action=deleteR&id=${user.id}">Delete Account</a><br/>
	<a href = "registerUser.jsp">Back to Homepage</a>
</body>
</html>