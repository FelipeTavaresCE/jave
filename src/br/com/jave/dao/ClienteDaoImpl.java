package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Cliente;

@Repository
@Transactional
public class ClienteDaoImpl implements ClienteDao {
	
	@PersistenceContext
	EntityManager entityManager;	

	@Override
	@Transactional
	public void gravar(Cliente cliente) throws Exception {
		entityManager.merge(cliente);
	}

	@Override
	public void excluir(Cliente cliente) throws Exception,
			ExclusaoNaoPermitidaException {
		throw new ExclusaoNaoPermitidaException("Não é permitido excluir um cliente.");
	}

	@Override
	public List<Cliente> listarTodos() throws Exception {
		TypedQuery<Cliente> query = entityManager.createNamedQuery("clienteListarTodos", Cliente.class); 
		return query.getResultList();
	}

	@Override
	public Cliente pesquisarPorId(Long id) throws Exception, NoResultException {
		return entityManager.find(Cliente.class, id);
	}
	
	public Cliente pesquisarPorCodigo(Long codigoCliente){
		TypedQuery<Cliente> query =  entityManager.createNamedQuery("clientePesquisarPorCodigo", Cliente.class);
		List<Cliente> cliente = query.getResultList();
		if (cliente.size() == 0)
			throw new NoResultException("nenhum registro encontrado com o codigo informado");
		return cliente.get(0);
	}

}
