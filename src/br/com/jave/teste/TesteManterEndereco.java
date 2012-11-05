package br.com.jave.teste;

import javax.persistence.NoResultException;

import br.com.java.modelo.Endereco;
import br.com.jave.fachada.EnderecoFachadaImpl;
import br.com.jave.fachada.Fachada;
import br.com.jave.fachada.PessoaFachadaImpl;
import br.com.jave.fachada.UfFachadaImpl;


public class TesteManterEndereco {
	
	static Fachada<Endereco> fachadaEndereco = new EnderecoFachadaImpl();
	static long ID_PESQUISA = 6;
	static Endereco endereco;
	
	public static void main(String[] args) {
		try{
			//salvar();
			//alterar();
			//excluir();
			//listarTodos();
			//listarPorId();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void salvar() throws Exception{
		Endereco endereco = new Endereco();
		endereco.setRua("Rua Frota Cavalcante");
		endereco.setNumero(93);
		endereco.setBairro("Colonia");
		endereco.setCep("60333745");
		endereco.setCidade("Fortaleza");
		endereco.setUf(new UfFachadaImpl().pesquisarPorId(ID_PESQUISA));
		endereco.setComplemento("Testes de graavação so endereco");
		endereco.setPontoDeReferencia("Perto da escola de sao jose");
		endereco.setPessoa(new PessoaFachadaImpl().pesquisarPorId((long)1));		
		fachadaEndereco.gravar(endereco);
	}

	public static void alterar() throws NoResultException, Exception{
		endereco = fachadaEndereco.pesquisarPorId(ID_PESQUISA);
		endereco.setRua("Av. Santos dumont");
		endereco.setNumero(1789);
		endereco.setComplemento("14 andar");
		endereco.setPontoDeReferencia(null);
		fachadaEndereco.gravar(endereco);
	}
	
	public static void excluir() throws NoResultException, Exception{
		endereco = fachadaEndereco.pesquisarPorId(ID_PESQUISA);
		fachadaEndereco.excluir(endereco);
	}
	
	public static void listarTodos() throws Exception{
		for(Endereco endereco : fachadaEndereco.listarTodos()){
			System.out.println(endereco.getRua());
		}		
	}
	
	public static void listarPorId() throws NoResultException, Exception{
		System.out.println(fachadaEndereco.pesquisarPorId((long)3).getRua());
	}
	
	
}
