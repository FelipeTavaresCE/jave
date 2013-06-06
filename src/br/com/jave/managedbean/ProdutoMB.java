package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.ProdutoDao;
import br.com.jave.dao.TipoDeMedidaDao;
import br.com.jave.modelo.Produto;
import br.com.jave.modelo.TipoDeMedida;
import br.com.jave.util.FacesMessageUtil;

@Controller
@ManagedBean
@Scope("view")
public class ProdutoMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean alter = false;
	private ProdutoDao produtoDao;
	private TipoDeMedidaDao tpMedidaDao;
	private Produto produto = new Produto();
	private Produto produtoSelection;
	private List<Produto> produtos = new ArrayList<>();
	private List<TipoDeMedida> tpMedidas = new ArrayList<>();
	
	public ProdutoMB() {
		
		try {
			tpMedidas = tpMedidaDao.listarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Autowired
	public ProdutoMB(ProdutoDao produtoDao,TipoDeMedidaDao tpMedidaDao) {
		this.produtoDao = produtoDao;	
		this.tpMedidaDao = tpMedidaDao;
	}

	public void gravar() {
		try {
			produtoDao.gravar(produto);
			criarNovo();
			alter = true;
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
			  if(produtos.isEmpty() && alter == true)
				  produtos = produtoDao.listarTodos();
			tpMedidas = tpMedidaDao.listarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produtos;
	}
	
	public void criarNovo(){
		produto = new Produto();
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


	public List<TipoDeMedida> getTpMedidas() {
		return tpMedidas;
	}

	public void setTpMedidas(List<TipoDeMedida> tpMedidas) {
		this.tpMedidas = tpMedidas;
	}


	public TipoDeMedidaDao getTpMedidaDao() {
		return tpMedidaDao;
	}


	public void setTpMedidaDao(TipoDeMedidaDao tpMedidaDao) {
		this.tpMedidaDao = tpMedidaDao;
	}
		
	
}
