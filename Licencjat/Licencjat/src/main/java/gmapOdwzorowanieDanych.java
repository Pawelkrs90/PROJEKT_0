
import Pojo._Trip;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class gmapOdwzorowanieDanych  implements Serializable{
    
    private String centerPosition ;
    private int zoom ;
    private String mapType;
    private _Trip trip;
    private TripDao dao;
    private String jsonData;

    public gmapOdwzorowanieDanych(){
            
        this.zoom = 10;
        this.centerPosition = "37.77, -122.447";
        this.mapType = "roadmap";
        
    }
    public String DownloadTrip(int id){
       
        dao = new TripDao();
        trip = dao.getById(id);
        
       
        jsonData = trip.getJSON_TRIP_DATA();
        if(jsonData.equals("")){
                
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                (FacesMessage.SEVERITY_INFO, "Brak trasy podróży.", ""));
            
            System.out.println("Brak Trasy");
        }
        
        return "gmapOdwzorowanieDanych.xhtml";
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

    public _Trip getTrip() {
        return trip;
    }

    public void setTrip(_Trip trip) {
        this.trip = trip;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
    
    
    
    
    
    
    
}
