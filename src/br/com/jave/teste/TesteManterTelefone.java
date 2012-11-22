package br.com.jave.teste;

import javax.persistence.NoResultException;

import br.com.jave.fachada.Fachada;
import br.com.jave.fachada.PessoaFachadaImpl;
import br.com.jave.fachada.TelefoneFachadaImpl;
import br.com.jave.modelo.Telefone;

public class TesteManterTelefone {
	
	Fachada<Telefone> telefoneFac = new TelefoneFachadaImpl();
	Telefone telefone = new Telefone();
	long ID_PESSOA = 3;
	long TELEFONE_ID = 1;
	
	public static void main(String[] args) {
		TesteManterTelefone teste = new TesteManterTelefone();
		try {
			//teste.slavar();
			//teste.atualizar();
			teste.excluir();
			//teste.pesquisarPorId();
			//teste.listarTodos();
		}catch(NoResultException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	void slavar() throws NoResultException, Exception{
		telefone.setCodigoDeArea("011");
		telefone.setNumero("30526222");
		telefone.setPessoa(new PessoaFachadaImpl().pesquisarPorId(ID_PESSOA));
		telefoneFac.gravar(telefone);
	}	
	
	void atualizar() throws NoResultException, Exception{
		telefone = telefoneFac.pesquisarPorId(TELEFONE_ID);
		telefone.setCodigoDeArea("000");
		telefone.setNumero("invalido");
		telefoneFac.gravar(telefone);
	}
	
	void excluir() throws NoResultException, Exception{
		telefoneFac.excluir(telefoneFac.pesquisarPorId(TELEFONE_ID));
	}
	void listarTodos() throws Exception{
		for(Telefone telefone : telefoneFac.listarTodos()){
			System.out.println(telefone.getNumero());
		}
	}
	void pesquisarPorId() throws NoResultException, Exception{
		System.out.println(telefoneFac.pesquisarPorId(TELEFONE_ID).getNumero());
	}

}
