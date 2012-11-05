package br.com.jave.teste;

import javax.persistence.NoResultException;

import br.com.java.modelo.Email;
import br.com.java.modelo.Pessoa;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.fachada.EmailFachadaImpl;
import br.com.jave.fachada.Fachada;
import br.com.jave.fachada.PessoaFachadaImpl;

public class TesteManterEmail {

	static Fachada<Email> emailFachada = new EmailFachadaImpl();
	Email email = new Email();
	Fachada<Pessoa> pessoaFachada = new PessoaFachadaImpl();
	static final long ID_PESQUISA = 1;
	
	//Main - executar teste
	public static void main(String[] args) {
		TesteManterEmail teste = new TesteManterEmail();
		try{
			teste.salvarEmail();
			teste.atualizarEmail(ID_PESQUISA);
			teste.excluir(emailFachada.pesquisarPorId(ID_PESQUISA));
			teste.listarTodos();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
	}
	
	public void salvarEmail() throws NoResultException, Exception{
		email.setEmail("adejanny@gmail.com");
		email.setPessoa(pessoaFachada.pesquisarPorId((long)1));
		emailFachada.gravar(email);
	}
	
	public void atualizarEmail(long id) throws NoResultException, Exception{
		Email emailAtualizar = new Email();		
		emailAtualizar = emailFachada.pesquisarPorId(id);
		emailAtualizar.setEmail("franciscopf@gmail.com");
		emailFachada.gravar(emailAtualizar);
	}

	public void excluir(Email email) throws ExclusaoNaoPermitidaException, Exception{
		emailFachada.exluir(email);
	}

	public void listarTodos() throws Exception{
		for(Email email : emailFachada.listarTodos()){
			System.out.println(email.getPessoa().getNome() + " - " +email.getEmail());
		}
	}

}
