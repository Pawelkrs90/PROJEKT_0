
package app.controllers;

import app.model.User;
import app.model.UserRole;
import app.service.UserDaoService;
import app.service.UserRoleDaoService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/UserRole")
public class UserRoleCrontroller {
    
    private UserRoleDaoService userRoleDaoService;
	
    @Autowired(required=true)
    @Qualifier(value="userRoleDaoService")
    public void setUserRoleService(UserRoleDaoService us){
	this.userRoleDaoService = us;
    }
    
    private UserDaoService userDaoService;
	
    @Autowired(required=true)
    @Qualifier(value="userDaoService")
    public void setUserService(UserDaoService us){
	this.userDaoService = us;
    }
    
    @RequestMapping(value = "/addUserRole", method = RequestMethod.GET)
    public String addUserGet(ModelMap model) {
        
        model.addAttribute("newUserRole", new UserRole());
        model.addAttribute("login", "");
        return "AddUserRole";
    }
    
    @RequestMapping(value = "/addUserRole", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("newUserRole") @Valid UserRole userRole, @ModelAttribute("login") @Valid String username , BindingResult result, HttpServletRequest httpRequest){
        
            if(result.hasErrors()){   //jesli Validacja zwroci problem
                return "AddUserRole";
            }
            
            if(result.getSuppressedFields().length > 0){  //sprawdzenie czy dodano tylko pola zgodne z binderem
                throw new RuntimeException("Proba wiazania niedozwolonych pol: "+ StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
            }
             
            userRole.setUser(userDaoService.findByUserName(username));
            userRoleDaoService.addUserRole(userRole);
            return "redirect:/UserRole/userRoleList";
    }
    
    @InitBinder
    public void initialiseBinder(WebDataBinder binder){
            
        //binder.setAllowedFields("username", "password");
    }
    
    @RequestMapping(value = "/userRoleList", method = RequestMethod.GET)
    public String userList(ModelMap model) {

        model.addAttribute("userRoleList", userRoleDaoService.getUserRoleList());
        return "UserRoleList";
    }
    
    
    
    /*
    
      @RequestMapping(value = "/DeleteUser/{id}", method = RequestMethod.GET)
    public String delteUser(Model model, @PathVariable("id") int userId){
        
        
        return null;
    }
    
    */
    
    
}
