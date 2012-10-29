package br.com.jave.excecoes;

public class ExclusaoNaoPermitidaException extends Exception{

	private static final long serialVersionUID = 1L;

	public ExclusaoNaoPermitidaException(String mensagem){
		super(mensagem);
	}
	
	
}
