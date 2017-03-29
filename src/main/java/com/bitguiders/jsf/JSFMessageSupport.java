package com.bitguiders.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

class JSFMessageSupport {
	private enum MESSAGE_TYPE{
		INFO("info"),
		SUCCESS("success"),
		WARNING("warning"),
		ERROR("danger");
		
		private String messageType;
		private MESSAGE_TYPE(String messageType){
			this.messageType = messageType;
		}
		private String getMessageType(){
			return this.messageType;
		}
	}
	private String message;
	private void setMessage(String messageType,String message){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message", message);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("messageType",messageType);
		this.message = message;
	}
	public void setMessage(String message){
		setMessage(MESSAGE_TYPE.SUCCESS.getMessageType(), message);
	}
	public String getMessage(){
		return this.message;
	}
	public FacesMessage getMessage(String message){
		return new FacesMessage(FacesMessage.SEVERITY_INFO,message,message);
	}

	public void setInfo(String message){
		setMessage(MESSAGE_TYPE.INFO.getMessageType(), message);
	}
	public void setWarning(String message){
		setMessage(MESSAGE_TYPE.WARNING.getMessageType(), message);
	}
	public void setError(String message){
		setMessage(MESSAGE_TYPE.ERROR.getMessageType(), message);
	}

}
