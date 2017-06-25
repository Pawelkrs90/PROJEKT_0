/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.model.UserRole;
import app.model.repository.UserDao;
import app.service.UserDaoService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.Attributes;
import org.jboss.logging.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class LoginSecurityService implements UserDetailsService {

    Logger logger = Logger.getLogger(getClass().getName());
    private UserDao userDao;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        logger.info("ROZPOCZYNAM LOGOWANIE");
        app.model.User user;
        
       if(userDao != null){
           logger.info("OK. -> DAO != NULL");
           logger.info("POBIERAM USERA: "+username);
           user = userDao.findByUserName(username);
           
           if(user==null){
                logger.info("ERRRO. USER == NULL");
                throw new UsernameNotFoundException("Invalid username or password");
           }
           else{
               logger.info("OK. USER != NULL");
               return new org.springframework.security.core.userdetails.
                       User(username, user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user));
           }
       }
       else{
           logger.info("DAO == NULL");
       }
        
        /*
        
        if(userDao!=null){
            logger.info("POBIERAM USERA");
            user = userDao.findByUserName(username);
            logger.info("Username: "+user.getUsername());
            if (user == null) {
              throw new UsernameNotFoundException("Invalid username or password");
            }
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user));
        }
        else{
            logger.info("DAO JEST NULLEM");
        }*/

        return null;
    }

    private List<GrantedAuthority> getAuthorities(app.model.User user) {
            
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserRole role : user.getUserRole()) {
		authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        return authorities;
    }

}
