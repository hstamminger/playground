package info.selfhost.stammingerit.playground.webapptest.web.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@ApplicationScoped
public class UserManagedBean {
	UserService userService = new UserService();

	private String username;
	private String password;
	private String searchUserName;
	private Collection<User> searchUsersResults = userService.getAllUsers();
	private User selectedUser;

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
//		return searchUsersResults;
		System.out.println("getSearchUsersResults");
		return new ArrayList<>(userService.getAllUsers());
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

	public String login() {
		if ("test".equalsIgnoreCase(getUsername()) && "test".equals(getPassword())) {
			return "home";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("username", new FacesMessage("Invalid UserName and Password"));
			return "login";
		}
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
