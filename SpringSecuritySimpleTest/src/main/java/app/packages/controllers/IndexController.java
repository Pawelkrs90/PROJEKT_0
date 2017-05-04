package app.packages.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){

        return "index";
    }
    
    @RequestMapping(value = "/loggin_checkout", method = RequestMethod.GET)
    public String logIntercept(){

        return "loggin_checkout";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)   
    public String errorPage(){

        return "loggin_checkout";
    }      
    
    
}
