package info.selfhost.stammingerit.playground.webapptest.web;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;

import info.selfhost.stammingerit.playground.webapptest.web.page.HomePage;
import info.selfhost.stammingerit.playground.webapptest.web.page.SetupPage;
import info.selfhost.stammingerit.playground.webapptest.web.page.SignInWithoutRememberMePage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see de.codepitbull.apps.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		getSecuritySettings().setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));
		if(RuntimeConfigurationType.DEVELOPMENT.equals(getConfigurationType())) {
			mountPage("/setup", SetupPage.class);
		}
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return UserAuthenticatedWebSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInWithoutRememberMePage.class;
	}
	
	
}
