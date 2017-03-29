package app.packages.controllers;

import app.packages.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/User")
public class UserController {
    
    @RequestMapping(value = "/UserList", method = RequestMethod.GET)
    public String getAllUser(Model model){
        
        List<User> lista = new ArrayList<>();
        lista.add(new User(1, "aaa", "aaasdfada", "adadad", "4535353"));
        lista.add(new User(1, "bbb", "bbbdsfdsf", "adadad", "4535353"));
        lista.add(new User(1, "ccc", "cccdsfdsfsd", "adadad", "4535353"));
        model.addAttribute("Users", lista);
        
        return "UserList";
    }
    
    
    @RequestMapping(value = "/UserInfo/{id}", method = RequestMethod.GET)
    public String getUserInfo(Model model, @PathVariable("id") int userId){
        
        User user = new User(1, "aaa", "aaasdfada", "adadad", "4535353");
        model.addAttribute("User", user);
        
        return "UserInfo";
    }
    
}




