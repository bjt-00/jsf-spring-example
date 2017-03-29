package com.bitguiders.dataaccess.dao;

import com.bitguiders.dataaccess.orm.User;

public interface UserDAO extends GenericDAO<User> {
	public User getByName(String name);
	public User getById(int id);
}
