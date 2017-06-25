/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service.Impl;

import app.model.User;
import app.model.UserRole;
import app.model.repository.UserRoleDao;
import app.service.UserRoleDaoService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserRoleDaoServiceImpl implements UserRoleDaoService{
    
    private UserRoleDao userRoleDao;

    public void setUserRoleDao(UserRoleDao dao) {
        this.userRoleDao = dao;
    }

    @Transactional
    @Override
    public void saveUserRole(UserRole userRole) {
        userRoleDao.saveUserRole(userRole);
    }
    
    @Transactional
    @Override
    public void deleteUserRole(UserRole userRole) {
        userRoleDao.deleteUserRole(userRole);
    }
    
    @Transactional
    @Override
    public void deletUserRoleByUser(User user) {
        userRoleDao.deletUserRoleByUser(user);
    }

    @Transactional
    @Override
    public List<UserRole> getUserRoleList() {
        return userRoleDao.getUserRoleList();
    }
}