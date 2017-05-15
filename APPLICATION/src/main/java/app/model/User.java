package app.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;
    
    @Column(name="enabled")
    private boolean enabled = true;
    
    @Column(name="locked")
    private boolean locked = false;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    public User(){
        
    }
    
    public User(String username, String password, boolean enabled, boolean locked) {
	this.username = username;
	this.password = password;
	this.enabled = enabled;
        this.locked = locked;
    }

    public User(String username, String password, boolean enabled, boolean locked, Set<UserRole> userRole) {
	this.username = username;
	this.password = password;
	this.enabled = enabled;
        this.locked = locked;
	this.userRole = userRole;
    }

    public void addRole(UserRole role){
        userRole.add(role);
    }
    
    public void deleteRole(UserRole role){
        userRole.remove(role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    
}
