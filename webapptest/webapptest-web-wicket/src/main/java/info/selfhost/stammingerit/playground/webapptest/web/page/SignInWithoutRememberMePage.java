package info.selfhost.stammingerit.playground.webapptest.web.page;

import org.apache.wicket.authroles.authentication.pages.SignInPage;
import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@SuppressWarnings("serial")
public class SignInWithoutRememberMePage extends SignInPage {

	public SignInWithoutRememberMePage(PageParameters parameters) {
		//The mechanism to store username/password should use the same encryption as the UserService
		//until then we turn it off
		remove("signInPanel");
		add(new SignInPanel("signInPanel",false));
	}

}
