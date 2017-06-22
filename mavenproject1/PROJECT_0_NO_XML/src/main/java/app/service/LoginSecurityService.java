
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        logger.info("Username: xxx");
        app.model.User user;
        
        if(userDao!=null){
            user = userDao.findByUserName(username);
            logger.info("Username: "+user.getUsername());
            if (user == null) {
              throw new UsernameNotFoundException("Invalid username or password");
            }
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user));
        }
        else{
            logger.info("xxxxxxxxEMPTY");
        }

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
