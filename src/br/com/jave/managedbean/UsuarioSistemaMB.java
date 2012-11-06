package br.com.jave.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.jave.fachada.UsuarioSistemaFachadaImpl;
import br.com.jave.util.FacesMessageUtil;

@ManagedBean
@RequestScoped
public class UsuarioSistemaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioSistemaFachadaImpl usuarioSistema = new UsuarioSistemaFachadaImpl();
	private String login;
	private String senha;

	public String validaUsuario(){
		String retorno = null;
		if(usuarioSistema.validarUsuario(login, senha) == true){
			retorno = "inicial";
		}else{
			FacesMessageUtil.erro("Dados de login incorretos");
			retorno = "";
		}
		return retorno;
	}	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsuarioSistemaFachadaImpl getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistemaFachadaImpl usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
