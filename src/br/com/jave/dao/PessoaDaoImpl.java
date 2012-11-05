package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.java.modelo.Pessoa;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;

public class PessoaDaoImpl implements GenericDao<Pessoa>{

	private EntityManager entityManager;
	
	public void gravar(Pessoa pessoa){
		entityManager = new EntityManagerFabrica().obterEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(pessoa);
		entityManager.getTransaction().commit();
		entityManager.close();		
	}	
	
	public void excluir(Pessoa pessoa)throws Exception, ExclusaoNaoPermitidaException{
		throw new Exception("Não é possível apagar essa informação do sistema!");
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarTodos(){
		entityManager = new EntityManagerFabrica().obterEntityManager();
		Query query = entityManager.createNamedQuery("pessoaListarTodos");
		return query.getResultList();
	}
	
	public Pessoa pesquisarPorId(Long id)throws NoResultException{
		entityManager = new EntityManagerFabrica().obterEntityManager();
		return entityManager.find(Pessoa.class, id);
	}

}
