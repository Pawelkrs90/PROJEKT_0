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
import org.jboss.logging.Logger;
import org.springframework.security.core.userdetails.User;
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

    //@Autowired
    private UserDao userDao;
    Logger logger = Logger.getLogger(getClass().getName());
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
            logger.info("APP_LOG - Autentification - Start");
        
            app.model.User user = userDao.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username");
		}
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(),
				true, true, true, getAuthorities(user));
	}

    private List<GrantedAuthority> getAuthorities(app.model.User user) {
            
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        logger.info("APP_LOG - Gettining User Roles");
        
        for (UserRole role : user.getUserRole()) {
		 authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        
        return authorities;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    

}
