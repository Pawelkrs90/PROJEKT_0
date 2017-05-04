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

        <title>Index</title>
    </head>

    <body>

        
    <section class="container">
        <div class="row">
            <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">

                <div class="thumbnail">
                        <div class="caption">

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
                                       
                                    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                                    
                                    <div >
                                        <br/>
                                        
                                        <input type="submit" id="btnAdd" class="btn btn-primary" value="Submit" />
                                    </div> 
                                </fieldset>
                                
                            </form>

                        </div>
                </div>

            </div>
        </div>
    </section>

    </body>
    
</html>