package br.com.jave.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.PerfilDeAcesso;

@Repository
@Transactional
public class PerfilDeAcessoDaoImpl implements PerfilDeAcessoDao{
	
	@PersistenceContext
	EntityManager entityManager;	

	@Override
	public void gravar(PerfilDeAcesso entidade) throws Exception {
		throw new Exception("ROTINA NATURAL: FUNCIONALIDADE NAO IMPLEMENTADA");
	}

	@Override
	public void excluir(PerfilDeAcesso entidade) throws Exception,
			ExclusaoNaoPermitidaException {
		throw new Exception("ROTINA NATURAL: FUNCIONALIDADE NAO IMPLEMENTADA");
		
	}

	@Override
	public List<PerfilDeAcesso> listarTodos() throws Exception {
		TypedQuery<PerfilDeAcesso> query = entityManager.createQuery("SELECT p FROM PerfilDeAcesso p", PerfilDeAcesso.class);
		return query.getResultList();
	}

	@Override
	public PerfilDeAcesso pesquisarPorId(Long id) throws Exception,
			NoResultException {
		return null;
	}

	@Override
	public PerfilDeAcesso pesquisarPorNome(String nome)
			throws NoResultException {
		return entityManager.find(PerfilDeAcesso.class, nome);
	}

}
