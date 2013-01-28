package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.UsuarioSistemaDao;
import br.com.jave.modelo.UsuarioSistema;
import br.com.jave.util.FacesMessageUtil;

@ManagedBean
@RequestScoped
@Controller
public class UsuarioSistemaMB implements Serializable {

	private static final long serialVersionUID = 1L;	
	private UsuarioSistemaDao usuarioSistemaDao;
	private List<UsuarioSistema> usuariosSistemaListagem;
	private UsuarioSistema usuarioSistema;
	
	public UsuarioSistemaMB(){}
	
	@Autowired
	public UsuarioSistemaMB(UsuarioSistemaDao usuarioSistemaDao){
		this.usuarioSistemaDao = usuarioSistemaDao;
	}
	
	public List<UsuarioSistema> listarUsuarios(){
		try {
			usuariosSistemaListagem = usuarioSistemaDao.listarTodos();
		} catch (Exception e) {
			FacesMessageUtil.erro("Erro ao Listar os clientes.", e.getMessage());
			e.printStackTrace();
		}
		return usuariosSistemaListagem;
	}

	public List<UsuarioSistema> getUsuariosSistemaListagem() {
		return usuariosSistemaListagem;
	}

	public void setUsuariosSistemaListagem(
			List<UsuarioSistema> usuariosSistemaListagem) {
		this.usuariosSistemaListagem = usuariosSistemaListagem;
	}

	public UsuarioSistema getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

}
