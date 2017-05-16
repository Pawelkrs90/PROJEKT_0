
package app.Init.OnlyForMe;

import app.model.User;
import app.model.UserRole;
import app.service.UserDaoService;
import app.service.UserRoleDaoService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/init")
public class InitAdmin {
    
    Logger logger = Logger.getLogger(getClass().getName());
    
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
     
    @RequestMapping(value = {"/adminAccount" }, method = RequestMethod.GET)
    public String initAdminAccount(Model model) {
  
        userDaoService.addUser(new User("ADMIN", "abcd", true, false));
        userRoleDaoService.addUserRole(new UserRole(userDaoService.findByUserName("ADMIN").getId(), "ROLE_ADMIN"));

       // userDaoService.findByUserName("ADMIN").getUserRole().stream().forEach(x->logger.info("XX: "+x.getRole()));
        
        //userDaoService.addRole("ROLE_ADMIN", userDaoService.findByUserName("ADMIN").getId());
        //Dodawanie user role userowi  w DAO zrobić
        //userDaoService.findByUserName("ADMIN").addRole(role);
        
      //  logger.info("Test UserName: "+user.getUsername());
       
        return "index";
    }
    
    
   
    
}
