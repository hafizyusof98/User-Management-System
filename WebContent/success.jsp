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
	<h1>Successfully added</h1>
	<form action = "registerUser.jsp">
		<button type = "submit">Back to Homepage</button>
	</form>
</body>
</html>