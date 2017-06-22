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

    <section class="container">
        <div class="row">
            <c:forEach items="${userList}" var="user">
                <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">

                    <div class="thumbnail">

                        <div class="caption">

                            <h3>${user.id}</h3>
                            <p>${user.username}</p>
                            <p>${user.password}</p>
                             <c:forEach items="${user.userRole}" var="role">
                                 <p>${role.name}</p>
                             </c:forEach>
                       

                        </div>

                    </div>

                </div>
            </c:forEach>
        </div>
    </section>

    </body>
    
</html>
