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

                            <h3>UserAdd Form </h3>
                            <form:form modelAttribute="newUser" >
                                
                                <form:errors path="*" cssClass="alert alert-danger" element="div" />
                                
                                <fieldset>
                                    <legend>Tesotwy Formularz</legend>
                                     
                                    <label for="id" ><spring:message code="form.Id" /></label>
                                    <div >
                                        <form:input id="id" path="id" type="text" class="form-control" placeholder="Identyfikator" />
                                        <form:errors path="id" cssClass="text-danger" />
                                    </div>
                                     
                                    
                                    <label for="FirstName"><spring:message code="form.FirstName" /></label>
                                    <div >
                                        <form:input id="FirstName" path="firstName" type="text" class="form-control" placeholder="Imię" />
                                        <form:errors path="firstName" cssClass="text-danger" />
                                    </div>
                                    
                                     
                                    <label  for="LastName"><spring:message code="form.LastName" /></label>
                                    <div  >
                                        <form:input id="LastName" path="lastName" type="text" class="form-control" placeholder="Nazwisko" />
                                        <form:errors path="lastName" cssClass="text-danger" />
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
