
import Pojo._User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="profilViewController")
@RequestScoped
public class profilViewController implements Serializable  {

    private _User ViewUser;
    UserDao dao;
    
    public profilViewController(){
        
        String login=null;
        
        try{
            
            login = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserToView");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("UserToView");            
        }
        catch(NullPointerException e){
            
            System.out.println("null pointer exception");
        }
        
        if(login!=null){
            
            try{
                dao = new UserDao();
                ViewUser = dao.getByKey(login);
            }
            catch(NullPointerException e){
            
                System.out.println("null pointer exception w dao");
            }
        }
        
        
         
    }
    
    public String loadProfil(int id){
        
        System.out.println("... id: "+id);
        dao = new UserDao();
        ViewUser = dao.getById(id);
        
        return "profilView.xhtml";
    }

    public _User getViewUser() {
        return ViewUser;
    }

    public void setViewUser(_User ViewUser) {
        this.ViewUser = ViewUser;
    }
  
}
