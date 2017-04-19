/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.packages.controllers;

import app.packages.domain.User;
import app.packages.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/User")
public class UserController {
    
    private UserDaoService userDaoService;
    
    @Autowired(required=true)
    @Qualifier(value="userDaoService")
    public void setUserService(UserDaoService us){
    this.userDaoService = us;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody void create(@RequestBody User user) {

        
        userDaoService.addUser(user);

    }
    
    
    
    /*
    
    @Autowired

private CartService cartService;

@Autowired

private ProductService productService;

@RequestMapping(method = RequestMethod.POST)

public @ResponseBody Cart create(@RequestBody Cart cart) {

return cartService.create(cart);

}

@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)

public @ResponseBody Cart read(@PathVariable(value = "cartId") String

cartId) {

return cartService.read(cartId);

}

@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)

@ResponseStatus(value = HttpStatus.NO_CONTENT)

public void update(@PathVariable(value = "cartId") String cartId,

@RequestBody Cart cart) {

cartService.update(cartId, cart);

}

@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)

@ResponseStatus(value = HttpStatus.NO_CONTENT)

public void delete(@PathVariable(value = "cartId") String cartId) {

cartService.delete(cartId);

}

@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)

@ResponseStatus(value = HttpStatus.NO_CONTENT)
    
    public void addItem(@PathVariable String productId, HttpServletRequest

request) {

String sessionId = request.getSession(true).getId();

Cart cart = cartService.read(sessionId);

if(cart== null) {

cart = cartService.create(new Cart(sessionId));

}

Product product = productService.getProductById(productId);

if(product == null) {

throw new IllegalArgumentException(new

ProductNotFoundException(productId));

}

cart.addCartItem(new CartItem(product));

cartService.update(sessionId, cart);

}

@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)

@ResponseStatus(value = HttpStatus.NO_CONTENT)

public void removeItem(@PathVariable String productId, HttpServletRequest

request) {

String sessionId = request.getSession(true).getId();

Cart cart = cartService.read(sessionId);

if(cart== null) {

cart = cartService.create(new Cart(sessionId));

}

Product product = productService.getProductById(productId);

if(product == null) {

throw new IllegalArgumentException(new

ProductNotFoundException(productId));

}

cart.removeCartItem(new CartItem(product));

cartService.update(sessionId, cart);

}

@ExceptionHandler(IllegalArgumentException.class)

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Niepoprawne żądanie,

sprawdź przesyłane dane.")

public void handleClientErrors(Exception ex) { }

@ExceptionHandler(Exception.class)

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Wewnętrzny

błąd serwera.")

public void handleServerErrors(Exception ex) { }
    
    */
    
    
    
    
    
}
