
package app.exceptions;

public class LoginNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -694354952032299587L;
	
	private String login;

	public LoginNotFoundException(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}

}
