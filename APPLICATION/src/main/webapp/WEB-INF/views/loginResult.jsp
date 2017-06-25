<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

        <link href="<c:url value="/resources/css/body.css" />" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page='topNavBar.jsp' />
        
        
        <div class="d-flex justify-content-center">
            <div class="container-fluid">
                <h1>LOGIN RESULT: ${result}</h1>

                <form action='<c:url value="/logout" />' method="post" id="logoutForm">
                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                    <input type="submit" id="btnLogout"  value="Logout"  class="btn btn-primary" />
                </form>

                <strong style="color: #009900"><i class="fa fa-user fa-fw"></i>${user}</strong>
            </div>
        </div>
        
        <!--
        <sec :authorize access="hasRole('USER')">
            <label>USER ONLY</label>
        </sec :authorize>
        
        <sec :authorize access="hasRole('ADMIN')">
            <label>ADMIN ONLY</label>
        </sec :authorize>

        <br/>
  
        <sec :authorize access="hasRole('ADMIN') and hasRole('VIP')">
            <label>ADMIN AND VIP</label>
        </sec :authorize>
        
        <security:authorize access="hasAnyRole('ADMIN', 'DEVELOPER', 'VIP')">
              Adsfdsfdsf
        </security:authorize>
        
        -->
    </body>
</html>