package com.bitguiders.jsf;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="action")
public class WebConstants {

	//ACTIONS
	public enum ACTION{
		CREATE("Create"),
		VIEW("View"),
		EDIT("Edit"),
		UPDATE("Update"),
		DELETE("Delete"),
		DELETE_CONFIRMED("Delete Confirmed"),
		CANCEL("Cancel"),
		SAVE("Save");
		private String action;
		ACTION(String action){
			this.action = action;
		}
		public String getAction(){
			return this.action;
		}
		public String getPageTitle(){
			return action;
		}		
	}
	public ACTION getCreate(){
		return ACTION.CREATE;
	}
	public ACTION getDelete(){
		return ACTION.DELETE;
	}
	public ACTION getDeleteConfirmed(){
		return ACTION.DELETE_CONFIRMED;
	}
	public ACTION getEdit(){
		return ACTION.EDIT;
	}
	public ACTION getSave(){
		return ACTION.SAVE;
	}
	public ACTION getCancel(){
		return ACTION.CANCEL;
	}
	public ACTION getUpdate(){
		return ACTION.UPDATE;
	}
	
}
