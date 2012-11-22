package br.com.jave.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Uf;

@Repository
@Transactional
public class UfDaoImpl implements UfDao, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void gravar(Uf uf) throws Exception {
		entityManager.persist(uf);
		entityManager.flush();
	}

	@Override
	public void excluir(Uf t) throws Exception , ExclusaoNaoPermitidaException{
		throw new ExclusaoNaoPermitidaException("Não é permitido excluir uma Unidade Federativa.");		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Uf> listarTodos() throws Exception {
		Query query = entityManager.createNamedQuery("ufListarTodos");
		return query.getResultList();
	}

	@Override
	public Uf pesquisarPorId(Long id) throws Exception, NoResultException {
		return entityManager.find(Uf.class, id);
	}
}
