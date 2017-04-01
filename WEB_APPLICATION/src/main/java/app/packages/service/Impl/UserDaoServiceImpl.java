/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.packages.service.Impl;

import app.packages.domain.User;
import app.packages.domain.repository.UserDao;
import app.packages.service.UserDaoService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserDaoServiceImpl implements UserDaoService{
    
   // @Autowired
    private UserDao userDao;
    public void setUserDao(UserDao dao){
        this.userDao = dao;
    }
    
    @Override
    @Transactional
    public void addUser(User user) {
        
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public User getUserById(int Id) {
        
        return userDao.getUserById(Id);
    }

    @Override
    @Transactional
    public List<User> getUserList() {
        
        return userDao.getUserList();
    }  


}