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
    <jsp:include page='topNavBar.jsp' />
        
    

            <div class="d-flex justify-content-center" >
                
                <form action='<c:url value="/login" />' method="post" style="border: solid 1px #666666; border-radius: 8px; 
                                                                                 padding-left: 4vw; padding-right: 4vw;
                                                                                 padding-bottom: 4vh; padding-top: 3vh;
                                                                                 background: url('<c:url value="/resources/images/textures/blackBg/black10.png" />')"> 
  
                    <h2 style="color: #333333"><spring:message text="Please sign in" /></h2>
                                
                                
                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger">
                                        <p>Invalid username or password.</p>
                                    </div>
                                </c:if>
                                <c:if test="${param.logout != null}">
                                    <div class="alert alert-success">
                                        <p>You have been logged out successfully.</p>
                                    </div>
                                </c:if>
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

    
        
    </body>
    
</html>
