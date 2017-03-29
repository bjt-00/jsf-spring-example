package com.bitguiders.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

//@FacesValidator("com.bitguiders.validator.SSN")
//@FacesConverter(forClass = CreditCard.class) generic foall this class
/*
 * 
<h:inputText id="card" value="#{payment.card}" required="true">
<f:validator validatorId="com.bitguiders.validator.SSN"/>
</h:inputText>
 */
public class JSFBeanConverter  implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		//String separator = (String) component.getAttributes().get("separator");
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		String separator = (String) component.getAttributes().get("separator");
		return null;
	}


}
