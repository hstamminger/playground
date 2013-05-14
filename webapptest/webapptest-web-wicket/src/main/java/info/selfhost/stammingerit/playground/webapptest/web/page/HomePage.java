package info.selfhost.stammingerit.playground.webapptest.web.page;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@SuppressWarnings("serial")
@AuthorizeInstantiation(Roles.USER)
public class HomePage extends WebPage {

    public HomePage(final PageParameters parameters) {
        super(parameters);
        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
        add(new Label("loggedin", "You are in!"));
    }
}
