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
	
	public boolean add(User user) {
		boolean status=false;
		if (user == null) {
		logger.log(Level.WARN, "User is null.");
		return status;
		}
		
		Response response =
		client.target(REST_HOST)
		.path("user/add.do")
		.request(MediaType.APPLICATION_JSON)
		.post(Entity.entity(user, MediaType.APPLICATION_JSON),
		Response.class);
		if (response.getStatus() == Status.CREATED.getStatusCode()) {
		status = true;
		} else {
		logger.log(Level.WARN, "couldn''t create User with ");// +
		//"id {0}. Status returned was {1}",
		//new Object[]{user.getId(), response.getStatus()});
		status = false;
		}
		return status;
		}


	public List<User> getList() {
		List<User> user =
		client.target(REST_HOST)
		.path("user/list.do")
		.request(MediaType.APPLICATION_JSON)
		.get(new GenericType<List<User>>() {});
		
		return user;
		}

		
	public boolean update(User user) {
		logger.info("------Updating user "+user.getName());
		boolean status=false;
		if (user == null) {
		logger.log(Level.WARN, "User is null.");
		return status;
		}
		
		status =
		client.target(REST_HOST)
		.path("user/update.do")
		.request(MediaType.APPLICATION_JSON)
		.post(Entity.entity(user, MediaType.APPLICATION_JSON),
		Boolean.class);
		if (!status) {
		logger.log(Level.WARN, "couldn''t update User with ");// +
		//"id {0}. Status returned was {1}",
		//new Object[]{user.getId(), response.getStatus()});
		status = false;
		}
		return status;
		}
public boolean delete(User orm) {
	boolean response =
	client.target(REST_HOST)
	.path("user/delete/"+orm.getId()+".do")
	.request(MediaType.APPLICATION_JSON)
	.get(Boolean.class);
	
	return response;
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
