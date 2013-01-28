package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Pessoa;

@Repository
@Transactional
public class PessoaDaoImpl implements PessoaDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void gravar(Pessoa pessoa){
		entityManager.merge(pessoa);
		entityManager.flush();
	}	
	
	@Override
	public void excluir(Pessoa pessoa)throws Exception, ExclusaoNaoPermitidaException{
		throw new Exception("Não é possível apagar essa informação do sistema!");
	}
	
	@Override
	public List<Pessoa> listarTodos(){
		TypedQuery<Pessoa> query = entityManager.createNamedQuery("pessoaListarTodos", Pessoa.class);
		return query.getResultList();
	}
	
	@Override
	public Pessoa pesquisarPorId(Long id)throws NoResultException{
		return entityManager.find(Pessoa.class, id);
	}
	
	@Override
	public List<Pessoa> pesquisarPorNomeCpfCnpj(String nome, String cpf, String cnpj){
		TypedQuery<Pessoa> query = entityManager.createNamedQuery("pessoaPesquisarPorNomeCpfCnpj", Pessoa.class);
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("cpf", cpf);
		query.setParameter("cnpj", cnpj);
		System.out.println("Entrou na função da dao");
		System.out.println(nome);
		System.out.println(cpf);
		System.out.println(cnpj);
		return query.getResultList();
	}

}
