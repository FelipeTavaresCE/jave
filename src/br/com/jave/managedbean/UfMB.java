package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.NoResultException;

import br.com.java.modelo.Uf;
import br.com.jave.dao.GenericDao;
import br.com.jave.dao.UfDaoImpl;
import br.com.jave.util.FacesMessageUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UfMB implements Serializable{

	private Uf uf = new Uf();
	private GenericDao<Uf> ufDao = new UfDaoImpl();
	private List<Uf> ufLista;
	private Uf ufSelecionada;
	
	public UfMB(){
		listarUfs();
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