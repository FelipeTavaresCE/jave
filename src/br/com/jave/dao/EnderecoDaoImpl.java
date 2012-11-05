package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.java.modelo.Endereco;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;

public class EnderecoDaoImpl implements GenericDao<Endereco>{

	private EntityManager entityManager;
	
	@Override
	public void gravar(Endereco endereco) throws Exception {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(endereco);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void exluir(Endereco endereco) throws Exception, ExclusaoNaoPermitidaException {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		Endereco enderecoRemove;
		enderecoRemove = entityManager.merge(endereco);
		entityManager.getTransaction().begin();		
		entityManager.remove(enderecoRemove);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> listarTodos() throws Exception {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		Query query = entityManager.createNamedQuery("enderecoListarTodos", Endereco.class);
		return query.getResultList();
	}

	@Override
	public Endereco pesquisarPorId(Long id) throws Exception, NoResultException {
		entityManager = new EntityManagerFabrica().obterEntityManager();
		return entityManager.find(Endereco.class, id);
	}

}
