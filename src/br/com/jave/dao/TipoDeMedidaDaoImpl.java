package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.TipoDeMedida;

@Repository
@Transactional
public class TipoDeMedidaDaoImpl implements TipoDeMedidaDao{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void gravar(TipoDeMedida tipoDeMedida) throws Exception {
		manager.merge(tipoDeMedida);
	}

	@Override
	public void excluir(TipoDeMedida tipoDeMedida) throws Exception,
			ExclusaoNaoPermitidaException {
		throw new ExclusaoNaoPermitidaException("nao e permitido excluir tipo de medida.");
	}

	@Override
	public List<TipoDeMedida> listarTodos() throws Exception {
		TypedQuery<TipoDeMedida> query = manager.createNamedQuery("tipoDeMedidaListarTodos", TipoDeMedida.class);
		return query.getResultList();
	}

	@Override
	public TipoDeMedida pesquisarPorId(Long id) throws Exception,
			NoResultException {
		return manager.find(TipoDeMedida.class, id);
	}

}
