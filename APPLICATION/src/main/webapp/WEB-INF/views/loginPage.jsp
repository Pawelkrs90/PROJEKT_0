<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html><html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
     
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

        <title>Index</title>
        <link href="<c:url value="/resources/css/body.css" />" rel="stylesheet">
        
    </head>
    <body>
        <jsp:include page='topNavBar.jsp' />
          
            <section>
                <div class="d-flex justify-content-center">
                    <div class="container-fluid">
                        <h3>Login Form </h3>

                        <form action='<c:url value="/login" />' method="post" > 

                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>

                            <fieldset>

                                <label for="username"><spring:message text="Username" code="" /></label>
                                <div >
                                        <input type="text" class="form-control" id="username" path="username" name="securityUsername" placeholder="Enter Username" required="true" />
                                </div>

                                <label for="password"><spring:message text="Password" code="" /></label>
                                <div  >
                                    <input type="password" class="form-control" id="password" path="password" name="securityPassword" placeholder="Enter Password" required="true" />
                                </div>

                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                
                                <div >
                                    <br/>
                                    <input type="submit" id="btnAdd" class="btn btn-primary" value="Submit" />
                                </div> 
                            </fieldset>

                        </form>

                    </div>
                </div>
            </section>
    
    </body>
    
</html>