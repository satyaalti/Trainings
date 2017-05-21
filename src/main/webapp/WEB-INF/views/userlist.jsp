<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="header.jsp" %>
<c:out value="${sessionScope.total}" />	
<h3>Users List</h3>
<c:choose> 
	<c:when test="${enable == true}">
		 Active | <a href="<c:url value='/admin/userlist/inactive'	/>" > Inactive </a>
 	</c:when>
 	<c:otherwise>
 		<a href="<c:url value='/admin/userlist/active'	/>" > Active </a> | Inactive
 	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${!empty userlist}">
		<form name="UserListForm" id="UserListForm" method="post">
		<input type="hidden" name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="selectedid" id="selectedid" value="" />
		<input type="hidden" name="enable" id="enable" value="${enable}" />
		<table border="1">
			<tr>
				<th width="80">User ID</th>
				<th width="120">First Name</th>
				<th width="120">Last name</th>
				<th width="60">email id</th>
				<th>bank</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${userlist}" var="user">
				<tr>
					<td>${user.getUserid()}</td>
					<td>${user.getFirstName()}</td>
					<td>${user.getLastName()}</td>
					<td>${user.getEmail() }</td>
					<td>${user.getBank().getBankname()}</td>
					<td>
						<a href="<c:url value='/admin/edituser/${user.getUserid()}' />" >Edit</a> |
						<%-- <c:if test="${loggedInUser.getUserid() != user.getUserid()}"><a href="<c:url value='/admin/removeuser/${user.getUserid()}' />" >Remove</a> | </c:if>  --%>
						<a href="<c:url value='/admin/userbalanceinfo/${user.getUserid()}' />" >Account</a>
						<c:if test="${loggedInUser.getUserid() != user.getUserid()}"> |
						<a href="#" name="changestatus" id="${user.getUserid()}"  >
					 <c:choose> 
						<c:when test="${enable == true}">
							 Make Inactive
					 	</c:when>
					 	<c:otherwise>
					 		Make Active
					 	</c:otherwise>
					</c:choose></a></c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	</c:when>
	<c:otherwise>
		<br/> ${errorMsg}
	</c:otherwise>
</c:choose>
<%@ include file="footer.jsp" %>
	