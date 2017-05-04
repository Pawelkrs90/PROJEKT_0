package app.packages.controllers;

import app.packages.domain.User;
import app.packages.service.UserDaoService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/User")
public class UserController {
    
    private UserDaoService userDaoService;
	
    @Autowired(required=true)
    @Qualifier(value="userDaoService")
    public void setUserService(UserDaoService us){
	this.userDaoService = us;
    }
    
    @RequestMapping(value = "/UserList", method = RequestMethod.GET)
    public String getAllUser(Model model){
        
       // List<User> lista = new ArrayList<>();
       // lista.add(new User(1, "aaa", "aaasdfada", "adadad", "4535353"));
       /// lista.add(new User(2, "bbb", "bbbdsfdsf", "adadad", "4535353"));
       // lista.add(new User(3, "ccc", "cccdsfdsfsd", "adadad", "4535353"));
      //  lista.add(new User(4, "bbb", "bbbdsfdsf", "adadad", "4535353"));
       // lista.add(new User(5, "ccc", "cccdsfdsfsd", "adadad", "4535353"));
       
        model.addAttribute("Users", userDaoService.getUserList());
        
        return "UserList";
    }
    
    
    @RequestMapping(value = "/UserInfo/{id}", method = RequestMethod.GET)
    public String getUserInfo(Model model, @PathVariable("id") int userId){
        
        User user = new User(1, "aaa", "aaasdfada", "adadad", "4535353");
        model.addAttribute("User", userDaoService.getUserById(userId));
        
        return "UserInfo";
    }
    
    
    @RequestMapping(value = "/DeleteUser/{id}", method = RequestMethod.GET)
    public String delteUser(Model model, @PathVariable("id") int userId){
        
        
        return null;
    }
    
    
    @RequestMapping(value = "/AddUser", method = RequestMethod.GET)
    public String initFormAddUser(Model model){
        
        model.addAttribute("newUser", new User());
        
        return "AddUser";
    }

    
    
    @RequestMapping(value = "/AddUser", method = RequestMethod.POST)
    public String processAddProductForm(@ModelAttribute("newUser") @Valid User user,
                                        BindingResult result, HttpServletRequest httpRequest){
        
           
            if(result.hasErrors()){   //jesli Validacja zwroci problem
                return "AddUser";
            }
            
            if(result.getSuppressedFields().length > 0){  //sprawdzenie czy dodano tylko pola zgodne z binderem
                throw new RuntimeException("Proba wiazania niedozwolonych pol: "
                                            + StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
            }
                  
            userDaoService.addUser(user);
            return "redirect:/User/UserList";
        }
    
        @InitBinder
        public void initialiseBinder(WebDataBinder binder){
            
            binder.setAllowedFields("id", "firstName", "lastName");
        }
        
       
}