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
            <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">

                <div class="thumbnail">

                        <div class="caption">

                            <h3>Add User Form </h3>
                            <form:form modelAttribute="userToAdd" >
                                
                                <form:errors path="*" cssClass="alert alert-danger" element="div" />
                                
                                <fieldset>
                                   
                                    <label for="username"><spring:message text="Username" /></label>
                                    <div >
                                        <form:input id="username" path="username" type="text" class="form-control" placeholder="Username" />
                                        <form:errors path="username" cssClass="text-danger" />
                                    </div>
                                    
                                     
                                    <label  for="password"><spring:message text="Password" /></label>
                                    <div  >
                                        <form:input id="password" path="password" type="text" class="form-control" placeholder="Password" />
                                        <form:errors path="password" cssClass="text-danger" />
                                    </div>
                                    
                                    
                                    <label  for="role"><spring:message text="Role" /></label>
                                    <div  >
                                        
                                        <form:select id="role_name" class="form-control" path="role_name">           
                                            <form:option value="NONE" label="--- Select ---" />
                                            <form:options items="${RoleList}" />
                                        </form:select>
                                        <form:errors path="role_name" cssClass="text-danger" />
                                    </div>
                                       
                                    <div >
                                        <br/>
                                        <input type="submit" id="btnAdd" class="btn btn-primary" value="Submit" />
                                    </div> 
                                </fieldset>
                                
                            </form:form>

                        </div>

                </div>

            </div>
        </div>
    </section>

    </body>
    
</html>
