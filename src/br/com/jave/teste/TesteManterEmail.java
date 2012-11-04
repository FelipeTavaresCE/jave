package br.com.jave.teste;

import javax.persistence.NoResultException;

import br.com.java.modelo.Email;
import br.com.java.modelo.Pessoa;
import br.com.jave.fachada.EmailFachadaImpl;
import br.com.jave.fachada.Fachada;
import br.com.jave.fachada.PessoaFachadaImpl;

public class TesteManterEmail {

	Fachada<Email> emailFachada = new EmailFachadaImpl();
	Email email = new Email();
	Fachada<Pessoa> pessoaFachada = new PessoaFachadaImpl();
	
	public static void main(String[] args) {
		TesteManterEmail teste = new TesteManterEmail(); 
		try{
			teste.salvarEmail();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
	}
	
	public void salvarEmail() throws NoResultException, Exception{
		email.setEmail("fulanodetal@talqual.com");
		email.setPessoa(pessoaFachada.pesquisarPorId((long)5));
		emailFachada.gravar(email);
	}	
}
