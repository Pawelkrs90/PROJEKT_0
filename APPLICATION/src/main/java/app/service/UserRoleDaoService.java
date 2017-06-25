/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.model.User;
import app.model.UserRole;
import java.util.List;

public interface UserRoleDaoService {
    
    public void saveUserRole(UserRole userRole);
    public void deleteUserRole(UserRole userRole);
    public void deletUserRoleByUser(User user);
    public List<UserRole> getUserRoleList();
}