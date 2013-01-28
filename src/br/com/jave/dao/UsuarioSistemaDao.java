package br.com.jave.dao;

import br.com.jave.modelo.UsuarioSistema;

public interface UsuarioSistemaDao extends GenericDao<UsuarioSistema>{
	
	public Boolean validarUsuario(String login, String senha);

}
