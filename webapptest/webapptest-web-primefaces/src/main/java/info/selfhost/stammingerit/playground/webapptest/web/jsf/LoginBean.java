package info.selfhost.stammingerit.playground.webapptest.web.jsf;

import info.selfhost.stammingerit.playground.webapptest.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;


//@ManagedBean
//@SessionScoped

@Named
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class LoginBean {

	@Inject
	private LoginForm loginForm;
	
	@Inject
	private UserService userService;
	
	public String login() {
		userService.authenticateAndGet(loginForm.getUsername(), loginForm.getPassword());
		
		if ("test".equalsIgnoreCase(loginForm.getUsername()) && "test".equals(loginForm.getPassword())) {
			return "home";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("username", new FacesMessage("Invalid UserName and Password"));
			return "login";
		}
	}

	@Named
	@Scope(WebApplicationContext.SCOPE_REQUEST)
	public static class LoginForm {
		private String username;
		private String password;
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
	
}