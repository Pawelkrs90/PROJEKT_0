package app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="USER_ROLES",uniqueConstraints = @UniqueConstraint(columnNames = { "USER_ROLE_NAME", "USER_NAME" }))
public class UserRole implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID", unique = true, nullable = false)
    private int id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_NAME", nullable = false)
    private User user;
    
    @Column(name = "USER_ROLE_NAME", nullable = false, length = 45)
    private String name;

    public UserRole() {
    
    }

    public UserRole(User user, String role) {
	this.user = user;
	this.name = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
