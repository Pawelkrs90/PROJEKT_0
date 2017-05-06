package GmapController;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;


@SessionScoped
@ManagedBean
public class GmapWyznaczanieTrasyController extends HttpServlet implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String opis;
    private MapModel model;
    private String nazwa;
    private double lat; 
    private double lng;
    private Marker marker;
    private String centerPosition ;
    private int zoom ;
    private String mapType;
    private boolean showMenu = false;
    private String overlayType = "PUNKT"; 
    private int sizeObszar=1;
    private String obszarOpis;
    private String GeocodingSearch;  
    private String markertype = "ControlPoint";

    private List<String>typyPunktow;  
    
    
    @PostConstruct
    public void init() {   
        
        this.markertype="START";
        this.model = new DefaultMapModel();
        this.zoom = 10;
        this.centerPosition = "51.246058159, 22.541917562";
        this.mapType = "SATTELITE";
        
        
       // marker = new Marker(new LatLng(37.77, -122.447), "titttle1" );
      //  model.addOverlay(marker);
       // marker = new Marker(new LatLng(37.768, -122.511), "titttle2" );
       // model.addOverlay(marker);

        typyPunktow = new ArrayList<String>(
            Arrays.asList("ControlPoint", "START", "KONIEC", "PUNKT_SZCZEGOLNY", "INNY_PUNKT"));

    }

    public void wybierzOverlay(String typ){
              
        this.overlayType = typ;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
            (FacesMessage.SEVERITY_INFO, "OVERLAY_TYPE: "+ this.overlayType, ""));
    }
 
    public void onMarkerSelect(OverlaySelectEvent event) {
    //    marker = (Marker) event.getOverlay();
    }
    
    public void onMarkerDrag(MarkerDragEvent event) {
        
     //   marker = event.getMarker();
     //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
      //  (FacesMessage.SEVERITY_INFO, "PRZESUNIECIE POZYCJI UDANE", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
    }

    public void onStateChange(StateChangeEvent event) {

     //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
      //          (FacesMessage.SEVERITY_INFO, "EVENT ZMIANA ZOOM_LEVEL  "+event.getZoomLevel(), ""));


       // if(event.getZoomLevel()<3){
       //     this.zoom = 3;
       // }

       // this.zoom = event.getZoomLevel();
       // this.centerPosition = ""+event.getCenter().getLat()+", "+event.getCenter().getLng()+"";
        
      /*  if(this.zoom != event.getZoomLevel()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
            (FacesMessage.SEVERITY_INFO, "EVENT ZMIANA ZOOM_LEVEL  ", ""));              
        }
        else{
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
           // (FacesMessage.SEVERITY_INFO, "EVENT ZMIANA CENTER_POSITION ", ""));
        }*/
    }
    
    public void onPointSelect(PointSelectEvent event) {
      
        showMenu = true;
        
     //   LatLng latlng = event.getLatLng();
     //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
      //  (FacesMessage.SEVERITY_INFO, "KLIKNIETO W MAPE"," info dodatkowe"));
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
 
        System.out.println("doPOST");
        // 1. wyciagam JSON'a z requestu
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
       
        String jsonText = "";
        
        if(bufferedReader != null){
            jsonText = bufferedReader.readLine();
   
        }

        //  Set response type to JSON
        response.setContentType("text/plain");    // Not required
        response.getWriter().write("response response");

        System.out.println("KONIEC doPOST");
    }

    public String getOverlayType() {
        return overlayType;
    }

    public void setOverlayType(String overlayType) {
        this.overlayType = overlayType;
    }

    public void openCloseMnu(){
        showMenu = !showMenu;
    }

    public MapModel getModel() {
        return model;
    }

    public void setModel(MapModel model) {
        this.model = model;
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

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public String getMarkertype() {
        return markertype;
    }

    public void setMarkertype(String markertype) {
        this.markertype = markertype;
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public int getSizeObszar() {
        return sizeObszar;
    }

    public void setSizeObszar(int sizeObszar) {
        this.sizeObszar = sizeObszar;
    }

    public String getGeocodingSearch() {
        return GeocodingSearch;
    }

    public void setGeocodingSearch(String GeocodingSearch) {
        this.GeocodingSearch = GeocodingSearch;
    }

    public String getObszarOpis() {
        return obszarOpis;
    }

    public void setObszarOpis(String obszarOpis) {
        this.obszarOpis = obszarOpis;
    }

    public List<String> getTypyPunktow() {
        return typyPunktow;
    }

    public void setTypyPunktow(List<String> typyPunktow) {
        this.typyPunktow = typyPunktow;
    }



}
