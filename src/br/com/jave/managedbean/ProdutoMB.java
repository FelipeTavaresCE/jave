package br.com.jave.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jave.dao.ProdutoDao;
import br.com.jave.modelo.Produto;


@ManagedBean
@RequestScoped
public class ProdutoMB {
	
	private ProdutoDao produtoDao;
	private Produto produto = new Produto();
	
	public ProdutoMB() {}
	
	
	@Autowired
	public ProdutoMB(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;	
	
	}

	public void salvar(Produto produto) throws Exception{
		produtoDao.gravar(produto);
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
