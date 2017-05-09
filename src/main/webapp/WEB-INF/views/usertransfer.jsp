<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<h1>Funds Transfer</h1>
<h2> Hi ${user.getFirstName()} Your <span id="baldiv"></span></h2>
<div><a href="<c:url value='/user/account' />">Back</a></div>
<div style="color: red">${errormsg}</div>
<form id="transferFrm" method="post">
 <input type="hidden" name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}" />
 <input type="hidden" name="total" id="total" value="" />
 <input type="hidden" name="fromaccount" id="fromaccount" value="${user.getUserid()}"  />
	<table border="1">
		<tr>
		<td colspan="2"><input type="radio" name="banktype" id="banktype"  value="SB" checked >Samebank
		<input type="radio" name="banktype" id="banktype"  value="DB"   >Diferrentbank</td></tr>
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