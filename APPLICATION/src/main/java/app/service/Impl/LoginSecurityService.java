/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service.Impl;

import app.model.User;
import app.model.UserRole;
import app.model.repository.UserDao;
import app.model.repository.UserRoleDao;
import app.service.UserDaoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginSecurityService implements  UserDetailsService {

    private UserDaoService userDaoService;
    
    @Autowired(required=true)
    @Qualifier(value="userDaoService")
    public void setUserService(UserDaoService us){
	this.userDaoService = us;
    }

    Logger logger = Logger.getLogger(getClass().getName());
    
    @Override
    @Transactional()
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
            logger.info("username: "+username);
           
            User user ;
            try{
                user = userDaoService.findByUserName(username);
                //logger.info("username: "+user.getUsername());
                //logger.info("password: "+user.getPassword());
            }catch(Exception e){
                logger.info("wyjebalo sie");
            }
            
            logger.info("APP_LOG - Autentification - Start");
           
          
            //if(user!=null){
                Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), 
                                                                                                         new SimpleGrantedAuthority("ROLE_USER")));
               /* userRoleDao.getUserRoleListByUser(user.getId()).stream()
                                                               .map(role->role.getRole())
                                                               .forEach(role->authorities.add(new SimpleGrantedAuthority(role)));
                */
              /*  user.getUserRole().stream()
                                  .map(role->role.getRole())
                                  .forEach(role->new SimpleGrantedAuthority(role));
                */
                org.springframework.security.core.userdetails.User securityUser = null;
                
                logger.info("APP_LOG - User Roles loaded");
        
                
                /* return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.isEnabled(),
                    true,
                    true,
                    true,
                    authorities
                );*/
               
               return new org.springframework.security.core.userdetails.User(
                    username,
                    "abcd",
                    true,
                    true,
                    true,
                    true,
                    authorities
                );
           // }
           // else{
           //     throw new UsernameNotFoundException("Invalid username");
           // }       
	}
    
   /* public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    */

}
