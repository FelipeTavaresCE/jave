package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.ConfiguracoesDoSistema;

@Repository
@Transactional
public class ConfiguracoesDoSistemaDaoImpl implements ConfiguracoesDoSistemaDao{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void gravar(ConfiguracoesDoSistema configuracoesDoSistema) throws Exception {
		manager.merge(configuracoesDoSistema);
	}

	@Override
	public void excluir(ConfiguracoesDoSistema configuracoesDoSistema) throws Exception,
			ExclusaoNaoPermitidaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ConfiguracoesDoSistema> listarTodos() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfiguracoesDoSistema pesquisarPorId(Long id) throws Exception, NoResultException {
		return manager.find(ConfiguracoesDoSistema.class, id);
	}

}
