package com.bitguiders.web.controller.user;

import static com.bitguiders.util.ApplicationConstants.REST_CLIENT_FOR_USER;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import com.bitguiders.dataaccess.orm.User;
import com.bitguiders.jsf.JSFBeanInterface;
import com.bitguiders.jsf.JSFBeanSupport;
import com.bitguiders.jsf.Navigate;
import com.bitguiders.jsf.Navigate.DOMAIN;
import com.bitguiders.service.rest.client.UserClient;

@ManagedBean(name="userBean")
@SessionScoped
@Navigate(domain=DOMAIN.USER)
public class UserBackingBean extends JSFBeanSupport<User> implements JSFBeanInterface<User>  {
private static final Logger logger = Logger.getLogger(UserBackingBean.class);


	@Resource(name=REST_CLIENT_FOR_USER)
	UserClient client;


	
	User user;
	
	public UserBackingBean(){}
	
	public void delete(){
		
		if(client.delete(getModel())){
			setMessage(getModel().getName()+" "+getModel().getRole()+" is deleted successfully");
		};
		
	}
	 public void update(){
		 client.update(getModel());
		setMessage(getModel().getName()+" updated successfully");
	}
	
	public void add(){
			client.add(getModel());
			setMessage(getModel().getName()+" added successfully");
	}
	@Override
	public List<User> getList() {
		//return service.getList();
		return client.getList();
	}

	public void roleChanged(ValueChangeEvent e){
		user = getModel();
		setMessage("Role changed "+(null!=user && user.getRole()!=null?"from "+user.getRole():"")+" to "+e.getNewValue());
		FacesContext.getCurrentInstance().renderResponse();
	}

  public void searchAction(ActionEvent e){
	  setInfo("Searching "+e.getComponent().getId());
  }
  public String action(){
	  setInfo("Link action is called");
	  FacesContext.getCurrentInstance().renderResponse();
	  return "index";
  }
	public void nameChangeListener(ValueChangeEvent e){
		setInfo(e.getNewValue()+" is not registered yet");
		/*user = getModel();
		if(client.getByName((String)e.getNewValue())!=null){
		setError(e.getNewValue()+" Already Registered, Please chose other one ");
		}else{
			setInfo(e.getNewValue()+" is not registered yet");
		}*/
		FacesContext.getCurrentInstance().renderResponse();
	}

	public void ajaxListener(AjaxBehaviorEvent event) {
	    //dosomething;
		setInfo("ajax cal received"+event.getPhaseId());
		logger.info("ajax call received"+event.getPhaseId());
	}
}
