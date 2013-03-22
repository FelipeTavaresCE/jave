package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.UsuarioSistema;
import br.com.jave.util.Criptografia;

@Repository
@Transactional
public class UsuarioSistemaDaoImpl implements UsuarioSistemaDao{

	@PersistenceContext
	EntityManager entityManager;
	//List<UsuarioSistema> usuarios;
	
	@Override
	public void gravar(UsuarioSistema usuarioSistema) throws Exception {
		entityManager.merge(usuarioSistema);
	}

	@Override
	public void excluir(UsuarioSistema entidade) throws Exception,
			ExclusaoNaoPermitidaException {
		throw new ExclusaoNaoPermitidaException("Não é possível excluir um usuário do sistema.");		
	}

	@Override
	public List<UsuarioSistema> listarTodos() throws Exception {
		TypedQuery<UsuarioSistema> query = entityManager.createNamedQuery("usuarioSistemaListarTodos", UsuarioSistema.class);
		return query.getResultList();
	}

	@Override
	public UsuarioSistema pesquisarPorId(Long id) throws Exception,
			NoResultException {
		return entityManager.find(UsuarioSistema.class, id);
	}
	
	@SuppressWarnings("unused")
	public Boolean validarUsuario(String login, String senha){
		Boolean autorizado = false;		
		try {
			UsuarioSistema usuarioValidacao = null;
			TypedQuery<UsuarioSistema> query = entityManager.createNamedQuery("usuarioSistemaValidarUsuario", UsuarioSistema.class);
			query.setParameter("login", login);
			query.setParameter("senha", Criptografia.md5(senha));
			usuarioValidacao = query.getSingleResult();
			autorizado = true;
		} catch (NoResultException e) {
			autorizado = false;
		}
		return autorizado;
	}
}
