package br.com.jave.fachada;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.jave.dao.GenericDao;
import br.com.jave.dao.UsuarioSistemaDaoImpl;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.UsuarioSistema;

public class UsuarioSistemaFachadaImpl implements Fachada<UsuarioSistema>{

	GenericDao<UsuarioSistema> usuarioSistemaDao = new UsuarioSistemaDaoImpl();
	UsuarioSistemaDaoImpl usuarioSistemaDaoImpl = new UsuarioSistemaDaoImpl();
	
	@Override
	public void gravar(UsuarioSistema usuarioSistema) throws Exception {
		usuarioSistemaDao.gravar(usuarioSistema);
	}

	@Override
	public void excluir(UsuarioSistema usuarioSistema) throws Exception, ExclusaoNaoPermitidaException {
		usuarioSistemaDao.excluir(usuarioSistema);
	}

	@Override
	public List<UsuarioSistema> listarTodos() throws Exception {
		return usuarioSistemaDao.listarTodos();
	}

	@Override
	public UsuarioSistema pesquisarPorId(Long id) throws Exception, NoResultException {
		return usuarioSistemaDao.pesquisarPorId(id);
	}
	
	public Boolean validarUsuario(String login, String senha){
		return usuarioSistemaDaoImpl.validarUsuario(login, senha);
	}

}
