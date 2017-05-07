package app.model;

public class UserRoleForm {
    
    private String userName;
    private String userRole;

    public UserRoleForm() {
        
    }
    public UserRoleForm(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
}
