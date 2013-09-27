package de.cimdata.kundenreg.jsfutil;

import  javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesHelper {
	
	public static void setMSG(String id, String msg){
		FacesMessage fm = new FacesMessage(msg);
		fm.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(id, fm);
	}

}
