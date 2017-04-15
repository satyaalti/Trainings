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
	<form action="<c:url value="/employee/add"/>" method="post">
	
		 	<c:choose> 
  				<c:when test="${!empty user.getUserid()}">
					<input type="hidden" name="userid" value="${user.getUserid()}">
			 	</c:when>
			</c:choose> 
			<table>
				<tr>
					<td>First Name</td>
					<td> <input type="text" name="firstName" value="${user.getFirstName()}" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td> <input type="text" name="lastName" value="${user.getLastName()}" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td> <input type="email" name="email" value="${user.getEmail() }" /> </td>
				</tr>
				<tr>
				<td>Bank</td>
			<td>
				<select id="bankid" name="bankid">
						<option value="0">Select Bank</option>
		            <c:forEach var="bank" items="${bankslist}">
		                <option value="${bank.getBankid()}" ${user.getBankid() == bank.getBankid() ? "selected" : ""}>${bank.getBankname()}</option>
		            </c:forEach>
		        </select>
			</td></tr>
				<tr>
					<td> <input type="button" name="cancel" value="Cancel" onclick="window.location.href='<c:url value="/employee/list"/>'" /></td>
					<td> <input type="submit" value="${!empty user.getUserid() ? 'Update' : 'Submit' }" name="submit" /></td>
				</tr>
		</table>
	</form>
</body>
</html>