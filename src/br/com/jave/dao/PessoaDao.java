package br.com.jave.dao;

import java.util.List;

import br.com.jave.modelo.Pessoa;

public interface PessoaDao extends GenericDao<Pessoa>{
	
	public List<Pessoa> pesquisarPorNomeCpfCnpj(String nome, String cpf, String cnpj);
	
}
