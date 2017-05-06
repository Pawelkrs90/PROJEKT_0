
import Pojo._Ogloszenie;
import Pojo._Trip;
import Pojo._User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.json.JSONArray;
import org.json.JSONObject;

@ManagedBean
@RequestScoped
public class gmapMapaPrzypietych  implements Serializable{
    
    private String centerPosition ;
    private int zoom ;
    private String mapType;
    private OgloszenieDao dao;
    private String JsonData;
    private ArrayList<_Ogloszenie> Lista;
    
    public gmapMapaPrzypietych(){
        
        this.zoom = 10;
        this.centerPosition = "51.246058159, 22.541917562";
        this.mapType = "roadmap";   
        
        dao = new OgloszenieDao();
        Lista = (ArrayList<_Ogloszenie>) dao.getList();
        
        JSONArray jasonArray = new JSONArray(Lista);
        
        JsonData = jasonArray.toString();
        
        System.out.println("dane z bazy: "+JsonData);
        
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

    public String getJsonData() {
        return JsonData;
    }

    public void setJsonData(String JsonData) {
        this.JsonData = JsonData;
    }


}
