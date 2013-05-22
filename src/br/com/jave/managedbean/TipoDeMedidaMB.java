package br.com.jave.managedbean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.jave.fachada.TipoDeMedidaFachadaImpl;
import br.com.jave.modelo.TipoDeMedida;

@Component
@Scope("view")
public class TipoDeMedidaMB {
	
	private TipoDeMedidaFachadaImpl tipoDeMedidaFachada;
	private List<TipoDeMedida> tipoDeMedidaListagem; 
	
	@Autowired
	public TipoDeMedidaMB(TipoDeMedidaFachadaImpl tipoDeMedidaFachada){
		this.tipoDeMedidaFachada = tipoDeMedidaFachada;
	}
	
	public TipoDeMedidaMB(){}
	
	public void listar(){
		try {
			setTipoDeMedidaListagem(tipoDeMedidaFachada.listarTodos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TipoDeMedida> getTipoDeMedidaListagem() {
		return tipoDeMedidaListagem;
	}

	public void setTipoDeMedidaListagem(List<TipoDeMedida> tipoDeMedidaListagem) {
		this.tipoDeMedidaListagem = tipoDeMedidaListagem;
	}

}
