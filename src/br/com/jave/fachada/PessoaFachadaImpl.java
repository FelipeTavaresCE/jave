package br.com.jave.fachada;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.java.modelo.Pessoa;
import br.com.jave.dao.GenericDao;
import br.com.jave.dao.PessoaDaoImpl;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;

public class PessoaFachadaImpl implements Fachada<Pessoa>{
	
	GenericDao<Pessoa> pessoaDao = new PessoaDaoImpl();

	@Override
	public void gravar(Pessoa entidade) throws Exception {
		pessoaDao.gravar(entidade);
	}

	@Override
	public void excluir(Pessoa entidade) throws Exception, ExclusaoNaoPermitidaException {
		pessoaDao.excluir(entidade);		
	}

	@Override
	public List<Pessoa> listarTodos() throws Exception {
		return pessoaDao.listarTodos();
	}

	@Override
	public Pessoa pesquisarPorId(Long id) throws Exception, NoResultException {
		return pessoaDao.pesquisarPorId(id);
	}

}
