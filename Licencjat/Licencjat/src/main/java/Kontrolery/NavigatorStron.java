package Kontrolery;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "NavigatorStron")
@SessionScoped
public class NavigatorStron implements Serializable{
    
    public int activPage=1;
    
    
    public String moveToHome(){
        activPage = 1;
        return "index.xhtml";
    }
    public String moveToProfil(){
        activPage = 2;
        return "profil.xhtml";
    }
    public String moveToNewTrip(){
        activPage = 3;
        return "nowaWycieczka.xhtml";
    }   
    public String moveToPrzypnijSie(){
        activPage = 4;
        return "przypnijSie.xhtml";
    }
    public String moveToListaWycieczek(){
        activPage = 5;
        return "tripDataForUsers.xhtml";
    }
    public String moveToMapaOsob(){
        activPage = 6;
        return "mapaPrzypietych.xhtml";
    }
    public String moveToPanelAdmina(){
        activPage = 7;
        return "PanelAdministratora.xhtml";
    }
    public String moveToRejestracja(){
        activPage = 0;
        return "rejestracja.xhtml";
    }
    public String moveToMapCreator(){
        activPage = 3;
        return "gmapWyznaczanieTrasy.xhtml";
    }
    public String moveToOdwzorowanieMapy(){
        activPage = 0;
        return "gmapOdwzorowanieDanych.xhtml";
    }
    public String moveToProfilView(){
        activPage = 2;
        return "profilView.xhtml";
    }
    
    
    public int getActivPage() {
        System.out.println("getPage: "+activPage);
        return activPage;
    }

    public void setActivPage(int activPage) {
        this.activPage = activPage;
    }

    
    
    
 
    
    
    
    
    
    
}
