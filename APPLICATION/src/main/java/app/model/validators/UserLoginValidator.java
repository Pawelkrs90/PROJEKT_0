
package app.model.validators;

import app.exceptions.LoginNotFoundException;
import app.model.User;
import app.service.UserDaoService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext; 
import org.springframework.beans.factory.annotation.Autowired;
       
class UserLoginValidator implements ConstraintValidator<UserLogin, String>{

    @Autowired   
    private UserDaoService userDaoService;  
    
    @Override
    public void initialize(UserLogin constraintAnnotation) {   // Celowo pozostawione puste; w tym miejscu należy zainicjować adnotację ograniczającą     
        // do sensownych domyślnych wartości.   } 
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {     

        User user;
        try { 
            user = userDaoService.findByUserName(value);
                
        } catch (LoginNotFoundException e) {
                return true;
        }     
        if(user!= null) {
            return false;
        }
        return true;
    }
}