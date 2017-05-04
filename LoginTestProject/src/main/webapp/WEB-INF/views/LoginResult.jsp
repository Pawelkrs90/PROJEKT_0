<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html><html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
     
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
         <link href="<c:url value="/resources/css/body.css" />" rel="stylesheet">
        <title>Index</title>
    </head>

    <body>
      
        <section>
	    <div class="jumbotron">
			<div class="container">
                           <h1>  ${loginResult}</h3</h1>
				
			</div>
                         <c:if test="${pageContext.request.userPrincipal.name != null}">
			<a href="<c:url value="/j_spring_security_logout" />" class="btn btn-danger btn-mini pull-right">wyloguj</a>	
                         </c:if>
                </div>
	</section>
  
    </body>
    
</html>
