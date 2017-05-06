
import Pojo._Trip;
import Pojo._User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "nowaWycieczka")
@SessionScoped
public class KontrolerNowejWycieczki implements Serializable{

    private boolean innyTyp=false;
    private boolean innyCel=false;
    private _Trip newTrip;
    private List<String> ListaTypTransportu = new ArrayList<>();
    private List<String> ListCelPodrozy = new ArrayList<>();
    private TripDao dao;
    private UserDao daoU;

    public KontrolerNowejWycieczki() {
        
        newTrip = new _Trip();
        
        ListaTypTransportu.add("Piesza wędrówka");
        ListaTypTransportu.add("Rowerem");
        ListaTypTransportu.add("Samochodem");
        ListaTypTransportu.add("Motocyklem");
        ListaTypTransportu.add("Samolotem");
        ListaTypTransportu.add("Statkiem");
        ListaTypTransportu.add("Komunikacja publiczna");
        ListaTypTransportu.add("\"Na_Stopa\"");
        ListaTypTransportu.add("Inne");
        
        
        ListCelPodrozy.add("Zwiedzanie");
        ListCelPodrozy.add("Odpoczynek");
        ListCelPodrozy.add("Sporty śnieżne");
        ListCelPodrozy.add("Sporty wodne");
        ListCelPodrozy.add("Rajd, przejażdżka");        
        ListCelPodrozy.add("Imprezowanie");
        ListCelPodrozy.add("Zakupy");
        ListCelPodrozy.add("Koncerty, wydarzenia");
        ListCelPodrozy.add("Inne");
    }


    public void wyborTypuTransportu(){
      
        if(this.newTrip.getTypTransportu().equals("Inne")){
           innyTyp=true;
           this.newTrip.setTypTransportu(null);
        }
        else{
           innyTyp=false;  
        }
           
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_INFO, "Wybrałeś typ transportu: "+ this.newTrip.getTypTransportu(), " "));
    }
    
    public void wyborCeluPodrozy(){
    
        if(newTrip.getCelPodrozy().equals("Inne")){
            innyCel = true;
            this.newTrip.setCelPodrozy(null);
        }
        else{
            innyCel = false;  
        }
           
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_INFO, "Wybrałeś cel podróży: "+ this.newTrip.getCelPodrozy(), " "));
      
    
    }    
    
    public String saveData(){
      
        System.out.println("dane: "+newTrip.getJSON_TRIP_DATA());
        _User u = LoginController.loginUser;
        newTrip.setOwnerId(u.getId());
        newTrip.setZapisaneOsoby(newTrip.getLimitOsob());  //wolne miesjca w wycieczce

        dao = new TripDao();
        dao.save(newTrip);

        daoU = new UserDao();
        u.setMyTripId(newTrip.getId());
        daoU.save(u);

        newTrip = new _Trip();   // nowy obiekt wycieczki zeby dodać kolejna wycieczke ewnetualnie
        
        return "zapisWycieczkiResults.xhtml";
    }
    
    public _Trip getNewTrip() {
        return newTrip;
    }

    public void setNewTrip(_Trip newTrip) {
        this.newTrip = newTrip;
    }

    public List<String> getListaTypTransportu() {
        return ListaTypTransportu;
    }

    public List<String> getListCelPodrozy() {
        return ListCelPodrozy;
    }

    public boolean isInnyTyp() {
        return innyTyp;
    }

    public boolean isInnyCel() {
        return innyCel;
    }

}
