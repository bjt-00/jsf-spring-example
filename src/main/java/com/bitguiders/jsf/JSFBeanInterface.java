package com.bitguiders.jsf;

import java.util.List;

public abstract interface JSFBeanInterface<Model> {

	 void add();
	 void update();
	 void delete();
	 public List<Model> getList();
	 //public String actionListener(WebConstants.ACTION action,Model model);

}
