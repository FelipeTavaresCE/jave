package br.com.jave.enums;

public enum Sexo {
	MASCULINO("Masculino"), FEMINO("Feminino");
	
	private String sexo;
	
	Sexo(String sexo){
		this.sexo = sexo;
	}
	
	public String toString(){
		return sexo;
	}
}
