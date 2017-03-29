package com.bitguiders.jsf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface  Navigate {
	public enum DOMAIN{
		USER("user");
		private String domain;
		DOMAIN(String domain){
			this.domain = domain;
		}
		public String getDomain(){
			return this.domain;
		}
		public String getFormViewName(){
			return domain+"Form";
		}
		public String getListViewName(){
			return domain+"List";
		}
	}
	DOMAIN domain() default DOMAIN.USER;
}
