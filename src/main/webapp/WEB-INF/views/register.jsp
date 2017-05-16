<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<center>  
  <br /> <br /> <br />  
  <div style="border: 1px solid black; width: 300px; padding-top: 10px;">
<h1>Register</h1>
	<form name="UserregisterForm" id="UserregisterForm" action="<c:url value="/register"/>" method="post">
	 <input type="hidden" name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}" />  
			<table>
				<tr>
					<td>First Name</td>
					<td> <input type="text" name="firstName" id="firstName" value="" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td> <input type="text" name="lastName" id="lastName" value="" /></td>
				</tr>
				<tr>
					<td>Username</td>
					<td> <input type="text" name="username" id="username" value="" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td> <input type="password" name="password" id="password" value="" /></td>
				</tr>
				<tr>
					<td>Confirm Password</td>
					<td> <input type="password" name="cpassword" id="cpassword" value="" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td> <input type="email" name="email" id="email" value="" /> </td>
				</tr>
				<tr>
				<td>Bank</td>
			<td>
				<select id="bankid" name="bankid">
						<option value="0">Select Bank</option>
		            <c:forEach var="bank" items="${bankslist}">
		                <option value="${bank.getBankid()}" >${bank.getBankname()}</option>
		            </c:forEach>
		        </select>
			</td></tr>
				<tr>
					<td> <input type="button" value="Cancel" name="cancel" onclick="window.location='<c:url value='/login' />'" /></td>
					<td> <input type="submit" value="Submit" name="submit" /></td>
				</tr>
		</table>
	</form>
	</div></center>
</body>
</html>