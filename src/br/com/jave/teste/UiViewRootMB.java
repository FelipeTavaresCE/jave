package br.com.jave.teste;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class UiViewRootMB {

	private String nome;
	private String cpf;
	
	public void testar(){
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();

		System.out.println("GetFamily: " + viewRoot.getFamily());
		System.out.println("getId: " + viewRoot.getId());
		System.out.println("getLocaly: " + viewRoot.getLocale());
		System.out.println("isRendered: " + viewRoot.isRendered());
		System.out.println("getViewId: " + viewRoot.getViewId());
		System.out.println(": " + viewRoot.COMPONENT_FAMILY);
		System.out.println(": " + viewRoot.COMPONENT_TYPE);
		System.out.println(": " + viewRoot.COMPOSITE_COMPONENT_TYPE_KEY);
		System.out.println(": " + viewRoot.CURRENT_COMPONENT);
		System.out.println(": " + viewRoot.UNIQUE_ID_PREFIX);
		
		
		System.out.println(nome);
		System.out.println(cpf);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
}
