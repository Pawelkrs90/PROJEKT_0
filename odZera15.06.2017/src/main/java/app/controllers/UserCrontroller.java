
package app.controllers;

import app.model.User;
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
@RequestMapping(value = "/User")
public class UserCrontroller {
    
    private UserDaoService userDaoService;
	
    @Autowired(required=true)
    @Qualifier(value="userDaoService")
    public void setUserService(UserDaoService us){
	this.userDaoService = us;
    }
    
    private UserRoleDaoService userRoleDaoService;
	
    @Autowired(required=true)
    @Qualifier(value="userRoleDaoService")
    public void setUserRoleService(UserRoleDaoService us){
	this.userRoleDaoService = us;
    }
    
    /*@RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUserGet(ModelMap model) {
        
        model.addAttribute("newUser", new User());
        return "AddUser";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("newUser") @Valid User user, BindingResult result, HttpServletRequest httpRequest){
        
            if(result.hasErrors()){   //jesli Validacja zwroci problem
                return "AddUser";
            }
            
            if(result.getSuppressedFields().length > 0){  //sprawdzenie czy dodano tylko pola zgodne z binderem
                throw new RuntimeException("Proba wiazania niedozwolonych pol: "+ StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
            }
                  
            userDaoService.addUser(user);
            return "redirect:/User/userList";
    }
    
    @InitBinder
    public void initialiseBinder(WebDataBinder binder){
            
        binder.setAllowedFields("username", "password");
    }
    
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String userList(ModelMap model) {

        model.addAttribute("userList", userDaoService.getUserList());
      //  model.addAttribute("UserRoleForm", new UserRoleForm());
        return "UserList";
    }
    
  
    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    public String addUserRolePOST(@ModelAttribute("UserRoleForm")UserRoleForm userRoleForm){

      //  userRoleDaoService.addUserRole(new UserRole(userDaoService.getUserById(userRoleForm.getUserId()), userRoleForm.getUserRole()));

        return "UserRoleList";
    }
    
    */
   // @RequestMapping(value = "/addUserRole/{id}/{role}", method = RequestMethod.GET)
 /*   public String addUserRole( @PathVariable("id") int userId, @PathVariable("role") String userRole){
        
        userRoleDaoService.addUserRole(new UserRole(userDaoService.getUserById(userId),userRole));
        
        
        return null;
    }
    */
    
    
    /*
    
      @RequestMapping(value = "/DeleteUser/{id}", method = RequestMethod.GET)
    public String delteUser(Model model, @PathVariable("id") int userId){
        
        
        return null;
    }
    
    */
    
    
}
