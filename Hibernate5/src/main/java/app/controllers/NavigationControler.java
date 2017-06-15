
package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigationControler {
    
    @RequestMapping(value = {"/home" }, method = RequestMethod.GET)
    public String homePage(Model model) {
  
        return "index";
    }
    
    @RequestMapping(value = {"/adminPanel" }, method = RequestMethod.GET)
    public String adminPanel(Model model) {
  
        return "Adminpanel";
    }
    
    
}
