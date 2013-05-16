package info.selfhost.stammingerit.playground.webapptest.web.jsf;

import info.selfhost.stammingerit.playground.webapptest.entities.User;
import info.selfhost.stammingerit.playground.webapptest.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.SelectableDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

//@Configurable
//@ManagedBean
//@ApplicationScoped

@Named
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class UserManagedBean {
//	@Autowired
//	private UserService userService;// = new UserService();

	@Inject
	private UserService userService;
	
	@Inject
	private UserSearchResultModel searchUserResults;// = userService.getAllUsers();

	private String searchUserName;
	private User selectedUser;

	public User getSelectedUser() {
		if (selectedUser == null) {
			selectedUser = new User();
		}
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public SelectableDataModel<User> getSearchUserResults() {
		System.out.println("getSearchUsersResults");
		return searchUserResults;
//		return new ArrayList<>(userService.getAllUsers());
	}

//	public void setSearchUserResults(List<User> searchUsersResults) {
//		this.searchUserResults = searchUsersResults;
//	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUser) {
		this.searchUserName = searchUser;
	}

	public String searchUser() {
		searchUserResults.setUsernamePattern(searchUserName);
//		System.out.println(searchUserResults);
		return "home";
	}

	public String updateUser() {
		userService.save(this.selectedUser);
		return "home";
	}

	public void onUserSelect(SelectEvent event) {
		System.out.println("onUserSelect() - event: " + event + " selectedUser = " + (User) event.getObject());
		selectedUser = (User) event.getObject();
	}

	public void onUserUnselect(UnselectEvent event) {
		System.out.println("onUserUnselect() - event: " + event);
		selectedUser = null;
	}
}
