package app.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin";
    }
     
    @RequestMapping(value = "/vip", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "vip";
    }
    
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage() {
        return "loginPage";
    }
    
    @RequestMapping(value = "/loginSucces", method = RequestMethod.GET)
    public String loginSucces(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("result", "SUCCES");
        return "loginResult";
    }
    
    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginFailed(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("result", "FAILED");
        return "redirect:/loginPage?error=true";
    }

   /* @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        //model.addAttribute("user", getPrincipal());
        model.addAttribute("result", "LOG OUT");
        return "loginResult";
    }*/
     @RequestMapping(value="/succLogout", method = RequestMethod.GET)
     public String logoutPage(ModelMap model) {
        
        model.addAttribute("result", "LOG OUT");
        return "redirect:/loginPage?logout=true";
     }
             
    private String getPrincipal(){
        
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}