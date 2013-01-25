package br.com.jave.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessageUtil {
	
	public static void mensagem(String messagem){
		FacesMessage facesMessage = new FacesMessage(messagem);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void aviso(String messagem){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, messagem, null);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		FacesContext.getCurrentInstance().renderResponse();
	}
	
	public static void erro(String messagem){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, messagem, null);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	//Mensagens com detalhes
	public static void mensagem(String messagem, String detalhae){
		FacesMessage facesMessage = new FacesMessage(messagem, detalhae);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void aviso(String messagem, String detalhae){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, messagem, detalhae);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		FacesContext.getCurrentInstance().renderResponse();
	}
	
	public static void erro(String messagem, String detalhae){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, messagem, detalhae);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

}