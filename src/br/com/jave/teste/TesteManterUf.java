package br.com.jave.teste;

import br.com.jave.dao.UfDaoImpl;

public class TesteManterUf {
	
	public static void main(String[] args) {
		UfDaoImpl daoUf = new UfDaoImpl();
		
		try{
			System.out.println(daoUf.pesquisarPorId((long)3).getNome());
		}catch(Exception e){
			e.getMessage();
		}
		
		
		
	}

}
