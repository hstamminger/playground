package info.selfhost.stammingerit.playground.webapptest.web;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import info.selfhost.stammingerit.playground.webapptest.entities.User;
import info.selfhost.stammingerit.playground.webapptest.service.UserService;

@SuppressWarnings("serial")
@Configurable
public class UserAuthenticatedWebSession extends AuthenticatedWebSession {
    private User user = null;
    
    @Autowired
    UserService userService;
    
    public UserAuthenticatedWebSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        this.user = userService.authenticateAndGet(username, password);
        if(user != null) {
            WebSession.get().replaceSession();
            return true;
        }
        return false;
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        if (user != null) {
            roles.add(Roles.USER);
        }
        return roles;
    }

}
