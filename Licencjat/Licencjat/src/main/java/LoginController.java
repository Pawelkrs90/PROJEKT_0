import Pojo._User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="loginController")
@RequestScoped
public class LoginController {

    private UserDao dao;
    private _User user;
    protected String login = ""; //do logowania
    protected String haslo = ""; //do logowania
    private String loginInfoResult = "";
    public static _User loginUser;

    public LoginController(){

        dao = new UserDao();
        user = null;
    }

    public String logAsAdmin(){

        dao = new UserDao();
        user = dao.getByKey("Admin");

        loginUser = user;
        if(user.getUprawnienia().equals("ADMIN")){
            UserController.czyZalogowany = true;
            UserController.czyAdmin = true;
        }

        System.out.println("Zalogowny: "+user.getLogin());
        user = null;

        return "logowanieResults.xhtml";
    }

    public String logowanie(){

        dao = new UserDao();
        user = dao.getByKey(login);

        if(user!=null){     //jesli uzytkownik jest w bazie
            System.out.println("Część logująca");

            if(user.getHaslo().equals(haslo)){  //sprawdzam czy haslo jes ok

                loginUser = user;
                if(loginUser.getUprawnienia().equals("ADMIN")){
                    UserController.czyAdmin = true;
                }
                UserController.czyZalogowany = true;

                user = null;
                this.login = ""; //czyszcze pola po zalogowaniu sie
                this.haslo = "";

                return "logowanieResults.xhtml";
            }
            else{

                user = null;
                login="";
                haslo="";
                this.loginInfoResult = "Błędne haslo";
                System.out.println("Błędne haslo");
                return null;
            }
        }
        else{
            login="";
            haslo="";
            this.loginInfoResult = "Brak Użytkownika w bazie";
            System.out.println("Brak Użytkownika w bazie");
            return null;
        }
    }

    public String wylogoj(){

        loginUser = null;
        UserController.czyZalogowany = false;
        UserController.czyAdmin = false;

        return "wylogowanieResults.xhtml";
    }

    public _User getUser() {
        return user;
    }

    public void setUser(_User user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getLoginInfoResult() {
        return loginInfoResult;
    }

    public void setLoginInfoResult(String loginInfoResult) {
        this.loginInfoResult = loginInfoResult;
    }
}
