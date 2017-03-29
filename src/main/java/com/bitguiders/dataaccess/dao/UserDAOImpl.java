package com.bitguiders.dataaccess.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bitguiders.dataaccess.orm.User;



@Repository
public class UserDAOImpl implements UserDAO {
	List<User> usersList;
	public UserDAOImpl(){
	}
	@Override
	public void add(User user) {
		usersList.add(user);
	}
	@Override
	public void update(User user) {
		//TODO this is a temp for demo purpose only
		User tempUser = getById(user.getId());
		usersList.remove(tempUser);
		usersList.add(user);
	}
	@Override
	public void delete(User user) {
		usersList.remove(user);
	}
	@Override
	public List<User> getList() {
		if(usersList==null){
			usersList = new ArrayList<User>();
			usersList.add(new User(usersList.size()+1,"-Abdul","abdul","Admin","111-11-1111"));
			usersList.add(new User(usersList.size()+1,"-Rakesh","rakesh","User","111-11-1111"));
			usersList.add(new User(usersList.size()+1,"-Waqas","waqas","Guest","111-11-1111"));
			usersList.add(new User(usersList.size()+1,"-Sohail","sohail","Guest","111-11-1111"));
		}
		// TODO Auto-generated method stub
		return usersList;
	}
	@Override
	public User getByName(String name) {
		User user = (User)getList().stream().filter(u-> name.equals(u.getName())).findAny().get();
		return user;
	}
	@Override
	public User getById(int id) {
		User user = getList().stream().filter(u-> id == u.getId()).findAny().get();
		return user;
	}
}
