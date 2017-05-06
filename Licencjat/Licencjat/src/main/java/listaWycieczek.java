
import Pojo._Trip;
import Pojo._User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="listaWycieczek")
@RequestScoped
public class listaWycieczek implements Serializable{
    
    private List<_User> UserList;  //lista uzytkownikow pobrana z bazy
    private List<_Trip> TripList;  //lista uzytkownikow pobrana z bazy
    UserDao daoU;
    TripDao daoT;
    private String guestLogin;
    private String guestEmail;

    public listaWycieczek() {
        
         daoU = new UserDao();
         daoT = new TripDao();
        
    }
    

    public String getUser(int id){
        return daoU.getById(id).getLogin();
    } 
    
    public void LoadUserList(){
        
        UserList = daoU.getList();
    }
    
    public void LoadTripList(){
        
        TripList = daoT.getList();
    }

    public void deleteUser(_User user){
        
        daoU.delete(user);
        LoadUserList();
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_INFO, "USUNIETO UZYTKOWNIKA - INDEX: "+user.getId(), ""));
    }
    
    public void deleteTrip(_Trip trip){
        
        daoT.delete(trip);
        LoadTripList();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_INFO, "USUNIETO WYCIECZKE - INDEX: "+trip.getId(), ""));
    }

    public List<_User> getUserList() {
        LoadUserList();
        return UserList;
    }

    public void setUserList(List<_User> UserList) {
        this.UserList = UserList;
    }

    public List<_Trip> getTripList() {
        LoadTripList();
        return TripList;
    }

    public void setTripList(List<_Trip> TripList) {
        this.TripList = TripList;
    }

    public String getGuestLogin() {
        return guestLogin;
    }

    public void setGuestLogin(String guestLogin) {
        this.guestLogin = guestLogin;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }
}
