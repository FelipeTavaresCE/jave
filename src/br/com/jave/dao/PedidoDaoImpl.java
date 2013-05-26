package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Pedido;

@Repository
@Component
@Transactional
public class PedidoDaoImpl implements PedidoDao{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void gravar(Pedido pedido) throws Exception {
		manager.merge(pedido);
	}

	@Override
	public void excluir(Pedido entidade) throws Exception,
			ExclusaoNaoPermitidaException {
		throw new ExclusaoNaoPermitidaException("nao e permitido exlcuir um pedido");
	}

	@Override
	public List<Pedido> listarTodos() throws Exception {
		TypedQuery<Pedido> query = manager.createQuery("SELECT p FROM Pedido p",Pedido.class);
		return query.getResultList();
	}

	@Override
	public Pedido pesquisarPorId(Long id) throws Exception, NoResultException {
		return manager.find(Pedido.class, id);
	}

}
