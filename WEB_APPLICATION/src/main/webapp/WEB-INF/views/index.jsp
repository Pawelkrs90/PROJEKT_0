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

         <!--   <nav class="navbar navbar-default navbar-fixed-top" >
                <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">WebSiteName</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<spring :url value='/' />" >Home</a></li>
                    <li><a href="<spring :url value='/Users' /> " > Users</a></li>
                    <li><a href="#">Page 2</a></li>
                    <li><a href="#">Page 3</a></li>
                </ul>
                <button class="btn btn-danger navbar-btn">Button</button>
                
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </ul>
                </div>
            </nav>
         
         -->
         <div style="background-color: #267871; width: 100%; text-align: center; padding-bottom: 5px; padding-top: 2px">
             
             <h3 style="color: #091002">MAIN PAGE</h3>
             
         </div>
         <div style="background-color: #267871; width: 100%; text-align: center; margin-bottom: 4px; margin-top: 4px">
             
             <a href="#">Login</a>
             <a href="#">Registration</a>
             
         </div>
           <div style="background-color: #267871; width: 100%; text-align: center;">
             
                <a href="#">Main</a>
                <a href="#">Profile</a>
                <a href="#">TripCreator</a>
                <a href="#">PathCreator</a>
                <a href="#">MapEvents</a>
                <a href="#">AdminPanel</a>
              
             
         </div>

</html>
