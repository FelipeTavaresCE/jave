package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NoResultException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.ClienteDao;
import br.com.jave.dao.EnderecoDao;
import br.com.jave.dao.PessoaDao;
import br.com.jave.dao.UfDao;
import br.com.jave.enums.Sexo;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Cliente;
import br.com.jave.modelo.Email;
import br.com.jave.modelo.Endereco;
import br.com.jave.modelo.Pessoa;
import br.com.jave.modelo.Telefone;
import br.com.jave.modelo.Uf;
import br.com.jave.util.FacesMessageUtil;
import br.com.jave.util.UploadDeImagem;

@Controller
@ManagedBean
@RequestScoped
public class ClienteMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private PessoaDao pessoaDao;
	private EnderecoDao enderecoDao;
	private UfDao ufDao;
	private ClienteDao clienteDao;
	
	private Pessoa pessoa = new Pessoa();
	private Cliente cliente = new Cliente();
	private Endereco endereco = new Endereco();
	private List<Cliente> clientes = null;
	private List<Sexo> sexo;
	private List<Uf> ufListagem;
	private Endereco enderecoParaExcluir;
	private Telefone telefoneAdicionar = new Telefone();
	private Email emailAdicionar = new Email();
	private DefaultStreamedContent fotoGerada;
	private byte[] conteudoDoArquivo;
	
	private Pessoa pessoaPesquisa = new Pessoa();
	
	public ClienteMB(){}
	
	@Autowired
	public ClienteMB(PessoaDao pessoaDao, EnderecoDao enderecoDao, UfDao ufDao, ClienteDao clienteDao){
		this.pessoaDao = pessoaDao;
		this.enderecoDao = enderecoDao;
		this.ufDao = ufDao;
		this.clienteDao = clienteDao;
		this.sexo = Arrays.asList(Sexo.values());
	}
	
	public List<Uf> carregaUfs(){
		try {
			this.ufListagem = ufDao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.ufListagem;
	}
	
	public void gravar(){
		try {			
			//criarNovo(); //Descomentar se quiser que a tela seja limpada após a gravação
			if(cliente == null){
				cliente = new Cliente();
				cliente.setDataCadastro(new Date());
			}
			cliente.setPessoa(pessoa);
			cliente = clienteDao.gravarRetorno(cliente);
			if(this.pessoa.getFoto() != null)
				this.fotoGerada = UploadDeImagem.gerarApresentacaoTela(this.pessoa.getFoto());
			
			FacesMessageUtil.mensagem("Cliente Gravado com sucesso.");
		}catch(DataIntegrityViolationException e){
			FacesMessageUtil.erro("ERRO: Já existe um cliente associado a esta pessoa. " +
					"Provavelmente a tela foi atualizada. Seleciona novamente o cliente.\n", e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			FacesMessageUtil.erro("Ocorreu um erro ao gravar o cliente.\n", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEdicao(){
		try {
			cliente = clienteDao.pesquisarPorId(cliente.getId());
			//this.pessoa = pessoaDao.pesquisarPorId(cliente.getPessoa().getId());
			this.pessoa = cliente.getPessoa();
			this.fotoGerada = UploadDeImagem.gerarApresentacaoTela(this.pessoa.getFoto());
		} catch (NoResultException e) {
			e.printStackTrace();
			FacesMessageUtil.mensagem("Pessoa não encontrada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void adicionarEndereco(){
		if(this.pessoa.getEnderecos() == null)
			this.pessoa.setEnderecos(new ArrayList<Endereco>());
		//this.endereco.setPessoa(this.pessoa);
		this.pessoa.getEnderecos().add(this.endereco);

		this.endereco = new Endereco();
	}
	
	public void editarEndereco(RowEditEvent event){
		Endereco enderecoEditado = (Endereco)event.getObject();
		try {
			enderecoDao.gravar(enderecoEditado);
			enderecoEditado = new Endereco();
			FacesMessageUtil.mensagem("Endereço alterado com sucesso!");
		}catch (NoResultException e) {
			FacesMessageUtil.erro("Erro ao obter a UF.\n" + e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			FacesMessageUtil.erro("Erro ao gravar o endereço.\n" + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public void criarNovo(){
		this.pessoa            = new Pessoa();
		this.endereco          = new Endereco();
		this.cliente           = new Cliente();
	}
	
	public void excluirEndereco(){
		try {
			enderecoDao.excluir(enderecoParaExcluir);
			FacesMessageUtil.mensagem("Endereço Excluido com sucesso!");
			this.pessoa = pessoaDao.pesquisarPorId(this.pessoa.getId());
			this.enderecoParaExcluir = null;  
		} catch (ExclusaoNaoPermitidaException e) {
			FacesMessageUtil.erro(e.getMessage());
		}catch(Exception e){
			FacesMessageUtil.erro(e.getMessage());
		}
	}
	
	public List<Cliente> listarClientes(){
		try {
			this.clientes = clienteDao.listarTodos();			
		} catch (Exception e) {
			FacesMessageUtil.erro("erro ao carregar os clientes.");
			e.printStackTrace();
		}
		return this.clientes;
	}
	
	public void adicionarTelefone(){
		if(this.pessoa.getContatos() == null){
			this.pessoa.setContatos(new ArrayList<Telefone>());
		}
		telefoneAdicionar.setPessoa(this.pessoa);
		this.pessoa.getContatos().add(telefoneAdicionar);
		this.telefoneAdicionar = new Telefone();
	}
	
	public void adicionarEmail(){
		if(this.pessoa.getEmails() == null){
			this.pessoa.setEmails(new ArrayList<Email>());
		}
		emailAdicionar.setPessoa(this.pessoa);
		this.pessoa.getEmails().add(emailAdicionar);
		this.emailAdicionar = new Email();
	}
	
	public void carregarFoto(FileUploadEvent event){
		this.conteudoDoArquivo = event.getFile().getContents();
		this.fotoGerada = UploadDeImagem.gerarApresentacaoTela(this.conteudoDoArquivo);
		this.pessoa.setFoto(this.conteudoDoArquivo);
	}
	
	public void selecionarPessoa() throws NoResultException, Exception{
		System.out.println("Pessoa selecionada: " + pessoaPesquisa.getNome());
		pessoa = pessoaDao.pesquisarPorId(pessoaPesquisa.getId());
	}
	
	/*Métodos e get e set*/
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<Sexo> getSexo() {
		return sexo;
	}

	public void setSexo(List<Sexo> sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEnderecoParaExcluir() {
		return enderecoParaExcluir;
	}

	public void setEnderecoParaExcluir(Endereco enderecoParaExcluir) {
		this.enderecoParaExcluir = enderecoParaExcluir;
	}

	public List<Uf> getUfListagem() {
		return ufListagem;
	}

	public void setUfListagem(List<Uf> ufListagem) {
		this.ufListagem = ufListagem;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Telefone getTelefoneAdicionar() {
		return telefoneAdicionar;
	}

	public void setTelefoneAdicionar(Telefone telefoneAdicionar) {
		this.telefoneAdicionar = telefoneAdicionar;
	}

	public Email getEmailAdicionar() {
		return emailAdicionar;
	}

	public void setEmailAdicionar(Email emailAdicionar) {
		this.emailAdicionar = emailAdicionar;
	}

	public DefaultStreamedContent getFotoGerada() {
		return fotoGerada;
	}

	public void setFotoGerada(DefaultStreamedContent fotoGerada) {
		this.fotoGerada = fotoGerada;
	}

	public Pessoa getPessoaPesquisa() {
		return pessoaPesquisa;
	}

	public void setPessoaPesquisa(Pessoa pessoaPesquisa) {
		this.pessoaPesquisa = pessoaPesquisa;
	}
}
