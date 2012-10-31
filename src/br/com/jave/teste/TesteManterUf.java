package br.com.jave.teste;

import br.com.java.modelo.Uf;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.fachada.Fachada;
import br.com.jave.fachada.UfFachadaImpl;

public class TesteManterUf {
	
	public static void main(String[] args) {
		
		Fachada<Uf> daoUf = new UfFachadaImpl();
		long ID_UF_PESQUISA = 5;
		
		try{
			//Criar
			Uf uf1 = new Uf();
			uf1.setNome("Belo Horizonte");
			uf1.setSigla("BH");
			daoUf.gravar(uf1);
			
			
			//Atualizar
			Uf ufUpdate = new Uf();
			ufUpdate = daoUf.pesquisarPorId(ID_UF_PESQUISA);
			ufUpdate.setNome("Santa Catarina");
			ufUpdate.setSigla("SC");
			daoUf.gravar(ufUpdate);
			
			
			for(Uf uf : daoUf.listarTodos()){
				System.out.println(uf.getId() + " - " + uf.getNome() + " - " + uf.getSigla());
			}			

			daoUf.exluir(ufUpdate);

			
			System.out.println("Pesquisa com filtro de ID: " + daoUf.pesquisarPorId((long)4).getNome());		
			
		}catch(ExclusaoNaoPermitidaException e){
			System.out.println(e.getMessage());
		}catch(Exception e){
			e.getMessage();
		}		
	}
}
