package app.controllers;

import app.model.User;
import app.model.UserRole;
import app.service.UserDaoService;
import app.service.UserRoleDaoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CRUDController {
    
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
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
       
        User user = new User();
        user.setUsername("Pawel");
        user.setPassword("hasloHaslo");
        user.setEnabled(true);
        
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        try{
            user.setCd_date(ft.parse("1990-10-29"));
        }
        catch(ParseException e){
            logger.info("PARSE PROBLEM");
        }
        
        userDaoService.addUser(user);
        
        UserRole userRole = new UserRole("ADMIN_ROLE");
        userRole.setUser(user);
        user.getUserRoles().add(userRole);
        
        userRoleDaoService.addUserRole(userRole);
        
        
        return "index";
    }
     
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        
        model.addAttribute("Users", userDaoService.getUserList());
       
        return "UserList";
    }
    
    
    
}
