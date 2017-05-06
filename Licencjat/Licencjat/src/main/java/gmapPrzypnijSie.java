
import Pojo._Ogloszenie;
import Pojo._User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class gmapPrzypnijSie  implements Serializable{
    
    private String centerPosition ;
    private int zoom ;
    private String mapType;
    private _User user;
    private _Ogloszenie ogloszenie;
    private OgloszenieDao dao;
    private String opis;
    private Double lat;
    private Double lng;
    private String adres="";
    
    public gmapPrzypnijSie(){
        
        if(UserController.czyZalogowany == true){
            user = LoginController.loginUser;
            ogloszenie = new _Ogloszenie();
            ogloszenie.setLogin(user.getLogin());
            ogloszenie.setUserId(user.getId());
        }
        

        this.zoom = 10;
        this.centerPosition = "51.246058159, 22.541917562";
        this.mapType = "roadmap";
        
    }
    
    public String save(){
        System.out.println("opis: "+opis+" lat: "+lat+" lng: "+lng);
        dao = new OgloszenieDao();
        ogloszenie.setLat(lat);
        ogloszenie.setLng(lng);
        ogloszenie.setOpis(opis);
        dao.save(ogloszenie);
        
        opis="";
        ogloszenie = new _Ogloszenie();
        ogloszenie.setLogin(user.getLogin());
        ogloszenie.setUserId(user.getId());
        
        return null;
    }
    
    public String getCenterPosition() {
        return centerPosition;
    }

    public void setCenterPosition(String centerPosition) {
        this.centerPosition = centerPosition;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public _User getUser() {
        return user;
    }

    public void setUser(_User user) {
        this.user = user;
    }

    public _Ogloszenie getOgloszenie() {
        return ogloszenie;
    }

    public void setOgloszenie(_Ogloszenie ogloszenie) {
        this.ogloszenie = ogloszenie;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }


}
