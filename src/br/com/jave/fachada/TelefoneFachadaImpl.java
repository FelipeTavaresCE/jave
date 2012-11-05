package br.com.jave.fachada;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.java.modelo.Telefone;
import br.com.jave.dao.GenericDao;
import br.com.jave.dao.TelefoneDaoImpl;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;

public class TelefoneFachadaImpl implements Fachada<Telefone>{
	
	GenericDao<Telefone> telefoneDao = new TelefoneDaoImpl();

	@Override
	public void gravar(Telefone telefone) throws Exception {
		telefoneDao.gravar(telefone);
	}

	@Override
	public void excluir(Telefone telefone) throws Exception, ExclusaoNaoPermitidaException {
		telefoneDao.excluir(telefone);
	}

	@Override
	public List<Telefone> listarTodos() throws Exception {
		return telefoneDao.listarTodos();
	}

	@Override
	public Telefone pesquisarPorId(Long id) throws Exception, NoResultException {
		return telefoneDao.pesquisarPorId(id);
	}
	

}
