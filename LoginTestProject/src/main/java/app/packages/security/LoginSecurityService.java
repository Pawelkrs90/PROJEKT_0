/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.packages.security;

import app.packages.model.UserRole;
import app.packages.model.repository.UserDao;
import app.packages.service.UserDaoService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


public class LoginSecurityService implements UserDetailsService {

        private UserDao userDao;

	@Override
        @Transactional
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

	    app.packages.model.User user = userDao.findByUserName(username);
	    List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

	    return (UserDetails) buildUserForAuthentication(user, authorities);
	}

	// Converts model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(app.packages.model.User user, List<GrantedAuthority> authorities) {
            
            return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

            Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
            for (UserRole userRole : userRoles) {
		setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
            }

            List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

            return Result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
