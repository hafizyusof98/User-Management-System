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
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Country</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items = "${userL}" var = "userL">
				<tr>
					<td><a href="viewUserController?action=viewUser&id=${userL.id }"><c:out value="${userL.id}"></c:out></a></td>
					<td><c:out value="${userL.name}"></c:out></td>
					<td><c:out value="${userL.email}"></c:out></td>
					<td><c:out value="${userL.country}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/><a href = "registerUser.jsp">Back to homepage</a>

</body>
</html>