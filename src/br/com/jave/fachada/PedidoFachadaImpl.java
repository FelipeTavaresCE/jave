package br.com.jave.fachada;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jave.dao.PedidoDao;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Pedido;

@Component
public class PedidoFachadaImpl implements PedidoFachada, Serializable{
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Autowired
	private PedidoDao pedidoDao;

	@Override
	public void gravar(Pedido pedido) throws Exception {
		pedidoDao.gravar(pedido);
	}

	@Override
	public void excluir(Pedido pedido) throws Exception,
			ExclusaoNaoPermitidaException {
		pedidoDao.excluir(pedido);
	}

	@Override
	public List<Pedido> listarTodos() throws Exception {
		return pedidoDao.listarTodos();
	}

	@Override
	public Pedido pesquisarPorId(Long id) throws Exception, NoResultException {
		return pedidoDao.pesquisarPorId(id);
	}

}
