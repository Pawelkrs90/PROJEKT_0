import Pojo._User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@ManagedBean(name="registrationController")
@RequestScoped
public class RegistrationController {


    private _User user;                 //do rejestracji
    private String passwordForCheck;  //do rejestracji
    private UserDao dao;
    private int rok, dzien;
    private String miesiac;
    private ArrayList<Integer> rokList,  dzienList;
    private ArrayList<String> miesiacList;

    public RegistrationController(){

        dao = new UserDao();
        user = new _User();
        user.setUprawnienia("NORMAL");

        //inicjalizacja list rok, miesiac, dzien
        rokList = new ArrayList<>();
        miesiacList = new ArrayList<>(
                Arrays.asList("Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czeriec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień")
        );
        dzienList = new ArrayList<>();

        for(int i = 1900; i <= 2016; i++){
            rokList.add(i);
        }
        for(int i = 1; i <= 31; i++){
            dzienList.add(i);
        }

    }

    public String rejestracja(){

        if(dao.getByKey(user.getLogin()) != null){

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_INFO, "Podany login juz istnieje w bazie", ""));

            return null;
        }
        else{
            Date dataUr = new Date(rok-1900, miesiacList.indexOf(miesiac), dzien);   //-1900 zeby bylo ok :D
            SimpleDateFormat df = new SimpleDateFormat ("dd-MM-yyyy");
            user.setDataUr(dataUr);


            dao.save(user);

            user = new _User();  //zapisałem do bazy wiec tworze kolejny obiekt zeby nie nadpisywać poprzedniego
            user.setUprawnienia("NORMAL");

            return "rejestracjaResults.xhtml";
        }
    }

    public _User getUser() {
        return user;
    }

    public void setUser(_User user) {
        this.user = user;
    }

    public String getPasswordForCheck() {
        return passwordForCheck;
    }

    public void setPasswordForCheck(String passwordForCheck) {
        this.passwordForCheck = passwordForCheck;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getDzien() {
        return dzien;
    }

    public void setDzien(int dzien) {
        this.dzien = dzien;
    }

    public String getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(String miesiac) {
        this.miesiac = miesiac;
    }

    public ArrayList<Integer> getRokList() {
        return rokList;
    }

    public void setRokList(ArrayList<Integer> rokList) {
        this.rokList = rokList;
    }

    public ArrayList<Integer> getDzienList() {
        return dzienList;
    }

    public void setDzienList(ArrayList<Integer> dzienList) {
        this.dzienList = dzienList;
    }

    public ArrayList<String> getMiesiacList() {
        return miesiacList;
    }

    public void setMiesiacList(ArrayList<String> miesiacList) {
        this.miesiacList = miesiacList;
    }
}
