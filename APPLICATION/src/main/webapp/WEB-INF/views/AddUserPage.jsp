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
        <title>Index</title>
    </head>

        <jsp:include page='topNavBar.jsp' />
          
        <section >
 
                <div class="d-flex justify-content-center">
                    <div class="container-fluid">
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
                                        
                                        <form:select id="role_name" class="form-control" path="role_name" multiple="true">           
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
            </section>
    
    </body>
    
</html>
