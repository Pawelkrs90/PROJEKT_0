import Pojo._Trip;
import Pojo._User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Collection;

@ManagedBean(name="joinToTrip")
@RequestScoped
public class joinToTrip implements Serializable{

    private TripDao daoT;
    private UserDao daoU;

    private _Trip trip;
    private _User user;

    public joinToTrip() {
        
    }

    public String joinToTrip(int tripId) {

        user = LoginController.loginUser;
        daoT = new TripDao();
        daoU = new UserDao();

        try {
            trip = daoT.getById(tripId);
        } catch (NullPointerException e) {
            System.out.println("null pointer exception");
        }

        if(chceckUser(user)) {  //user nie jest uczestnikiem zadnej wycieczki
            System.out.println("OK_1");
            if (user.getId() != trip.getOwnerId()) {  //user nie tworzy tej wycieczki
                System.out.println("OK_2");
                if (trip.getZapisaneOsoby() > 0) {  //sprawdzam czy są wolne msca w wycieczce


                        trip.setZapisaneOsoby(trip.getZapisaneOsoby() - 1);  //zmniejszam ilosc wolnych miejsc
                        trip.getUczestnicy().add(user);
                        user.setJoinTripId(trip.getId());
                        daoT.save(trip);
                        daoU.save(user);

                        System.out.println("dołaczono do wycieczki");
                        return "tripJoinResult.xhtml";


                } else {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                            (FacesMessage.SEVERITY_INFO, "Wycieczka jest pełna.", ""));
                    return null;
                }
            }
            else{

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                        (FacesMessage.SEVERITY_INFO, "Jesteś organizatorem tej wycieczki.", ""));
                return null;
            }


        } else {

            System.out.println(" uczestniczysz juz w wycieczce");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_INFO, "Uczestniczysz juz w wycieczkce.", ""));
            return null;
        }
    }



    public boolean chceckUser(_User user){


        Collection<_Trip> lista = daoT.getList();

        for(_Trip t: lista){

            Collection<_User> uList = daoT.getUczestnicy(t.getId());

            for(_User u: uList){

                if(u.getId() == user.getId()){
                    return false;
                }
            }
        }
        return true;
    }
}
