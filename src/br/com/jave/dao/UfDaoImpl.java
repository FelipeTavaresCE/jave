package br.com.jave.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.java.modelo.Uf;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;

public class UfDaoImpl implements GenericDao<Uf>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EntityManager entityManager;
	
	@Override
	public void gravar(Uf uf) throws Exception {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(uf);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void excluir(Uf t) throws Exception , ExclusaoNaoPermitidaException{
		throw new ExclusaoNaoPermitidaException("Não é permitido excluir uma Unidade Federativa.");		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Uf> listarTodos() throws Exception {
		entityManager = new EntityManagerFabrica().obterEntityManager(); 
		Query query = entityManager.createNamedQuery("ufListarTodos");
		return query.getResultList();
	}

	@Override
	public Uf pesquisarPorId(Long id) throws Exception, NoResultException {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		return entityManager.find(Uf.class, id);
	}
}
