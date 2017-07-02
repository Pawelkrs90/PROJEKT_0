
package app.model.validators;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE }) 
@Retention(RUNTIME) 
@Constraint(validatedBy = UserLoginValidator.class) 
@Documented 
public @interface UserLogin {
    

    String message() default "{Validation.User.Login.OnlyOne}";   
    Class<?>[] groups() default {};   
    public abstract Class<? extends Payload>[] payload() default {}; 

}
