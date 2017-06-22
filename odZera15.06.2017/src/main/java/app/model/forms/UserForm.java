
package app.model.forms;

public class UserForm {
    
    private String username;
    private String password;
    private String role_name;

    public UserForm() {}
    
    public UserForm(String username, String password, String role_name) {
        this.username = username;
        this.password = password;
        this.role_name = role_name;
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

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
 
}
