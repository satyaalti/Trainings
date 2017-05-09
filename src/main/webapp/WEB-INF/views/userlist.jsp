<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ include file="header.jsp" %>
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
			<td><a href="<c:url value='/admin/edituser/${user.getUserid()}' />" >edit</a></td>
			<td><a href="<c:url value='/admin/removeuser/${user.getUserid()}' />" >Remove</a></td>
			<td><a href="<c:url value='/admin/userbalanceinfo/${user.getUserid()}' />" >Account</a></td>
			
	</tr>
	</c:forEach>
</table>
	</c:when>
	<c:otherwise>
	${errorMsg}
    </c:otherwise>
	</c:choose>
	<%@ include file="footer.jsp" %>
	