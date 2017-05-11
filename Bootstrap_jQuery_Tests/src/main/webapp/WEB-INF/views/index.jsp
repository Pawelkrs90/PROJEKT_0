<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>


        <title>JSP Page</title>
    </head>
    <body>
    

        <nav class="navbar fixed-top navbar-toggleable-xl navbar-inverse bg-inverse">
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#nav-content" aria-controls="nav-content" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Brand -->
            <a class="navbar-brand" href="#">Logo</a>

            <!-- Links -->
            <div class="collapse navbar-collapse" id="nav-content">   
            <ul class="navbar-nav">
            <li class="nav-item">
            <a class="nav-link" href="#">Link 1</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="#">Link 2</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="#">Link 3</a>
            </li>
            </ul>
        </nav>
        
        <br/><br/><br/><br/><br/>

        <h1>Buttons</h1>
        <a class="btn btn-primary" href="#" role="button">A Button</a>
        <button class="btn btn-primary" type="submit">Button</button>
        <input class="btn btn-primary" type="submit" value="Button Submit">
        <input class="btn btn-primary" type="button" value="Button tak input">
        
        <br/>
        <br/>
        
        <button type="button" class="btn btn-outline-primary">Primary</button>
        <button type="button" class="btn btn-outline-secondary">Secondary</button>
        <button type="button" class="btn btn-outline-info">Info</button>
        <button type="button" class="btn btn-outline-success">Success</button>
        <button type="button" class="btn btn-outline-warning">Warning</button>
        <button type="button" class="btn btn-outline-danger">Danger</button>
        
         
        <br/>
        <br/>       
        
        
        <button type="button" class="btn btn-success btn-sm">Small</button>
        <button type="button" class="btn btn-success">Default</button>
        <button type="button" class="btn btn-success btn-lg">Large</button>

        
        <br/>
        <br/>
              
        <button class="btn btn-block" type="submit">Button block</button>
        
        
        <br/>
        <br/>
             
        <p><a href="#" class="btn btn-primary btn-lg disabled" role="button">The 'a' Element</a></p>
        <p><button type="button" class="btn btn-lg btn-primary" disabled="disabled">The 'button' Element</button></p>
        <p><input type="button" class="btn btn-lg btn-primary" disabled="disabled" value="The 'input' Element"></p>
        
        <br/>
        <br/>
             
        
        <div class="btn-group" data-toggle="buttons">
        <label class="btn btn-primary active">
        <input type="checkbox" checked autocomplete="off"> Boots
        </label>
        <label class="btn btn-primary">
        <input type="checkbox" autocomplete="off"> Shoes
        </label>
        <label class="btn btn-primary">
        <input type="checkbox" autocomplete="off"> Feet
        </label>
        </div>
        
        
        <br/>
        <br/>
             
        <div class="btn-group" data-toggle="buttons">
        <label class="btn btn-primary">
        <input type="radio" name="options" id="boots" autocomplete="off" checked> Boots
        </label>
        <label class="btn btn-primary">
        <input type="radio" name="options" id="shoes" autocomplete="off"> Shoes
        </label>
        <label class="btn btn-primary">
        <input type="radio" name="options" id="feet" autocomplete="off"> Feet
        </label>
        </div>
        
        <br/>
        <br/>
             
        <form action="#">
            <div class="container-flex">
                <div class="row">
                    <div class="form-group">

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Username">
                        </div>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Password">
                        </div>

                        <div class="offset-xs-3 col-xs-9">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </div>

                </div>
            </div>
        </form>
        
        <br/>
        <br/>
   
        
        <div class="d-flex justify-content-center">
            <form action="/html/tags/html_form_tag_action.cfm">
                <div class="container-fluid">
                    <div class="form-group row">

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="first_name" name="Username" placeholder="Username">
                        </div>
                    </div>

                    <div class="form-group row">

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="last_name" name="Password" placeholder="Password">
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="offset-xs-3 col-xs-9">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </div>

                </div>
            </form>
        </div>
        <div class="d-flex justify-content-center"><strong>...</strong></div>
        
        <br/>
        <br/>
             
                
        
        <br/>
        <br/>
             
                
        
        <br/>
        <br/>
             
                
        
        <br/>
        <br/>
             
        
        <br/><br/><br/><br/><br/>  <br/><br/><br/><br/><br/>  <br/><br/><br/><br/><br/>  <br/><br/><br/><br/><br/>
        
        
        
        
    <div class="d-flex justify-content-center">CENTER</div>
<div class="d-flex flex-row">
  <div class="p-2">Flex item 1</div>
  <div class="p-2">Flex item 2</div>
  <div class="p-2">Flex item 3</div>
</div>
<div class="d-flex flex-row-reverse">
  <div class="p-2">Flex item 1</div>
  <div class="p-2">Flex item 2</div>
  <div class="p-2">Flex item 3</div>
</div>
    
    <div class="d-flex flex-column">
  <div class="p-2">Flex item 1</div>
  <div class="p-2">Flex item 2</div>
  <div class="p-2">Flex item 3</div>
</div>
<div class="d-flex flex-column-reverse">
  <div class="p-2">Flex item 1</div>
  <div class="p-2">Flex item 2</div>
  <div class="p-2">Flex item 3</div>
</div>
    
    
    <div class="row">
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
</div>
<div class="row">
  <div class="col-md-8">.col-md-8</div>
  <div class="col-md-4">.col-md-4</div>
</div>
<div class="row">
  <div class="col-md-4">.col-md-4</div>
  <div class="col-md-4">.col-md-4</div>
  <div class="col-md-4">.col-md-4</div>
</div>
<div class="row">
  <div class="col-md-6">.col-md-6</div>
  <div class="col-md-6">.col-md-6</div>
</div>
    </body>
</html>