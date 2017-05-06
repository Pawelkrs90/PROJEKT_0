
import Pojo._User;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="userController")
@RequestScoped
public class UserController implements Serializable {
    
    private _User user;
    private UserDao dao;
    private String zalogowanyLogin="";
    private int zalogowanyID;
    public static boolean czyZalogowany = false;
    public static boolean czyAdmin = false;

    public UserController(){

        //Dane Admina - dodane tylko raz
        dao = new UserDao();

        if(dao.getByKey("Admin")==null) {

            user = new _User();
            user.setImie("Administrator");
            user.setLogin("Admin");
            user.setHaslo("Admin");
            user.setEmail("Admin@Admin.com");
            user.setUprawnienia("ADMIN");
            user.setPlec("ADMIN-MAN");

            Date data = new Date();
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                data = dataFormat.parse("29/10/1990");
                user.setDataUr(data);
            } catch (ParseException ex) {
                System.out.println("error :)");
            }

            dao.save(user);
        }

        user = new _User();
        user.setHaslo("");
        user.setLogin("");
        user.setUprawnienia("NORMAL");

        System.out.println("Aktualizacja topPanel");
        if(LoginController.loginUser != null){ //jesli zalogowany

            System.out.println("Zalogowany");
            user = LoginController.loginUser;
            zalogowanyLogin = user.getLogin();
            zalogowanyID = user.getId();
            czyZalogowany = true;

            if(user.getUprawnienia().equals("ADMIN")){

                System.out.println("Admin");
                czyAdmin = true;
            }
            else{
                System.out.println("Nie Admin");
            }
        }
        else{
            System.out.println("Nie zalogowany");
            czyZalogowany = false;
            czyAdmin = false;
            zalogowanyLogin = "";
        }

    }

    public String getZalogowanyLogin() {
        return zalogowanyLogin;
    }

    public void setZalogowanyLogin(String zalogowanyLogin) {
        this.zalogowanyLogin = zalogowanyLogin;
    }

    public int getZalogowanyID() {
        return zalogowanyID;
    }

    public void setZalogowanyID(int zalogowanyID) {
        this.zalogowanyID = zalogowanyID;
    }

    public boolean isCzyZalogowany() {
        return czyZalogowany;
    }

    public void setCzyZalogowany(boolean czyZalogowany) {
        this.czyZalogowany = czyZalogowany;
    }

    public boolean isCzyAdmin() {
        return czyAdmin;
    }

    public void setCzyAdmin(boolean czyAdmin) {
        this.czyAdmin = czyAdmin;
    }

    public _User getUser() {
        return user;
    }

    public void setUser(_User user) {
        this.user = user;
    }

}
