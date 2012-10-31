package br.com.jave.fachada;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;

public interface Fachada<T> {

	public void gravar(T entidade)throws Exception;
	public void exluir(T t)throws Exception, ExclusaoNaoPermitidaException;
	public List<T> listarTodos()throws Exception;
	public T pesquisarPorId(Long id)throws Exception, NoResultException;
	
}
