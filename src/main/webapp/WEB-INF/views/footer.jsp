<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:url var="logoutUrl" value="/j_spring_security_logout" />  
  <form action="${logoutUrl}" method="post">  
   <input type="submit" value="Log out" /> <input type="hidden"  
    name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}" />  
  </form>
</body>
</html>