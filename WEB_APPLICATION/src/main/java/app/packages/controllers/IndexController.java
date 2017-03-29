/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.packages.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    
    Logger log = Logger.getLogger(this.getClass().getName());
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        
        
        log.info("STARRRRRTEED");
        return "index";
    }
    
    
}
