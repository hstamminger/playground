package info.selfhost.stammingerit.playground.webapptest.web.page;

import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import info.selfhost.stammingerit.playground.webapptest.entities.User;
import info.selfhost.stammingerit.playground.webapptest.service.UserService;

@SuppressWarnings("serial")
@Configurable
public class SetupPage extends WebPage {
    @Autowired
    private UserService userService;

	public SetupPage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if(RuntimeConfigurationType.DEVELOPMENT.equals(Application.get().getConfigurationType())) {
			try {
		        User testUser = new User();
		        testUser.setName("testuser");
		        testUser.setPassword("1234");
		        userService.create(testUser);
			}
			//NOSONAR this is just a setup convenience-Page
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
