<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="<c:url value="/employee/add"/>">add user</a>
<a href="<c:url value="/employeebal/transfer"/>">transfer</a>
<c:out value="${sessionScope.total}" />	

<h3>Users List</h3>
<c:choose>
<c:when test="${!empty userlist}">
<table border="1">
	<tr>
		<th width="80">User ID</th>
		<th width="120">First Name</th>
		<th width="120">Last name</th>
		<th width="60">email id</th>
		<th>bank</th>
	</tr>
	<c:forEach items="${userlist}" var="user">
		<tr>
			<td>${user.getUserid()}</td>
			<td>${user.getFirstName()}</td>
			<td>${user.getLastName()}</td>
			<td>${user.getEmail() }</td>
			<td>${user.getBank().getBankname()}</td>
			<td><a href="<c:url value='/employee/edit/${user.getUserid()}' />" >edit</a></td>
			<td><a href="<c:url value='/employee/remove/${user.getUserid()}' />" >Remove</a></td>
			<td><a href="<c:url value='/employeebal/balanceinfo/${user.getUserid()}' />" >Account</a></td>
			
	</tr>
	</c:forEach>
</table>
	</c:when>
	<c:otherwise>
	${errorMsg}
    </c:otherwise>
	</c:choose>
	<%@ include file="footer.jsp" %>
	