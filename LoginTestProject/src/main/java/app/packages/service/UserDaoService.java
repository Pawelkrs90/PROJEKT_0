/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.packages.service;

import app.packages.model.User;
import java.util.List;


public interface UserDaoService {
    
    public void addUser(User user);
    public User getUserById(int Id);
    public List<User> getUserList();
    public User findByUserName(String name);
}