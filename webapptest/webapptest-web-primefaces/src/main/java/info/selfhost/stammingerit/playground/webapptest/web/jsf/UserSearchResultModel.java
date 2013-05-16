package info.selfhost.stammingerit.playground.webapptest.web.jsf;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class UserModel extends LazyDataModel<User> implements SelectableDataModel<User> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	@Override
	public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		final SortMeta sortMeta = new SortMeta();
		sortMeta.setSortField(sortField);
		sortMeta.setSortOrder(sortOrder);
		
		return load(first, pageSize, Collections.singletonList(sortMeta), filters);
	}
	
	
	@Override
	public List<User> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, String> filters) {
		final List<User> allUsers = userService.getAllUsers();
		return allUsers.subList(first, Math.min(first + pageSize, allUsers.size()));
	}
	
	
	@Override
	public Object getRowKey(User user) {
		return user.getUserId();
	}

	@Override
	public User getRowData(String rowKey) {
		return userService.getUser(Integer.valueOf(rowKey));
	}

}
