package app.model.repository;

import app.model.User;
import java.util.List;

public interface UserDao{
    
    public void saveUser(User user);
    public User getUserById(int Id);
    public List<User> getUserList();
    public User findByUserName(String name);
     
}