package com.bitguiders.jsf;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


/**
 * @author abdulkareem
 *
 */
public abstract class JSFBeanSupport<Model> extends JSFBeanValidator {
	private static final Logger logger = Logger.getLogger(JSFBeanSupport.class);
	public Model model;
	private boolean isCreateAction;
	private boolean isDeleteAction;
	private boolean isListAction;
	
	private boolean isCancelAction;
	private boolean isSaveAction;
	private boolean isUpdateAction;
	private boolean isEditAction;
	private boolean isDisabled;
	private String currentAction;
	private boolean isDeleteConfirmedAction;
	private boolean isResetAction;
	
	private String pageTitle;
	private Integer id;


	public JSFBeanSupport(){
		isCreateAction=true;
		isCancelAction=true;
		isUpdateAction=true;
		isDeleteAction=true;
		isDeleteConfirmedAction=true;
		isSaveAction = true;
		this.isEditAction = true;
		isDisabled = false;
	}
	

	public boolean isCreateAction() {
		return isCreateAction;
	}

	public void setCreateAction() {
		this.isCreateAction = true;
		this.isCancelAction = true;
		this.isSaveAction = true;
		this.isDisabled = false;
		this.isResetAction = true;
		
	}

	public boolean isDeleteAction() {
		return isDeleteAction;//getStyle(isDeleteAction);
	}

	public void setDeleteAction() {
		this.isDeleteAction = true;
		this.isDeleteConfirmedAction = true;
		this.isCancelAction = true;
		this.isDisabled = true;
	}

	public boolean isDeleteConfirmedAction() {
		return isDeleteConfirmedAction;//getStyle(isDeleteConfirmedAction);
	}

	public void setDeleteConfirmedAction() {
		this.isDeleteConfirmedAction = true;
		this.isCancelAction = true;
		setViewAction();
	}

	public String getPageTitle(){
		return this.pageTitle;
	}
	public void setViewAction() {
		this.isCancelAction = true;
		this.isDeleteAction = true;
		this.isDisabled = true;
		this.isEditAction = true;
	}

	public void setEditAction() {
		this.isEditAction = true;
		this.isUpdateAction = true;
		this.isCancelAction = true;
		this.isDisabled = false;
		this.isResetAction = true;
	}
	public boolean isEditAction(){
		return this.isEditAction;
	}
	public boolean isDisabled() {
		return isDisabled;
	}
	public void setDisabled(boolean isDisabled) {
		 this.isDisabled = isDisabled;
	}


	public String getCurrentAction() {
		return (currentAction!=null?currentAction:"");
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}


	//create new instance of model by using java reflection
	public void resetModel(){
		try {
			Class cls = Class.forName(this.model.getClass().getName());
			Object arglist[] = new Object[0];
			for(Constructor c:cls.getConstructors()){
				//logger.info(c.getName()+" "+c.getParameterCount()+" "+c);
				if(c.getParameterCount()==0){
					this.model = (Model) cls.getConstructors()[0].newInstance(arglist);
					break;
				}
			}
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			//e.printStackTrace();
			//setError(e.getMessage());
		}
	}

	public String performAction(JSFBeanInterface bean,WebConstants.ACTION action,Model model){
		if(null!=model){
			setModel(model);
			}
		setDomain(bean);
		setCurrentAction(action);
		
		switch(action){
		case DELETE:
			setWarning("Do you really want to delete selected record..?");
		break;
		case DELETE_CONFIRMED:
			bean.delete();
		break;
		case CREATE:
			resetModel();
		break;
		case EDIT:
		break;
		case SAVE:
			bean.add();
		break;
		case UPDATE:
			bean.update();
		break;
		
		}
		return getView();
	}
	private void setCurrentAction(WebConstants.ACTION currentAction){
		reset();
		this.currentAction = currentAction.getAction();
		this.pageTitle = currentAction.getPageTitle();

		switch(currentAction)
		{
		case CREATE:
			setView(domain.getFormViewName());
			setCreateAction();
			break;
		case VIEW:
			setView(domain.getListViewName());
			setViewAction();
			break;
		case EDIT:
			setView(domain.getFormViewName());
			setEditAction();
			break;
		case UPDATE:
			setView(domain.getListViewName());
			setUpdateAction();
			break;
		case CANCEL:
			setView(domain.getListViewName());
			setViewAction();
			break;
		case DELETE_CONFIRMED:
			setView(domain.getListViewName());
			setDeleteConfirmedAction();
			break;
		case SAVE:
			setView(domain.getListViewName());
			setSaveAction();
			break;
		case DELETE:
			setView(domain.getFormViewName());
			setDeleteAction();
			break;
		}
	}

	public void reset(){
		  isCreateAction = false;
		  isDeleteAction = false;
		  isListAction	 = false;
		  isSaveAction = false;
		  isUpdateAction = false;
		  this.isDeleteConfirmedAction = false;
		  isDisabled = true;
		  isResetAction = false;
		  currentAction="";
		  this.isEditAction = false;
	}

	private String view;
	private Navigate.DOMAIN  domain;
	public void setView(String viewName) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		request.getSession().setAttribute("viewName", domain.getDomain()+"/"+viewName);
		
		this.view = viewName;
	}
	public String getView(){
		return this.view;
	}
	public Navigate.DOMAIN getDomain(){
		return this.domain;
	}
	public void setDomain(JSFBeanInterface bean){
		//JSFBeanInterface bean
		if(bean.getClass().isAnnotationPresent(Navigate.class)){
			Annotation annocation = bean.getClass().getAnnotation(Navigate.class);
			Navigate navigate = (Navigate)annocation;
			this.domain = navigate.domain();
		}
	}

	public String getSelectedApplication() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String app = request.getParameter("app");
		app = (app != null ? app.trim() : "");
		return app;
	}
	
	public String getParameter(String x) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String param = request.getParameter(x);
		param = (param != null ? param.trim() : "");
		
		
		return param;
	}

	public boolean isListAction() {
		return isListAction;
	}

	public void setListAction(boolean isListAction) {
		this.isListAction = isListAction;
	}


	public boolean isCancelAction() {
		return isCancelAction;
	}

	public void setCancelAction(boolean isCancelAction) {
		this.isCancelAction = isCancelAction;
	}

	public void setUpdateAction() {
		this.isCancelAction = true;
		this.isUpdateAction = true;
		setViewAction();
	}
	public boolean isUpdateAction() {
		return isUpdateAction;
	}

	public boolean isSaveAction() {
		return isSaveAction;
	}

	public void setSaveAction() {
		this.isSaveAction = true;
		this.isCancelAction = true;
		setViewAction();
	}
	public boolean isResetAction() {
		return isResetAction;
	}
	public void setResetAction(boolean isReset) {
		this.isResetAction = isReset;
	}
	public int getId() {
		return (id!=null?id.intValue():0);
	}
	public void setId(int id){
		this.id=id;
	}
	

}
