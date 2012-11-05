package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.java.modelo.Email;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;

public class EmailDaoImpl implements GenericDao<Email>{
	
	EntityManager entityManager;

	@Override
	public void gravar(Email entidade) throws Exception {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(entidade);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void excluir(Email entidade) throws Exception, ExclusaoNaoPermitidaException {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		Email emailMerge;
		entityManager.getTransaction().begin();
		emailMerge = entityManager.merge(entidade);
		entityManager.remove(emailMerge);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Email> listarTodos() throws Exception {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		Query query = entityManager.createNamedQuery("emailListarTodos");
		return query.getResultList();
	}

	@Override
	public Email pesquisarPorId(Long id) throws Exception, NoResultException {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		return entityManager.find(Email.class, id);
	}

}
