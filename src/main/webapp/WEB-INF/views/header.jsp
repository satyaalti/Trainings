<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}"/>
<title>Funds Transfer</title>
<script> var rooturl = "<c:url value='/' />"; </script>
<script src="<c:url value="/resources/scripts/jquery-3.2.0.min.js" />"></script>
<script src="<c:url value="/resources/scripts/myscripts.js" />"></script>
</head>
<body>

<c:set var="role" value="${sessionScope['LOGGED_IN_USER_ROLE']}" />
<div>
	<a href="<c:url value='/user/account' />" >Home</a> | 
	<c:if test="${role == 'ROLE_ADMIN'}">
	<a href="<c:url value='/admin/userlist' />" >User List</a> | 
	<a href="<c:url value="/admin/adduser" />">Add User</a> | 
	<a href="<c:url value='/admin/usertransfer' />" >Admin Funds Transfer</a> | </c:if>
	<a href="<c:url value='/user/transfer' />" >Funds Transfer</a></div>