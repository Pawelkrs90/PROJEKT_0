import Pojo._Trip;
import Pojo._User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@ManagedBean(name="getUczestnicy")
@RequestScoped
public class getUczestnicy implements Serializable{

    TripDao daoT;
    String ListaString;

    public getUczestnicy() {
        ListaString = "";
    }

    public String getUsersList(int id){

        daoT = new TripDao();
        _Trip trip = daoT.getById(id);
        StringBuilder sb = new StringBuilder();

        Collection<_User> lista = trip.getUczestnicy();
        for (_User iter : lista) {

           sb.append(iter.getLogin()).append(", ");
        }

        if(sb.length()==0){
            ListaString = "Brak użytkowników";
        }
        else{
            ListaString = sb.toString();
        }

        System.out.println("getUczestnicy - zbudowano liste uczestnikow wycieczki");

        return this.ListaString;
    }
}
