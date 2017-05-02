<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Funds Transfer</title>
<script> var rooturl = "<c:url value='/' />"; </script>
<script src="<c:url value="/resources/scripts/jquery-3.2.0.min.js" />"></script>
<script src="<c:url value="/resources/scripts/myscripts.js" />"></script>
</head>
<body>
<h1>Funds Transfer</h1>
<div><a href="<c:url value='/employee/list' />" >User List</a></div>
<div style="color: red">${errormsg}</div>
<form id="transferFrm" method="post">
	<table border=1>
		<tr>
		<td></td>
		<td><input type="radio" name="banktype" id="banktype"  value="SB" checked >Samebank
		<input type="radio" name="banktype" id="banktype"  value="DB"   >Diferrentbank</td></tr>
		<tr>
			<td>From</td>
			<td>
				<select id="fromaccount" name="fromaccount">
			            <c:forEach var="user" items="${userlist}">
			                <option value="${user.getUserid()}">${user.getFirstName()}</option>
			            </c:forEach>
			        </select>   <span id="baldiv"></span>
			        <input type="hidden" id="total" />
			</td>
		</tr>
		<tr>
			<td>To</td>
			<td id="toaccounttd">
				
			</td>
		</tr>
		<tr>
			<td>Amount</td>
			<td> <input type="number" id="transferamount" name="transferamount" /></td>
		</tr>
		<tr>
			<td></td>
			
			<td> <input type="submit" name="transfer" value="Submit" /></td>
		</tr>
	</table>
</form>
<%@ include file="footer.jsp" %>