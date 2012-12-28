package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Produto;

@Repository
@Transactional
public class ProdutoDaoImpl implements ProdutoDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void gravar(Produto produto) throws Exception {
		entityManager.merge(produto);
		entityManager.flush();		
	}

	@Override
	public void excluir(Produto produto) throws Exception,
			ExclusaoNaoPermitidaException {
		entityManager.remove(produto);
	}

	@Override
	public List<Produto> listarTodos() throws Exception {
		Query query = entityManager.createNamedQuery("produtoListarTodos");
		return query.getResultList();
	}

	@Override
	public Produto pesquisarPorId(Long id) throws Exception, NoResultException {
		return entityManager.find(Produto.class, id);
	}

}
