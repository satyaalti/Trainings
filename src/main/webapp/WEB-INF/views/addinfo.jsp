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

<title>Insert title here</title>

<script>
function validateWithdrawAmount() {
	var total_bal = parseInt(document.getElementById("totalbalance").value);
	var withdrawamount = parseInt(document.getElementById("withdrawamount").value);
	if(total_bal < withdrawamount) {
		alert("Insufficient balance. You can withdraw upto :" + total_bal + "/- only");
		return false;
	}
	
	return true;
}
</script>
</head>
<body>
<div><h1 style="text-shadow: 2px 2px #FF0000" align=center> ${user.getFirstName()}'s balance</h1></div>
<div><a href="<c:url value='/employee/list' />" >User List</a> | <a href="<c:url value='/employeebal/transfer' />" >Funds Transfer</a></div>
<form name="addbalanceform" method="post">
		<c:choose> 
	  				<c:when test="${!empty user.getUserid()}">
						<input type="hidden" name="userid" value="${user.getUserid()}">
				 	</c:when>
			</c:choose> 
	<table border="1">
	
	<tr>
	<td>Add Amount</td>
	<td> <input type="text" name="addamount" /></td>
	
	</tr>
	<tr>
	<td></td>
	<td> <input type="submit" name="addbalancebtn" value="Submit" /></td>
	</tr>
	</table>
</form>
<br/>
<form name="withbalanceform" method="post" onsubmit="javascript:return validateWithdrawAmount();">
	<c:choose> 
	  				<c:when test="${!empty user.getUserid()}">
						<input type="hidden" name="userid" value="${user.getUserid()}">
				 	</c:when>
				</c:choose> 
	<table border="1">
		<tr>
	<td>Withdraw Amount</td>
	<td> <input type="text" id="withdrawamount" name="withdrawamount" /></td>
	</tr>
	<tr>
	<td></td>
	<td> <input type="submit" name="withdrawbalancebtn" value="Submit" /></td>
	</tr>
	</table>
</form>
<form  name="listform">


<h3>UsersBalance List</h3>
<c:choose>
<c:when test="${!empty usersBalancelist}">
<table border="1" bgcolor="#FFF0F5">
  
	<tr>
		
		<th width="120">Date</th>
		<th>Type of txn</th>
		<th width="120" >Add amount</th>
		<th width="120">Withdraw Amount</th>
		
		<th>WithdrawFee</th>
		<th>TransferFee</th>
		
		<th>Total Balance</th>
		
	</tr>
	
	<c:set var="addtotal" value="${0}"/>
	<c:set var="withdrawtotal" value="${0}"/>
	<c:set var="withdrawfeetotal" value="${0}"/>
	<c:set var="transferfeetotal" value="${0}"/>
	<c:forEach items="${usersBalancelist}" var="user">
		<tr>
			<td><fmt:formatDate pattern="dd-MM-yyyy" value="${user.getDate()}" /> </td>
			<td>
			
				<c:choose> 
	  				<c:when test="${user.getTypeoftxn() == 'M'}">
						Myself
				 	</c:when>
				 	
				 	<c:when test="${user.getTypeoftxn() == 'T'}">
						Transfer from
				 	</c:when>
				 	
				 	<c:when test="${user.getTypeoftxn() == 'W'}">
						withdraw
				 	</c:when>
				 	<c:when test="${user.getTypeoftxn() == 'WT'}">
						withdraw transfer
				 	</c:when>
				</c:choose> 
				</td>
			<td style="${user.getAddamount() eq 0 ? '':'background-color: #ADFF2F'}" >
			    ${user.getAddamount()}
			</td>
			
			<td style="${user.getWithdrawamount() eq 0 ? '':'background-color: #CD5C5C'}">
			     ${user.getWithdrawamount()}
			</td>
			
				<td style="${user.getWithdrawfee() eq 0 ? '':'background-color: #FFD700'}">
					${user.getWithdrawfee()}
				</td>
				
				<td style="${user.getTransferfee() eq 0 ? '':'background-color: 	#00FFFF'}">
					${user.getTransferfee()}
				</td>
			<c:set var="addtotal" value="${addtotal + user.getAddamount()}" />
			<c:set var="withdrawtotal" value="${withdrawtotal + user.getWithdrawamount()}"/>
			<c:set var="withdrawfeetotal" value="${withdrawfeetotal + user.getWithdrawfee()}" />
			<c:set var="transferfeetotal" value="${transferfeetotal + user.getTransferfee()}" />
			
		</tr>
	</c:forEach>
		<tr>
			<td>Total</td>
			<td>
			
			</td>
			<td>${addtotal}</td>
			<td>${withdrawtotal}</td>
			<td>${withdrawfeetotal}</td>
			<td>${transferfeetotal}</td>
			<td>${bal} </td>
			
			<input type="hidden" id="totalbalance"  value="${bal}" />
		</tr>
		
</table>
	</c:when>
	<c:otherwise>
	${errorMsg}
	</c:otherwise>
	</c:choose>
	</form>
</body>
</html>
