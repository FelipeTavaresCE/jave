package br.com.jave.enums;

public enum StatusPedido {
	ORCAMENTO(0),
	CONCRETIZADO(1),
	CANCELADO(2);
	
	private final int statusId;
	
	StatusPedido(int id){
		statusId = id;		
	}

	public int getStatusId() {
		return statusId;
	}
}