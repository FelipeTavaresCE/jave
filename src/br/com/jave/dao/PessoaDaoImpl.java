package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.java.modelo.Pessoa;

public class PessoaDaoImpl implements GenericDao<Pessoa>{

	private EntityManager entityManager;
	
	public void gravar(Pessoa pessoa){
		entityManager = new EntityManagerFabrica().obterEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(pessoa);
		entityManager.getTransaction().commit();
		entityManager.close();		
	}	
	
	public void exluir(Pessoa pessoa)throws Exception{
		throw new Exception("Não é possível apagar essa informação do sistema!");
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarTodos(){
		entityManager = new EntityManagerFabrica().obterEntityManager();
		Query query = entityManager.createNamedQuery("pessoaListarTodos");
		return query.getResultList();
	}
	
	public Pessoa pesquisarPorId(Long id){
		entityManager = new EntityManagerFabrica().obterEntityManager();
		Query query = entityManager.createNamedQuery("pessoaPesquisarPorId", Pessoa.class);
		query.setParameter("id", id);
		return (Pessoa)query.getSingleResult();
	}

}
