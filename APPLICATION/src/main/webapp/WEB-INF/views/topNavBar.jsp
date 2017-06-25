<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
   
    </head>
    <body>
       
        <nav class="navbar navbar-toggleable-md navbar-inverse  " style="background-color: #333333; margin-bottom: 10vh">
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href='<c:url value="/home" />'>Home</a>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                  <li class="nav-item"> <!-- <li class="nav-item active"> -->
                    <a class="nav-link" href="#">Profil</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Messages</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link " href="#">Trip</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link " href="#">Path</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link " href="#">World Map</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link " href='<c:url value="/adminPanel" />'>Admin Panel</a>
                  </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <ul class="navbar-nav mr-auto">
                        <sec:authorize access="!isAuthenticated()">
                            <li class="nav-item"> <!-- <li class="nav-item active"> -->
                                <a class="nav-link" href='<c:url value="/loginPage" />'><strong>Sign in</strong></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href='<c:url value="/User/AddUser" />'><strong>Sign up</strong></a>
                            </li>
                        </sec:authorize>
                        
                        <sec:authorize access="isAuthenticated()">
                            <li class="nav-item">
                                <a class="nav-link" href='#'><strong style="color: #009900">User: <sec:authentication property="principal.username" /> </strong></a>
            
                            </li>
                            <li class="nav-item">
                                <form action='<c:url value="/logout" />' method="post" id="logoutForm">
                                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                    <input type="submit" id="btnLogout"  value="Logout"  class="btn btn-success btn-sm" />
                                </form>
                            </li>
                        </sec:authorize>
                    </ul>
              
                </form>
            </div>
        </nav>
        
    </body>
</html>
