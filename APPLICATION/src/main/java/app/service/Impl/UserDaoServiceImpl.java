/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service.Impl;

import app.model.User;
import app.model.repository.UserDao;
import app.service.UserDaoService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserDaoServiceImpl implements UserDaoService{
    
    private UserDao userDao;
    
    public void setUserDao(UserDao dao){
        this.userDao = dao;
    }
    
    @Override
    @Transactional
    public void saveUser(User user) {
        
        userDao.saveUser(user);
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

    @Override
    @Transactional
    public User findByUserName(String name) {
       return userDao.findByUserName(name);
    }
}