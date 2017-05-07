<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>INDEX</h1>
        
        <form:form modelAttribute="userRoleForm" method="POST" >
            
                <fieldset>                                  
                    <label for="username"><spring:message text="userName" /></label>
                    <div>
                        <form:input id="username" path="userName" type="text" class="form-control" placeholder="UserName" />
                    </div>
                    
                    <label for="userrole"><spring:message text="userRole" /></label>
                    <div>
                        <form:input id="userrole" path="userRole" type="text" class="form-control" placeholder="UserRole" />
                    </div>
                    
                    <label for="userrole"><spring:message text="userRoleList" /></label>
                    <br/>
                    <form:select path="userRole" items="${RoleList}" />
                    <br/>
                    <form:select path="userRole">           
                        <form:option value="NONE" label="--- Select ---" />
                        <form:options items="${RoleList}" />
                    </form:select>
                    <br/>     
                    <!-- jesli mapa zamaist listy 
                      < form :select path="departmentId">         
                        < form :options items="$ {departments}" var="department" itemValue="value" itemLabel="key"/>
                      < / form: select> 
                    -->
                    
                    <form:select path="userRole" items="${RoleList}" multiple="true" />
                    <br/>
                    <form:radiobuttons path="userRole" items="${RoleList}" />
                    <br/>
                    <form:checkboxes path="userRole" items="${RoleList}" />
                    
                    <div >
                        <br/>
                        <input type="submit" id="btnAdd" class="btn btn-primary" value="Submit" />
                    </div> 
                </fieldset>              
        </form:form>
  
          
    </body>
</html>