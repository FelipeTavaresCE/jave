package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.ProdutoDao;
import br.com.jave.modelo.Produto;
import br.com.jave.util.FacesMessageUtil;

@Controller
@ManagedBean
@RequestScoped
public class ProdutoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProdutoDao produtoDao;
	private Produto produto = new Produto();
	private Produto produtoSelection = new Produto();
	private List<Produto> produtos = new ArrayList<>();
	
	public ProdutoMB() {}
	
	
	@Autowired
	public ProdutoMB(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;	
	}

	public void gravar() {
		try {
			produtoDao.gravar(produto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesMessageUtil.mensagem("Produto gravado com sucesso!");
	}
	
	public void prepararEdicao() {
		try {
			
			produto = produtoDao.pesquisarPorId(produtoSelection.getId());
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Produto> listarProdutos(){
		
		try {
			produtos = produtoDao.listarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produtos;
	}
	
	
	public String criarNovo(){
		produto = new Produto();
		return "novo";
	}
	
	
	
	
	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}


	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}


	public Produto getProdutoSelection() {
		return produtoSelection;
	}


	public void setProdutoSelection(Produto produtoSelection) {
		this.produtoSelection = produtoSelection;
	}
		
	
}
