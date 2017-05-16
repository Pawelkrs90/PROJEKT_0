/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.repository;

import app.model.User;
import app.model.UserRole;
import java.util.List;
import java.util.Set;

public interface UserDao{
    
    public void addUser(User user);
    public User getUserById(int Id);
    public List<User> getUserList();
    public User findByUserName(String name); 
}