package br.com.jave.dao;

import br.com.jave.modelo.Cliente;

public interface ClienteDao extends GenericDao<Cliente>{
	public Cliente gravarRetorno(Cliente cliente) throws Exception;

}
