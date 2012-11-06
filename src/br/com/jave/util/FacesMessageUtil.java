package br.com.jave.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessageUtil {
	
	public static void mensagem(String messagem){
		FacesMessage facesMessage = new FacesMessage(messagem, messagem);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void aviso(String messagem){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, messagem, messagem);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void erro(String messagem){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, messagem, messagem);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

}