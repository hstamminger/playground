package info.selfhost.stammingerit.playground.webapptest.web.jsf;


import info.selfhost.stammingerit.playground.webapptest.entities.User;
import info.selfhost.stammingerit.playground.webapptest.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Named
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class UserSearchResultModel extends LazyDataModel<User> implements SelectableDataModel<User> {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;
	
	private String usernamePattern;
	
	@Override
	public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		final SortMeta sortMeta = new SortMeta();
		sortMeta.setSortField(sortField);
		sortMeta.setSortOrder(sortOrder);
		
		return load(first, pageSize, Collections.singletonList(sortMeta), filters);
	}
	
	
	@Override
	public List<User> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, String> filters) {
		final List<User> result;
		if (usernamePattern == null || usernamePattern.trim().isEmpty()) {
			result = userService.findAllUsers();
			
		} else {
			result = userService.findUsersByUsernamePattern(usernamePattern);
		}
		final List<User> allUsers = userService.findAllUsers();
		return allUsers.subList(first, Math.min(first + pageSize, allUsers.size()));
	}
	
	
	@Override
	public Object getRowKey(User user) {
		return user.getId();
	}

	@Override
	public User getRowData(String rowKey) {
		return userService.findUserById(rowKey);
	}


	public String getUsernamePattern() {
		return usernamePattern;
	}


	public void setUsernamePattern(String usernamePattern) {
		this.usernamePattern = usernamePattern;
	}

}
