
package Pojo;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.primefaces.model.map.LatLng;

@Entity
@Table(name = "_Trip")
public class _Trip implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  
    @Column(name="ownerId",nullable=true,length=100) 
    private int ownerId;  //id usera - zalozyciel wycieczki
    @Column(name="nazwa",nullable=true,length=100)    
    private String nazwa;
    @Column(name="typTransportu",nullable=true,length=100)
    private String typTransportu;
    @Column(name="mscDocelowe",nullable=true,length=100)
    private String mscDocelowe;
    @Column(name="celPodrozy",nullable=true,length=100)
    private String celPodrozy;
    @Column(name="limitOsob",nullable=true,length=100)
    private int limitOsob;
    @Column(name="zapisaneOsoby",nullable=true,length=100)
    private int zapisaneOsoby; //wolnemiejsca
    @Column(name="minWiek",nullable=true,length=100)
    private int minWiek;
    @Column(name="maxWiek",nullable=true,length=100)
    private int maxWiek;
    @Column(name="plec",nullable=true,length=100)
    private String plec;
    @Column(name="wymogi",nullable=true,length=100)
    private String wymogi;
    @Column(name="zastrzezenia",nullable=true,length=100)
    private String zastrzezenia;
    @Column(name="koszt",nullable=true,length=100)
    private int koszt;
    @Temporal(TemporalType.DATE)
    @Column(name = "startDate")
    private Date startDate;         //data rozpoczecia
    @Column(name = "czasPodrozy", nullable=true,length=100)
    private int czasPodrozy;  //ilosc dni
    @Column(name = "dystans",nullable=true,length=100)
    private int dystans;      //dystans do przebycia dziennie;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String JSON_TRIP_DATA;

    @Transient    //element nie umieszczany w bazie danych
    private String uczestnicyString;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<_User> uczestnicy = new ArrayList<>();
    
  
    public _Trip(){
        
        this.limitOsob=10;
        this.minWiek=18;
        this.maxWiek=80;
        this.koszt = 0;
        this.plec = "Bez znaczenia";
        this.dystans = 0;
        this.wymogi = "Brak";
        this.zastrzezenia = "Brak";
        this.JSON_TRIP_DATA = "";
        this.czasPodrozy = 7;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTypTransportu() {
        return typTransportu;
    }

    public void setTypTransportu(String typTransportu) {
        this.typTransportu = typTransportu;
    }

    public String getMscDocelowe() {
        return mscDocelowe;
    }

    public void setMscDocelowe(String mscDocelowe) {
        this.mscDocelowe = mscDocelowe;
    }

    public String getCelPodrozy() {
        return celPodrozy;
    }

    public void setCelPodrozy(String celPodrozy) {
        this.celPodrozy = celPodrozy;
    }

    public int getLimitOsob() {
        return limitOsob;
    }

    public void setLimitOsob(int limitOsob) {
        this.limitOsob = limitOsob;
    }

    public int getZapisaneOsoby() {
        return zapisaneOsoby;
    }

    public void setZapisaneOsoby(int zapisaneOsoby) {
        this.zapisaneOsoby = zapisaneOsoby;
    }

    public int getMinWiek() {
        return minWiek;
    }

    public void setMinWiek(int minWiek) {
        this.minWiek = minWiek;
    }

    public int getMaxWiek() {
        return maxWiek;
    }

    public void setMaxWiek(int maxWiek) {
        this.maxWiek = maxWiek;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getWymogi() {
        return wymogi;
    }

    public void setWymogi(String wymogi) {
        this.wymogi = wymogi;
    }

    public String getZastrzezenia() {
        return zastrzezenia;
    }

    public void setZastrzezenia(String zastrzezenia) {
        this.zastrzezenia = zastrzezenia;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getCzasPodrozy() {
        return czasPodrozy;
    }

    public void setCzasPodrozy(int czasPodrozy) {
        this.czasPodrozy = czasPodrozy;
    }

    public int getDystans() {
        return dystans;
    }

    public void setDystans(int dystans) {
        this.dystans = dystans;
    }

    public int getKoszt() {
        return koszt;
    }

    public void setKoszt(int koszt) {
        this.koszt = koszt;
    }

    public String getJSON_TRIP_DATA() {
        return JSON_TRIP_DATA;
    }

    public void setJSON_TRIP_DATA(String JSON_TRIP_DATA) {
        this.JSON_TRIP_DATA = JSON_TRIP_DATA;
    }

    public Collection<_User> getUczestnicy() {
        return uczestnicy;
    }

    public void setUczestnicy(Collection<_User> uczestnicy) {
        this.uczestnicy = uczestnicy;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.ownerId;
        hash = 17 * hash + Objects.hashCode(this.nazwa);
        hash = 17 * hash + Objects.hashCode(this.typTransportu);
        hash = 17 * hash + Objects.hashCode(this.mscDocelowe);
        hash = 17 * hash + Objects.hashCode(this.celPodrozy);
        hash = 17 * hash + this.limitOsob;
        hash = 17 * hash + this.zapisaneOsoby;
        hash = 17 * hash + this.minWiek;
        hash = 17 * hash + this.maxWiek;
        hash = 17 * hash + Objects.hashCode(this.plec);
        hash = 17 * hash + Objects.hashCode(this.wymogi);
        hash = 17 * hash + Objects.hashCode(this.zastrzezenia);
        hash = 17 * hash + this.koszt;
        hash = 17 * hash + Objects.hashCode(this.startDate);
        hash = 17 * hash + this.czasPodrozy;
        hash = 17 * hash + this.dystans;
        hash = 17 * hash + Objects.hashCode(this.JSON_TRIP_DATA);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final _Trip other = (_Trip) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.ownerId != other.ownerId) {
            return false;
        }
        if (!Objects.equals(this.nazwa, other.nazwa)) {
            return false;
        }
        if (!Objects.equals(this.typTransportu, other.typTransportu)) {
            return false;
        }
        if (!Objects.equals(this.mscDocelowe, other.mscDocelowe)) {
            return false;
        }
        if (!Objects.equals(this.celPodrozy, other.celPodrozy)) {
            return false;
        }
        if (this.limitOsob != other.limitOsob) {
            return false;
        }
        if (this.zapisaneOsoby != other.zapisaneOsoby) {
            return false;
        }
        if (this.minWiek != other.minWiek) {
            return false;
        }
        if (this.maxWiek != other.maxWiek) {
            return false;
        }
        if (!Objects.equals(this.plec, other.plec)) {
            return false;
        }
        if (!Objects.equals(this.wymogi, other.wymogi)) {
            return false;
        }
        if (!Objects.equals(this.zastrzezenia, other.zastrzezenia)) {
            return false;
        }
        if (this.koszt != other.koszt) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (this.czasPodrozy != other.czasPodrozy) {
            return false;
        }
        if (this.dystans != other.dystans) {
            return false;
        }
        if (!Objects.equals(this.JSON_TRIP_DATA, other.JSON_TRIP_DATA)) {
            return false;
        }
        return true;
    }


}
