package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Email;

@Repository
@Transactional
public class EmailDaoImpl implements GenericDao<Email>{
	
	@PersistenceContext
	EntityManager entityManager;

	
	@Override
	public void gravar(Email entidade) throws Exception {
		entityManager.persist(entidade);
		entityManager.flush();
	}

	@Override
	public void excluir(Email entidade) throws Exception, ExclusaoNaoPermitidaException {
		entityManager.remove(entidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Email> listarTodos() throws Exception {
		Query query = entityManager.createNamedQuery("emailListarTodos");
		return query.getResultList();
	}

	@Override
	public Email pesquisarPorId(Long id) throws Exception, NoResultException {
		return entityManager.find(Email.class, id);
	}

}
