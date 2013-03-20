package br.com.jave.dao;

import javax.persistence.NoResultException;

import br.com.jave.modelo.PerfilDeAcesso;

public interface PerfilDeAcessoDao extends GenericDao<PerfilDeAcesso>{
	
	PerfilDeAcesso pesquisarPorNome(String nome)throws NoResultException;

}
