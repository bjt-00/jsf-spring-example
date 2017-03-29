package com.bitguiders.service.rest.client;

import static com.bitguiders.util.ApplicationConstants.REST_HOST;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bitguiders.dataaccess.orm.User;

@Named
@Stateless
public class UserClient {
	protected Client client;
	private static final Logger logger =Logger.getLogger(UserClient.class.getName());
	@PostConstruct
	private void init() {
	client = ClientBuilder.newClient();
	}
	@PreDestroy
	private void clean() {
	client.close();
	}
	
	public String createCustomer(User user) {
		if (user == null) {
		logger.log(Level.WARN, "customer is null.");
		return "userForm";
		}
		String navigation;
		Response response =
		client.target(REST_HOST+"user/create")
		.request(MediaType.APPLICATION_XML)
		.post(Entity.entity(user, MediaType.APPLICATION_XML),
		Response.class);
		if (response.getStatus() == Status.CREATED.getStatusCode()) {
		navigation = "userList";
		} else {
		logger.log(Level.WARN, "couldn''t create customer with ");// +
		//"id {0}. Status returned was {1}",
		//new Object[]{user.getId(), response.getStatus()});
		navigation = "userForm";
		}
		return navigation;
		}


	public List<User> getList() {
		List<User> user =
		client.target(REST_HOST)
		.path("user/list.do")
		.request(MediaType.APPLICATION_JSON)
		.get(new GenericType<List<User>>() {});
		
		return user;
		}

public void add(User orm) {
	// TODO Auto-generated method stub
	
}
public void update(User orm) {
	// TODO Auto-generated method stub
	
}
public void delete(User orm) {
	// TODO Auto-generated method stub
	
}
/*	public String getJSONList() {

String json = client.target("http://bitguiders.com/rest/ttts/?s=qb&a=list")
        .request(MediaType.APPLICATION_JSON)
        .get(String.class);
return json;
}*/

public static void main(String arg[]){
UserClient c = new UserClient();
c.init();
//System.out.println(c.getJSONList());
List<User> u = c.getList();
for(User user:u){
	System.out.println(user.getName());
}
c.clean();
}
}
