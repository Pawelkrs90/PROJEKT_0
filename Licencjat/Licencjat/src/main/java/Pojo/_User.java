package Pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "_User")
public class _User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  
    @Column(name = "imie",nullable=true, length=100) 
    private String imie;
    @Column(name = "email",nullable=true, length=100) 
    private String email;
    @Column(name = "login",nullable=true, length=100) 
    private String login;
    @Column(name = "haslo",nullable=true, length=100) 
    private String haslo;
    @Temporal(TemporalType.DATE)
    @Column(name = "dataUr")
    private Date dataUr;
    @Column(name = "plec",nullable=true, length=100) 
    private String plec;
    @Column(name = "uprawnienia",nullable=true, length=100)
    private String uprawnienia;
    @Column(name = "myTripId")
    private int myTripId;

    @Column(name = "joinTripId")
    private int joinTripId;

    public _User(){

    }

    public _User(int id, String imie, String email, String login, String haslo, Date dataUr, String plec, String uprawnienia) {
        this.id = id;
        this.imie = imie;
        this.email = email;
        this.login = login;
        this.haslo = haslo;
        this.dataUr = dataUr;
        this.plec = plec;
        this.uprawnienia = uprawnienia;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String Imie) {
        this.imie = Imie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Date getDataUr() {
        return dataUr;
    }

    public void setDataUr(Date dataUr) {
        this.dataUr = dataUr;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(String uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    public int getMyTripId() {
        return myTripId;
    }

    public void setMyTripId(int myTripId) {
        this.myTripId = myTripId;
    }

    public int getJoinTripId() {
        return joinTripId;
    }

    public void setJoinTripId(int joinTripId) {
        this.joinTripId = joinTripId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.imie);
        hash = 73 * hash + Objects.hashCode(this.email);
        hash = 73 * hash + Objects.hashCode(this.login);
        hash = 73 * hash + Objects.hashCode(this.haslo);
        hash = 73 * hash + Objects.hashCode(this.dataUr);
        hash = 73 * hash + Objects.hashCode(this.plec);
        hash = 73 * hash + Objects.hashCode(this.uprawnienia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final _User other = (_User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.imie, other.imie)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.haslo, other.haslo)) {
            return false;
        }
        if (!Objects.equals(this.plec, other.plec)) {
            return false;
        }
        if (!Objects.equals(this.uprawnienia, other.uprawnienia)) {
            return false;
        }
        if (!Objects.equals(this.dataUr, other.dataUr)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "_User{" + "id=" + id + ", imie=" + imie + ", email=" + email + ", login=" + login + ", haslo=" + haslo + ", dataUr=" + dataUr + ", plec=" + plec + ", uprawnienia=" + uprawnienia +'}';
    }
    
    

}
