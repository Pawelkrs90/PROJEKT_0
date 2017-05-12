<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html><html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link href="<c:url value="/resources/css/body.css" />" rel="stylesheet">
        <title>Index</title>
    </head>

    <body>
    <jsp:include page='TopNavBar.jsp' />
        
    
       <section class="container">
        <div class="row">
            <div class="col-sm-6 col-md-3 centered" style="padding-bottom: 15px">

                <div class="thumbnail">

                    <div class="caption">
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
                                
                                <h3><spring:message text="Please sign in" /></h3>
                                <fieldset>
                              
                               
                                    <div >
                                        <input type="text" class="form-control" id="username" path="username" name="securityUsername" placeholder="Username" required="true" />
                                       
                                    </div>
                                    
                                 
                                    <div  >
                                         <input type="password" class="form-control" id="password" path="password" name="securityPassword" placeholder="Password" required="true" />
                                        
                                    </div>
                                       
                                    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                                    
                                    <div style="padding-top: 0.5vh">

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
