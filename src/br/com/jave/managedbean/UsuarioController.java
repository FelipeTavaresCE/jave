package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.jave.modelo.UsuarioSistema;

@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioSistema usuario;
	private List<UsuarioSistema> usuarios;

	public UsuarioController() {
		try {
			usuario = new UsuarioSistema();
			SecurityContext context = SecurityContextHolder.getContext();
			if (context instanceof SecurityContext) {
				Authentication authentication = context.getAuthentication();
				if (authentication instanceof Authentication) {
					usuario.setLogin(((User) authentication.getPrincipal())
							.getUsername());
				} else {
					usuario = null;
				}
			} else {
				usuario = null;
			}
		} catch (Exception e) {
		}
	}

	public UsuarioSistema getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSistema usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioSistema> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioSistema> usuarios) {
		this.usuarios = usuarios;
	}

	// omitido getters and setters

}
