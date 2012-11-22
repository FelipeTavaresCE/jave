package br.com.jave.fachada;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.jave.dao.EmailDaoImpl;
import br.com.jave.dao.GenericDao;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Email;

public class EmailFachadaImpl implements Fachada<Email>{
	
	GenericDao<Email> emailDao = new EmailDaoImpl();

	@Override
	public void gravar(Email email) throws Exception {
		emailDao.gravar(email);	
	}

	@Override
	public void excluir(Email email) throws Exception, ExclusaoNaoPermitidaException {
		emailDao.excluir(email);	
	}

	@Override
	public List<Email> listarTodos() throws Exception {
		return emailDao.listarTodos();
	}

	@Override
	public Email pesquisarPorId(Long id) throws Exception, NoResultException {
		return emailDao.pesquisarPorId(id);
	}

}
