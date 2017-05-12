package app.model.repository;

import app.model.User;
import app.model.UserRole;
import java.util.List;

public interface UserRoleDao{
    
    public void addUserRole(UserRole userRole);
    public void deleteUserRole(UserRole userRole);
    public void deletUserRoleByUser(User user);
    public List<UserRole> getUserRoleList();
    public List<UserRole> getUserRoleListByUser(User user);
 
}