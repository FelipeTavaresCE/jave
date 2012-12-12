package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Endereco;

@Repository
@Transactional
public class EnderecoDaoImpl implements EnderecoDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void gravar(Endereco endereco) throws Exception {
		entityManager.merge(endereco);
		entityManager.flush();
	}

	@Override
	@Transactional
	public void excluir(Endereco endereco) throws Exception, ExclusaoNaoPermitidaException {
			entityManager.remove(entityManager.merge(endereco));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> listarTodos() throws Exception {
		Query query = entityManager.createNamedQuery("enderecoListarTodos", Endereco.class);
		return query.getResultList();
	}

	@Override
	public Endereco pesquisarPorId(Long id) throws Exception, NoResultException {
		return entityManager.find(Endereco.class, id);
	}

}
