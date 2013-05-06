package info.selfhost.stammingerit.playground.webapptest.web.jsf;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModel extends LazyDataModel<User> implements SelectableDataModel<User> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	@Override
	public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		SortMeta sortMeta = new SortMeta();
		sortMeta.setSortField(sortField);
		sortMeta.setSortOrder(sortOrder);
		
		return load(first, pageSize, new ArrayList<SortMeta>(), filters);
	}
	
	
	@Override
	public List<User> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, String> filters) {
		List<User> allUsers = userService.getAllUsers();
		return allUsers.subList(first, Math.min(first + pageSize, allUsers.size()));
	}
	
	
	@Override
	public Object getRowKey(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getRowData(String rowKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
