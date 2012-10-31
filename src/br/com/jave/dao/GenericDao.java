package br.com.jave.dao;

import java.util.List;

import javax.persistence.NoResultException;

public interface GenericDao<T> {
	
	public void gravar(T entidade) throws Exception;
	public void exluir(T t)throws Exception;	
	public List<T> listarTodos()throws Exception;
	public T pesquisarPorId(Long id)throws Exception, NoResultException;

}
