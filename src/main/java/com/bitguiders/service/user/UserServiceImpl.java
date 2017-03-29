package com.bitguiders.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitguiders.dataaccess.dao.UserDAO;
import com.bitguiders.dataaccess.orm.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;

	@Override
	public void add(User user) {
		dao.add(user);
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

	@Override
	public void delete(User user) {
		dao.delete(user);
	}

	@Override
	public List<User> getList() {
		return dao.getList();
	}

	@Override
	public User getByName(String name) {
		return dao.getByName(name);
	}

	@Override
	public User getById(int id) {
		return dao.getById(id);
	}


}
