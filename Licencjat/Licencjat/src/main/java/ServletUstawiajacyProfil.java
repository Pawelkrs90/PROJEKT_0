
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/ServletUstawiajacyProfil"})
public class ServletUstawiajacyProfil extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
 
        System.out.println("doPOST");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String Text = "";
        
        if(bufferedReader != null){
            Text = bufferedReader.readLine();
        }
     
        System.out.println("dane:" + Text);

        //Set response 
        response.setContentType("text/plain");    // Not required
        response.getWriter().write("dane zwrotne");
        
        try{
            System.out.println("login: "+Text);
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserToView", Text);
        }
        catch(NullPointerException e ){
           System.out.println("Jest Null :(");
        }  
        catch(java.lang.NumberFormatException e){
            System.out.println("Number format Exception");
        }

        System.out.println("KONIEC doPOST");
    }


}
