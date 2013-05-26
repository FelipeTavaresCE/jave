package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.jave.dao.ProdutoDao;
import br.com.jave.fachada.PedidoFachada;
import br.com.jave.modelo.Pedido;
import br.com.jave.modelo.PedidoItem;
import br.com.jave.modelo.Produto;
import br.com.jave.util.FacesMessageUtil;


@Component
@Scope("view")
public class VendaMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProdutoDao produtoDao;
	@Autowired
	private Pedido pedido;
	private Produto produto = new Produto();
	@Autowired
	private PedidoFachada pedidoFachada;
	@Autowired
	private PedidoItem pedidoItens;
	private Integer quantidade;
	
	public void finalizarPedido(){
		try {
			pedidoFachada.gravar(pedido);
			FacesMessageUtil.mensagem("Pedigo gravad com sucesso.");
		} catch (Exception e) {
			FacesMessageUtil.erro("erro ao gravar o pedido.", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void pesquisarProduto(){
		if(produto.getId() != null){
			try {
				produto = produtoDao.pesquisarPorId(produto.getId());
			} catch (NoResultException e) {
				System.out.println("ERRO AO PESQUISAR PRODUTO POR CODIGO" + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("ERRO AO PESQUISAR PRODUTO POR CODIGO" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void pesquisaPorCodigoBarras(){
		if(!produto.getCodigoDeBarras().isEmpty()){
			try {
				produto = produtoDao.pesquisarProdutoCodigoDeBarras(produto.getCodigoDeBarras());
			} catch (Exception e) {
				System.out.println("ERRO AO PESQUISAR PRODUTO POR CODIGO DE BARRAS" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void adicionarProduto(){
		if(quantidade != null && quantidade != 0){
			pedidoItens.setProduto(produto);
			pedidoItens.setQuantidade(quantidade);
			if(pedido.getPedidoItens() == null){
				pedido.setPedidoItens(new ArrayList<PedidoItem>());
			}
			pedido.getPedidoItens().add(pedidoItens);
			
			pedidoItens = new PedidoItem();
			produto = new Produto();
			quantidade = null;
		}
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
