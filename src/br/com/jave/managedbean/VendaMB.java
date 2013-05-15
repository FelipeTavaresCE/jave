package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.ProdutoDao;
import br.com.jave.modelo.Pedido;
import br.com.jave.modelo.PedidoItem;
import br.com.jave.modelo.Produto;

@ManagedBean
@ViewScoped
@Controller
public class VendaMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ProdutoDao produtoDao;
	private Long codigoProdutoPesquisa;
	private Pedido pedido = new Pedido();
	private Produto produto = new Produto();
	private PedidoItem pedidoItens = new PedidoItem();
	private Integer quantidade;
	
	@Autowired
	public VendaMB(ProdutoDao produtoDao){
		this.produtoDao = produtoDao;
	}
	
	public VendaMB(){}
	
	public void pesquisarProduto(){
		try {
			produto = produtoDao.pesquisarPorId(codigoProdutoPesquisa);
		} catch (NoResultException e) {
			System.out.println("ERRO AO PESQUISAR PRODUTO" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("ERRO AO PESQUISAR PRODUTO" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void adicionarProduto(){
		pedidoItens.setProduto(produto);
		pedidoItens.setQuantidade(quantidade);
		if(pedido.getPedidoItens() == null){
			pedido.setPedidoItens(new ArrayList<PedidoItem>());
		}
		pedido.getPedidoItens().add(pedidoItens);
		
		pedidoItens = new PedidoItem();
		produto = new Produto();
		codigoProdutoPesquisa = null;
		quantidade = null;
		
		System.out.println("produto incluido ao pedido.");
	}

	public Long getCodigoProdutoPesquisa() {
		return codigoProdutoPesquisa;
	}

	public void setCodigoProdutoPesquisa(Long codigoProdutoPesquisa) {
		this.codigoProdutoPesquisa = codigoProdutoPesquisa;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public PedidoItem getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(PedidoItem pedidoItens) {
		this.pedidoItens = pedidoItens;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	


}
