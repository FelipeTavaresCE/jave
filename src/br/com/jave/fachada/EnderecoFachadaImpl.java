package br.com.jave.fachada;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.jave.dao.EnderecoDaoImpl;
import br.com.jave.dao.GenericDao;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Endereco;

public class EnderecoFachadaImpl implements Fachada<Endereco>{

	GenericDao<Endereco> enderecoDao = new EnderecoDaoImpl();
	
	@Override
	public void gravar(Endereco endereco) throws Exception {
		enderecoDao.gravar(endereco);
	}

	@Override
	public void excluir(Endereco endereco) throws Exception, ExclusaoNaoPermitidaException {
		enderecoDao.excluir(endereco);
	}

	@Override
	public List<Endereco> listarTodos() throws Exception {
		return enderecoDao.listarTodos();
	}

	@Override
	public Endereco pesquisarPorId(Long id) throws Exception, NoResultException {
		return enderecoDao.pesquisarPorId(id);
	}
}
