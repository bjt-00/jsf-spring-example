package com.bitguiders.service.user;

import com.bitguiders.dataaccess.orm.User;
import com.bitguiders.service.GenericService;

public interface UserService extends GenericService<User> {
	public User getByName(String name);
	public User getById(int id);
}
