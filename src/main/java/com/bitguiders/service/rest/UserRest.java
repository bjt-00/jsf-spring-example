package com.bitguiders.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitguiders.dataaccess.orm.User;
import com.bitguiders.service.user.UserService;


@Controller
@RequestMapping(value="/user")
public class UserRest {
	
	@Autowired
	UserService service;
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET ,produces="application/json")
	public @ResponseBody List<User> getList(){
		return service.getList();
	}
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET ,produces="application/json")
	public @ResponseBody User getById(@PathVariable(value="id", required=true) int id){
		return service.getById(id);
	}

	/*	@RequestMapping(value="/list2/{id}", method=RequestMethod.GET )
	public @ResponseBody List<QuestionORM> getList2(@PathVariable(value="id", required=false) int id){
		return client.getListByQuestionBundleId(id);
	}
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET , produces="application/json")
	public @ResponseBody QuestionORM getOne(@PathVariable(value="id", required=false) int id){
		return (QuestionORM) client.getById(id);
	}
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public @ResponseBody String add(){
		QuestionORM qb = new QuestionORM();
		qb.setQuestionBundleId(2);
		qb.setQuestion("Test Question");
		qb.setType("TF");
		qb.setFals(false);
		qb.setTru(true);
		//qb.setOptions("A,B");
		return client.add(qb);
	}
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public @ResponseBody String update(@PathVariable(value="id", required=false) int id,HttpServletRequest request){
		QuestionORM qb = client.getById(id);
		qb.setQuestion("-"+qb.getQuestion());
		qb.setTru(true);
		return client.update(qb,request);
	}
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable(value="id", required=false) int id){
		return client.delete(id);
	}
*/	

}
