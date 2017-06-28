
package app.controllers;

import app.model.User;
import app.model.UserRole;
import app.model.forms.UserForm;
import app.service.UserDaoService;
import app.service.UserRoleDaoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/User")
public class UserCrontroller {
    
    private Logger logger = Logger.getLogger(getClass().getName());
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
    
    @RequestMapping(value = "/AddUser", method = RequestMethod.GET)
    public String addUser(ModelMap model) {
        
        List<String> roleList = new ArrayList<>(Arrays.asList("ROLE_ADMIN", "ROLE_USER", "ROLE_VIP", "ROLE_GUEST"));
        
        model.addAttribute("RoleList", roleList);
        model.addAttribute("userToAdd", new UserForm());
        return "AddUserPage";
    }
    

    @RequestMapping(value = "/AddUser", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("userToAdd") @Valid UserForm userForm, BindingResult result, HttpServletRequest httpRequest){
        
            if(result.hasErrors()){   //jesli Validacja zwroci problem
                logger.info("Form have errors, refreshing page...");
                return "AddUserPage";
            }
            
            if(result.getSuppressedFields().length > 0){  //sprawdzenie czy dodano tylko pola zgodne z binderem
                throw new RuntimeException("Proba wiazania niedozwolonych pol: "+ StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
            }
                  
            User user = new User(userForm.getUsername(), userForm.getPassword());
            userDaoService.saveUser(user);
            
            UserRole userRole = new UserRole(user, userForm.getRole_name());
            userRoleDaoService.saveUserRole(userRole);
   
            user.addRole(userRole);

            return "redirect:/User/UserListPage";
    }
    
    @InitBinder
    public void initialiseBinder(WebDataBinder binder){
            
        binder.setAllowedFields("username", "password", "role_name");
    }
    
    @RequestMapping(value = "/UserListPage", method = RequestMethod.GET)
    public String userList(ModelMap model) {

        model.addAttribute("userList", userDaoService.getUserList());
        return "UserListPage";
    }
    
    //User/UserDetailsPage?userId=2
    @RequestMapping(value = "/UserDetailsPage", method = RequestMethod.GET)
    public String userDetailsByRequestParam(ModelMap model, @RequestParam(value = "userId", defaultValue = "1") int userId) {

        model.addAttribute("user", userDaoService.getUserById(userId));        
        return "UserDetailsPage";
    }
    
    ///User/UserDetailsPage/2
    @RequestMapping(value = "/UserDetailsPage/{userId}", method = RequestMethod.GET)
    public String userDetailsByPathVariable(ModelMap model, @PathVariable("userId") int userId) {

        model.addAttribute("user", userDaoService.getUserById(userId));        
        return "UserDetailsPage";
    }
    
    
  /*
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
