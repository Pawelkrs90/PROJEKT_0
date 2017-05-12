/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.service.*;
import app.model.User;
import java.util.List;


public interface UserDaoService {
    
    public void addUser(User user);
    public User getUserById(int Id);
    public List<User> getUserList();
    public User findByUserName(String name);
}