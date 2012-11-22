package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Pessoa;

@Repository
@Transactional
public class PessoaDaoImpl implements GenericDao<Pessoa>{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void gravar(Pessoa pessoa){
		entityManager.persist(pessoa);
		entityManager.flush();
	}	
	
	public void excluir(Pessoa pessoa)throws Exception, ExclusaoNaoPermitidaException{
		throw new Exception("Não é possível apagar essa informação do sistema!");
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarTodos(){
		Query query = entityManager.createNamedQuery("pessoaListarTodos");
		return query.getResultList();
	}
	
	public Pessoa pesquisarPorId(Long id)throws NoResultException{
		return entityManager.find(Pessoa.class, id);
	}

}
