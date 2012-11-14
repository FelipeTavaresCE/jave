package br.com.jave.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


@ManagedBean
@SessionScoped
public class UsuarioController {

    private br.com.java.modelo.User usuario;
    private List<br.com.java.modelo.User> usuarios;

    public UsuarioController() {
        try {
            usuario = new br.com.java.modelo.User();
            SecurityContext context = SecurityContextHolder.getContext();
            if (context instanceof SecurityContext) {
                Authentication authentication = context.getAuthentication();
                if (authentication instanceof Authentication) {
                    usuario.setUsername(((User) authentication.getPrincipal()).getUsername());
                } else {usuario = null;} 
            } else {usuario = null;} 
        } catch (Exception e) {
        }
    }

	public br.com.java.modelo.User getUsuario() {
		return usuario;
	}

	public void setUsuario(br.com.java.modelo.User usuario) {
		this.usuario = usuario;
	}

	public List<br.com.java.modelo.User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<br.com.java.modelo.User> usuarios) {
		this.usuarios = usuarios;
	}

//omitido getters and setters

}
