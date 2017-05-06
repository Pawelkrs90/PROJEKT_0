import Pojo._Trip;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="ListOfTrips")
@RequestScoped
public class tripListController {

    private TripDao daoT;
    private UserDao daoU;
    private List<_Trip> TripList;  //lista uzytkownikow pobrana z bazy
    private String searchValue;

    public tripListController() {

        daoT = new TripDao();
        daoU = new UserDao();
        TripList = daoT.getList();
    }

    public void LoadTripList() {

        TripList = daoT.getList();
    }

    public void getOrderedTripList(String orderBy) {

        System.out.println("pobieraniae listy posortowanej");

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                (FacesMessage.SEVERITY_INFO, "SORTOWANIE WEDŁUG KLUCZA: "+orderBy, ""));
        TripList = daoT.getOrderedList(orderBy);
    }

    public void getFreeSpaceList(){

        System.out.println("pobieraniae listy posortowanej według wolych miejsc");

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                (FacesMessage.SEVERITY_INFO, "SORTOWANIE WEDŁUG WOLNYCH MIEJSC ", ""));

        TripList = daoT.getFreeSpaceList();
    }

    public void search(){

        System.out.println("szukanie");

        TripList = daoT.getListByKey(searchValue);
        searchValue = "";

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                (FacesMessage.SEVERITY_INFO, "Wyszukiwanie ukonczone", ""));
    }

    public String getUser(int id) {

        return daoU.getById(id).getLogin();
    }

    public void deleteTrip(_Trip trip){

        daoT.delete(trip);
        LoadTripList();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_INFO, "USUNIETO WYCIECZKE - INDEX: "+trip.getId(), ""));
    }

    public List<_Trip> getTripList() {
        return TripList;
    }

    public void setTripList(List<_Trip> tripList) {
        TripList = tripList;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}




