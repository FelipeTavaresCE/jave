package br.com.jave.fachada;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jave.dao.TipoDeMedidaDao;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.TipoDeMedida;

@Component
public class TipoDeMedidaFachadaImpl implements Fachada<TipoDeMedida> {
	
	private TipoDeMedidaDao tipoDeMedidaDao;
	
	@Autowired
	public TipoDeMedidaFachadaImpl(TipoDeMedidaDao tipoDeMedidaDao){
		this.tipoDeMedidaDao = tipoDeMedidaDao;
	}

	public TipoDeMedidaFachadaImpl(){}

	@Override
	public void gravar(TipoDeMedida tipoDeMedida) throws Exception {
		tipoDeMedidaDao.gravar(tipoDeMedida);
	}

	@Override
	public void excluir(TipoDeMedida tipoDeMedida) throws Exception,
			ExclusaoNaoPermitidaException {
		tipoDeMedidaDao.excluir(tipoDeMedida);
	}

	@Override
	public List<TipoDeMedida> listarTodos() throws Exception {
		return tipoDeMedidaDao.listarTodos();
	}

	@Override
	public TipoDeMedida pesquisarPorId(Long id) throws Exception,
			NoResultException {
		return tipoDeMedidaDao.pesquisarPorId(id);
	}

}
