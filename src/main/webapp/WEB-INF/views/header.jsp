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
<form name="logoutFrm" method="post">  
   <input type="hidden" name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}" />  
</form>
<c:set var="role" value="${sessionScope['LOGGED_IN_USER_ROLE']}" />
<c:set var="loggedInUser" value="${sessionScope['LOGGED_IN_USER']}" />
<div>
	Hi ${loggedInUser.getFirstName()}!!
	<a href="<c:url value='/user/account' />" >Home</a> | 
	<c:if test="${role == 'ROLE_ADMIN'}">
	<a href="<c:url value='/admin/userlist' />" >User List</a> | 
	<a href="<c:url value="/admin/adduser" />">Add User</a> | 
	<a href="<c:url value='/admin/usertransfer' />" >Admin Funds Transfer</a> | </c:if>
	<a href="<c:url value='/user/transfer' />" >Funds Transfer</a> |
	 <a href="#" id="logout" >Logout</a></div> 