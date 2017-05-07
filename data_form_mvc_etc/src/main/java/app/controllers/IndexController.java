
package app.controllers;

import app.model.UserRoleForm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.jboss.logging.Logger;
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
 
    public Logger logger = Logger.getLogger("IndexLogger ");
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String homePage(Model model) {
        
        model.addAttribute("IndexInfo", "Application Start Page");
        return "index";
    }
       
    @RequestMapping(value = {"/ParamReqest"}, method = RequestMethod.GET)  
    public String hello(@RequestParam(value = "userName", defaultValue = "default") String userName, 
                        @RequestParam(value = "userRole", defaultValue = "default") String userRole, Model model) {
    
        logger.info("userName: "+userName);
        logger.info("userRole: "+userRole);      
              
        return "index";
    } 
    
    @RequestMapping(value = {"/formRequest"}, method = RequestMethod.GET)
    public String formGet(Model model){
        
        List<String> roleList = new ArrayList<>(Arrays.asList("USER_ROLE", "ADMIN_ROLE", "VIP_ROLE"));
        
        model.addAttribute("RoleList", roleList);
        model.addAttribute("userRoleForm", new UserRoleForm());
        return "formRequest";
    }
    
    @RequestMapping(value = "/formRequest", method = RequestMethod.POST)
    public String formPost(Model model, @ModelAttribute("userRoleForm") @Valid UserRoleForm userRoleForm, BindingResult result, HttpServletRequest httpRequest){
        
            if(result.hasErrors()){   //jesli Validacja zwroci problem
                return "formRequest";
            }
            
            if(result.getSuppressedFields().length > 0){  //sprawdzenie czy dodano tylko pola zgodne z binderem
                throw new RuntimeException("Proba wiazania niedozwolonych pol: "+ StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
            }
            
            logger.info("FORM INFO: "+userRoleForm.getUserName()+"  "+userRoleForm.getUserRole());
            return "formRequest";
           // userDaoService.addUser(user);
           
            //return "redirect:/User/userList";
    }
    
    @InitBinder
    public void initialiseBinder(WebDataBinder binder){
            
        binder.setAllowedFields("userName", "userRole");
    }
}