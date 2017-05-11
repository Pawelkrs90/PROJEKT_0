
package app.controllers;

import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController{
 
    //public Logger logger = Logger.getLogger("IndexLogger ");
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String indexPage(Model model) {
        
      
        return "index";
    }

    
}