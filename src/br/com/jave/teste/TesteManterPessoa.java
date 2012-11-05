package br.com.jave.teste;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.java.modelo.Email;
import br.com.java.modelo.Endereco;
import br.com.java.modelo.Pessoa;
import br.com.java.modelo.Telefone;
import br.com.java.modelo.Uf;
import br.com.jave.dao.GenericDao;
import br.com.jave.dao.UfDaoImpl;
import br.com.jave.enums.Sexo;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.fachada.Fachada;
import br.com.jave.fachada.PessoaFachadaImpl;
import br.com.jave.util.DataHoraUtil;
//import org.apache.log4j.BasicConfigurator;

public class TesteManterPessoa {
	
	private Pessoa pessoa = new Pessoa();
	private List<Telefone> telefones = new ArrayList<Telefone>();
	private List<Email> emails = new ArrayList<Email>();
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	private GenericDao<Uf> uf = new UfDaoImpl();
	private Fachada<Pessoa> pessoaFachada = new PessoaFachadaImpl();
	
	public static void main(String[] args) throws NoResultException, Exception {
		//BasicConfigurator.configure();
		
		TesteManterPessoa teste = new TesteManterPessoa();
		
		try{
			teste.salvarPessoa();
			teste.listarTodos();
			teste.pesquisarPorId(1);
			teste.excluir(null);
			teste.alterar();
		}catch(NoResultException e){
			System.out.println(e.getMessage());
			System.out.println("dado não localizado");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}	
	}
	
	public void salvarPessoa() throws NoResultException, Exception{
		
		//-------- Telefones ----------------------------
		Telefone tel1 = new Telefone();
		tel1.setCodigoDeArea("085");
		tel1.setNumero("8785-0188");
		tel1.setPessoa(pessoa);
		
		Telefone tel2 = new Telefone();
		tel2.setCodigoDeArea("085");
		tel2.setNumero("32865592");
		tel2.setPessoa(pessoa);
		
		telefones.add(tel1);
		telefones.add(tel2);
		//-----------------------------------------------
		
		//-------- Email ----------------------------
		Email email1 = new Email();
		email1.setEmail("paulogomes.tec@gmail.com");

		Email email2 = new Email();
		email2.setEmail("paulo.gomes@arce.ce.gov.br");

		emails.add(email1);
		emails.add(email2);
		//-----------------------------------------------
		
		//-------- Pessoa ----------------------------

		RandomAccessFile arquivoEntrada = new RandomAccessFile("/home/desenv/livre/carro.jpg", "r");
		byte[] arquivoFoto = new byte[(int)arquivoEntrada.length()];
		arquivoEntrada.read(arquivoFoto);
		arquivoEntrada.close();
		
		pessoa.setCnpj(null);
		pessoa.setContatos(telefones);
		pessoa.setCpf("00434926302");
		pessoa.setDataCadastro(new Date());
		pessoa.setDataNascimento(DataHoraUtil.criarData("14/08/1986"));
		pessoa.setEmails(emails);
		pessoa.setEnderecos(enderecos);
		pessoa.setNome("Francisco Paulo Ferreira Gomes");
		pessoa.setSexo(Sexo.MASCULINO);
		pessoa.setFoto(arquivoFoto);
		//-----------------------------------------------
		
		Endereco endereco1 = new Endereco();
		endereco1.setRua("Rua São Jose");
		endereco1.setNumero(567);
		endereco1.setBairro("Colonia");
		endereco1.setCep("60333-745");
		endereco1.setCidade("Fortaleza");
		endereco1.setUf(uf.pesquisarPorId((long)1));
		endereco1.setPontoDeReferencia("Perto da Praia dos Arpoadores");
		endereco1.setComplemento("Apartamento 10000");
		enderecos.add(endereco1);		
		
		pessoaFachada.gravar(pessoa);
		
		byte[] byteFoto = pessoaFachada.pesquisarPorId((long)1).getFoto();
		
		FileOutputStream fileOuputStream = new FileOutputStream("/home/desenv/livre/carroBanco.jpg");
		fileOuputStream.write(byteFoto);
		fileOuputStream.close();
	}
	
	public void listarTodos() throws Exception{
		for(Pessoa pessoas : pessoaFachada.listarTodos()){
			System.out.println(pessoas.getNome());
		}		
	}

	public void pesquisarPorId(long id) throws NoResultException, Exception{
		System.out.println(pessoaFachada.pesquisarPorId(id).getNome());
	}

	public void excluir(Pessoa pessoa) throws ExclusaoNaoPermitidaException, Exception{
		pessoaFachada.excluir(pessoa);
	}

	public void alterar() throws Exception,NoResultException{
		Pessoa pessoa;
		pessoa = pessoaFachada.pesquisarPorId((long)1);
		pessoa.setNome("Paulo Gomes");
		//pessoa.setCpf("02985696305");
		//pessoa.setSexo(Sexo.MASCULINO);
		pessoaFachada.gravar(pessoa);
	}

}