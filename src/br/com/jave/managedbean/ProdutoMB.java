package br.com.jave.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
	
	public ProdutoMB() {}
	
	
	@Autowired
	public ProdutoMB(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;	
	
	}

	public void gravar() throws Exception {
		produtoDao.gravar(produto);
		FacesMessageUtil.mensagem("Produto gravado com sucesso!");
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
	
	
	
	
	
}
