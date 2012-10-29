package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.java.modelo.Uf;

public class UfDaoImpl implements GenericDao<Uf>{

	EntityManager entityManager;
	
	@Override
	public void gravar(Uf entidade) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exluir(Uf t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Uf> listarTodos() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uf pesquisarPorId(Long id) throws Exception, NoResultException {
		entityManager = new EntityManagerFabrica().obterEntityManager(); 
		Query query = entityManager.createNamedQuery("ufPesquisarPorId");
		query.setParameter("id", id);
		return (Uf)query.getSingleResult();
	}
	
	

}
