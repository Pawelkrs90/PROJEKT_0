import Pojo._Trip;
import Pojo._User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name="ListOfUsers")
@SessionScoped
public class userListController {

    private UserDao daoU;
    private List<_User> UserList;  //lista uzytkownikow pobrana z bazy

    public userListController() {

        daoU = new UserDao();
        UserList = daoU.getList();
    }

    public void LoadUserList(){

        UserList = daoU.getList();
    }

    public void deleteUser(_User user){

        daoU.delete(user);
        LoadUserList();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                (FacesMessage.SEVERITY_INFO, "USUNIETO UZYTKOWNIKA - INDEX: "+user.getId(), ""));
    }

    public List<_User> getUserList() {
        return UserList;
    }

    public void setUserList(List<_User> userList) {
        UserList = userList;
    }
}




