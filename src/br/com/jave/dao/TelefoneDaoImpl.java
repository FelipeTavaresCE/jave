package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Telefone;

@Repository
@Transactional
public class TelefoneDaoImpl implements GenericDao<Telefone>{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void gravar(Telefone telefone) throws Exception {
		entityManager.persist(telefone);
		entityManager.flush();
	}

	@Override
	public void excluir(Telefone telefone) throws Exception, ExclusaoNaoPermitidaException {
				entityManager.remove(telefone);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Telefone> listarTodos() throws Exception {
		Query query = entityManager.createNamedQuery("telefoneListarTodos");
		return query.getResultList();
	}

	@Override
	public Telefone pesquisarPorId(Long id) throws Exception, NoResultException {
		return entityManager.find(Telefone.class, id);
	}

}
