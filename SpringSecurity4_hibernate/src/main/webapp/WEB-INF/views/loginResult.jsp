<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LOGIN RESULT: ${result}</h1>

        <form action='<c:url value="/logout" />' method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
            <input type="submit" id="btnLogout"  value="Logout" />
        </form>
        
        <strong>${user}</strong>
        <br/><br/>
        
        <sec:authorize access="hasRole('USER')">
            <label>USER ONLY</label>
        </sec:authorize>
        
        <sec:authorize access="hasRole('ADMIN')">
            <label>ADMIN ONLY</label>
        </sec:authorize>

        <br/>
  
        <sec:authorize access="hasRole('ADMIN') and hasRole('VIP')">
            <label>ADMIN AND VIP</label>
        </sec:authorize>
        
        <security:authorize access="hasAnyRole('ADMIN', 'DEVELOPER', 'VIP')">
              Adsfdsfdsf
        </security:authorize>
    </body>
</html>