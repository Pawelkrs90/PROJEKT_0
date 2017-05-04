/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.packages.controllers;

import app.packages.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Login")
public class LoginController {
    
    @RequestMapping(value = "/LoginUser", method = RequestMethod.GET)
        public String loginFormUserInit(Model model){

            return "LoginUser";
        }
        
        @RequestMapping(value = "/LoginSucces", method = RequestMethod.GET)
        public String loginSucces(Model model){

            model.addAttribute("loginResult", "LOGIN SUCCES");
            return "LoginResult";
        }
        
        @RequestMapping(value = "/LoginFailed", method = RequestMethod.GET)
        public String loginFailed(Model model){

            model.addAttribute("loginResult", "LOGIN FAILED");
            return "LoginResult";
        }
        
        @RequestMapping(value = "/LoginLogout", method = RequestMethod.GET)
        public String loginLogout(Model model){

            model.addAttribute("loginResult", "LOGOUT SUCCES");
            return "LoginResult";
        }
      
}
