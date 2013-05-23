package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jave.fachada.TipoDeMedidaFachada;
import br.com.jave.modelo.TipoDeMedida;
import br.com.jave.util.FacesMessageUtil;

@Controller
@Scope("view")
public class TipoDeMedidaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TipoDeMedidaFachada tipoDeMedidaFachada;
	private List<TipoDeMedida> tipoDeMedidaListagem;
	@Autowired
	private TipoDeMedida tipoDeMedida;
	
	
	public void gravar(){
		try {
			tipoDeMedidaFachada.gravar(tipoDeMedida);
			criarNovo();
			FacesMessageUtil.mensagem("Operação realizada com sucesso.");
		} catch (Exception e) {
			FacesMessageUtil.erro("erro ao gravar os dados.", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void criarNovo(){
		tipoDeMedida = new TipoDeMedida();
	}
	
	public void listar(){
		try {
			setTipoDeMedidaListagem(tipoDeMedidaFachada.listarTodos());
		} catch (Exception e) {
			FacesMessageUtil.erro("erro ao listar os tipos de medida", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEdicao(){
		try {
			tipoDeMedida = tipoDeMedidaFachada.pesquisarPorId(tipoDeMedida.getId());
		} catch (NoResultException e) {
			FacesMessageUtil.erro("Erro ao recuperar objeto do banco de dados", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			FacesMessageUtil.erro("Erro ao recuperar objeto do banco de dados", e.getMessage());
			e.printStackTrace();
		}
	}

	public List<TipoDeMedida> getTipoDeMedidaListagem() {
		return tipoDeMedidaListagem;
	}

	public void setTipoDeMedidaListagem(List<TipoDeMedida> tipoDeMedidaListagem) {
		this.tipoDeMedidaListagem = tipoDeMedidaListagem;
	}

	public TipoDeMedida getTipoDeMedida() {
		return tipoDeMedida;
	}

	public void setTipoDeMedida(TipoDeMedida tipoDeMedida) {
		this.tipoDeMedida = tipoDeMedida;
	}

}
