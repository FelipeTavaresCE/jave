package br.com.jave.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;

import br.com.jave.util.UploadDeImagem;

@ManagedBean
@SessionScoped
public class LogomarcaUpload implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private DefaultStreamedContent fotoGerada;
	
	public void upload(FileUploadEvent event) {		
		
		this.fotoGerada = UploadDeImagem.gerarApresentacaoTela(event.getFile().getContents());		
		
        FacesMessage msg = new FacesMessage("Imagem carregada com sucesso!", null);  
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public DefaultStreamedContent getFotoGerada() {
		return fotoGerada;
	}

	public void setFotoGerada(DefaultStreamedContent fotoGerada) {
		this.fotoGerada = fotoGerada;
	}
	
}