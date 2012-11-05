package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.java.modelo.Telefone;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;

public class TelefoneDaoImpl implements GenericDao<Telefone>{

	private EntityManager entityManager;
	
	@Override
	public void gravar(Telefone telefone) throws Exception {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(telefone);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void excluir(Telefone telefone) throws Exception, ExclusaoNaoPermitidaException {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		Telefone telefoneExcluir;
		entityManager.getTransaction().begin();
		telefoneExcluir = entityManager.merge(telefone);
		entityManager.remove(telefoneExcluir);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Telefone> listarTodos() throws Exception {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		Query query = entityManager.createNamedQuery("telefoneListarTodos");
		return query.getResultList();
	}

	@Override
	public Telefone pesquisarPorId(Long id) throws Exception, NoResultException {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		return entityManager.find(Telefone.class, id);
	}

}
