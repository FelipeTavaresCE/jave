package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.jave.dao.UfDao;
import br.com.jave.modelo.Uf;
import br.com.jave.util.FacesMessageUtil;

@Component
@Scope("view")
public class UfMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Uf uf = new Uf();
	private UfDao ufDao;
	private List<Uf> ufLista;
	private Uf ufSelecionada;
	
	@Autowired
	public UfMB(UfDao dao){
		ufDao = dao;
		listarUfs();
	}
	
	public UfMB(){
		
	}

	public void salvar(){
		try {
			ufDao.gravar(uf);
			uf = new Uf();
			FacesMessageUtil.mensagem("Estado Cadastro com sucesso.");
			listarUfs();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessageUtil.mensagem("Ocorreu um erro.\n" + e.getMessage());
		}
	}	
	
	public void prepararEdicao(){
		try {
			this.uf = ufDao.pesquisarPorId(ufSelecionada.getId());
		} catch (NoResultException e) {
			e.printStackTrace();
			FacesMessageUtil.mensagem("Estado não encontrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Uf> listarUfs(){
		try {
			ufLista = ufDao.listarTodos();
		} catch (Exception e) {
			FacesMessageUtil.mensagem("Não Existe Estados cadastrados.");
		}
		return ufLista;
	}
	
	
	public UfDao getUfDao() {
		return ufDao;
	}

	public void setUfDao(UfDao ufDao) {
		this.ufDao = ufDao;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public List<Uf> getUfLista() {
		return ufLista;
	}

	public void setUfLista(List<Uf> ufLista) {
		this.ufLista = ufLista;
	}

	public Uf getUfSelecionada() {
		return ufSelecionada;
	}

	public void setUfSelecionada(Uf ufSelecionada) {
		this.ufSelecionada = ufSelecionada;
	}
}