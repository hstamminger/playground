package info.selfhost.stammingerit.playground.webapptest.web.jsf;

import java.util.Collection;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@ManagedBean
@ApplicationScoped
public class UserManagedBean {
//	@Autowired
//	private UserService userService;// = new UserService();

	private String searchUserName;
	private Collection<User> searchUsersResults;// = userService.getAllUsers();
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

	public List<User> getSearchUsersResults() {
		System.out.println("getSearchUsersResults");
		return searchUsersResults;
//		return new ArrayList<>(userService.getAllUsers());
	}

	public void setSearchUsersResults(Collection<User> searchUsersResults) {
		this.searchUsersResults = searchUsersResults;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUser) {
		this.searchUserName = searchUser;
	}

	public String searchUser() {
		String username = (this.searchUserName == null) ? "" : this.searchUserName.trim();
		this.searchUsersResults = userService.searchUsers(username);
		System.out.println(searchUsersResults);
		return "home";
	}

	public String updateUser() {
		userService.update(this.selectedUser);
		return "home";
	}

	public void onUserSelect(SelectEvent event) {
		selectedUser = (User) event.getObject();
		System.out.println("selectedUser = " + selectedUser);
	}

	public void onUserUnselect(UnselectEvent event) {
		selectedUser = null;
	}
}
