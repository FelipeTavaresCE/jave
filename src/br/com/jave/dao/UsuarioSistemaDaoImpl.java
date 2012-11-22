package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.UsuarioSistema;
import br.com.jave.util.Criptografia;

public class UsuarioSistemaDaoImpl implements GenericDao<UsuarioSistema>{

	EntityManager entityManager;
	List<UsuarioSistema> usuarios;
	
	@Override
	public void gravar(UsuarioSistema usuarioSistema) throws Exception {
		entityManager.persist(usuarioSistema);
		entityManager.flush();
	}

	@Override
	public void excluir(UsuarioSistema entidade) throws Exception,
			ExclusaoNaoPermitidaException {
		throw new ExclusaoNaoPermitidaException("Não é possível excluir um usuário do sistema.");		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioSistema> listarTodos() throws Exception {
		Query query = entityManager.createNamedQuery("usuarioSistemaListarTodos");
		usuarios = query.getResultList();
		return usuarios;
	}

	@Override
	public UsuarioSistema pesquisarPorId(Long id) throws Exception,
			NoResultException {
		return entityManager.find(UsuarioSistema.class, id);
	}
	
	@SuppressWarnings("finally")
	public Boolean validarUsuario(String login, String senha){
		Boolean autorizado = false;		
		try {
			@SuppressWarnings("unused")
			UsuarioSistema usuarioValidacao = null;
			Query query = entityManager.createNamedQuery("usuarioSistemaValidarUsuario");
			query.setParameter("login", login);
			query.setParameter("senha", Criptografia.md5(senha));
			usuarioValidacao = (UsuarioSistema)query.getSingleResult();
			autorizado = true;
		} catch (NoResultException e) {
			autorizado = false;
		}finally{
			return autorizado;
		}					
	}
}
