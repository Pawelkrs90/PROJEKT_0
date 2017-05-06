
package Pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "_Ogloszenie")
public class _Ogloszenie implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "login", nullable=true, length=100) 
    private String login;
    @Column(name = "userId", nullable=true, length=100) 
    private int userId;
    @Column(name = "lat",nullable=true, length=100) 
    private double lat;
    @Column(name = "lng",nullable=true, length=100) 
    private double lng;
    @Lob   
    @Type(type = "org.hibernate.type.TextType")
    private String opis;

    public _Ogloszenie() {
    
    }

    public _Ogloszenie(String login, int userId, double lat, double lng) {
        this.login = login;
        this.userId = userId;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    
    
    
    
    
    
}
