
import Pojo._Trip;
import Pojo._User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="profilController")
@RequestScoped
public class profilController implements Serializable {

    private _User user;
    TripDao daoT;
    private String twojaWycieczka;
    private String joinedTrips;
    
    public profilController(){

        if(LoginController.loginUser != null){
            
            user = (_User) LoginController.loginUser;

            try{
                daoT = new TripDao();
                _Trip trip =  daoT.getById(user.getMyTripId());

                twojaWycieczka = trip.getMscDocelowe();

                trip = daoT.getById(user.getJoinTripId());

                joinedTrips = trip.getMscDocelowe();

                System.out.println("twojaWycieczka - "+twojaWycieczka);
            }
            catch(Exception e){
                System.out.println("ERROR - "+e);
            }
        }
    }


    public _User getUser() {
        return user;
    }

    public void setUser(_User user) {
        this.user = user;
    }

    public String getTwojaWycieczka() {
        return twojaWycieczka;
    }

    public void setTwojaWycieczka(String twojaWycieczka) {
        this.twojaWycieczka = twojaWycieczka;
    }

    public String getJoinedTrips() {
        return joinedTrips;
    }

    public void setJoinedTrips(String joinedTrips) {
        this.joinedTrips = joinedTrips;
    }
}
