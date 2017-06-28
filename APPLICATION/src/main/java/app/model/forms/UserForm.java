
package app.model.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {
    
    @NotNull(message="{Validation.User.Username.NotNull}")
    @Size(min=4, max=25, message="{Validation.User.Username.Size}")
    private String username;
    
    @NotNull(message="{Validation.User.Password.NotNull}")
    @Size(min=4, max=25, message="{Validation.User.Password.Size}")
    private String password;
    
    @Pattern(regexp = "ROLE_[A-Z]", message = "{Validation.User.RoleName.Pattern}")
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
