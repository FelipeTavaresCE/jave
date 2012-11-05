package br.com.jave.dao;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;

public interface GenericDao<T> {
	
	public void gravar(T entidade) throws Exception;
	public void excluir(T entidade)throws Exception, ExclusaoNaoPermitidaException;
	public List<T> listarTodos()throws Exception;
	public T pesquisarPorId(Long id)throws Exception, NoResultException;

}
