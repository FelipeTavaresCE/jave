package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NoResultException;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.ClienteDao;
import br.com.jave.dao.EnderecoDao;
import br.com.jave.dao.PessoaDao;
import br.com.jave.dao.UfDao;
import br.com.jave.enums.Sexo;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Cliente;
import br.com.jave.modelo.Endereco;
import br.com.jave.modelo.Pessoa;
import br.com.jave.modelo.Uf;
import br.com.jave.util.FacesMessageUtil;

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
	private Cliente cliente;
	private Endereco endereco = new Endereco();
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	private List<Pessoa> pessoasListagem;
	private List<Cliente> clientes = null;
	private List<Sexo> sexo;
	private List<Uf> ufListagem;
	private Pessoa pessoaSelecionada;
	private Endereco enderecoParaExcluir;
	private Long idUfSelecionada;
	
	public ClienteMB(){}
	
	@Autowired
	public ClienteMB(PessoaDao pessoaDao, EnderecoDao enderecoDao, UfDao ufDao, ClienteDao clienteDao){
		this.pessoaDao = pessoaDao;
		this.enderecoDao = enderecoDao;
		this.ufDao = ufDao;
		this.clienteDao = clienteDao;
		this.sexo = Arrays.asList(Sexo.values());
		//listarPessoas();
		//listarClientes();
		carregaUfs();
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
			pessoa.setEnderecos(enderecos);
			pessoaDao.gravar(this.pessoa);
			this.pessoa            = new Pessoa();
			this.pessoaSelecionada = new Pessoa();
			this.enderecos         = new ArrayList<Endereco>();
			this.endereco          = new Endereco();
			listarPessoas();
			FacesMessageUtil.mensagem("Cliente Gravado com sucesso.");
		} catch (Exception e) {
			FacesMessageUtil.erro("Ocorreu um erro ao gravar o cliente.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEdicao(){
		try {
			this.pessoa = pessoaDao.pesquisarPorId(cliente.getPessoa().getId());
		} catch (NoResultException e) {
			e.printStackTrace();
			FacesMessageUtil.mensagem("Estado não encontrado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public List<Pessoa> listarPessoas(){
		try {
			this.pessoasListagem = pessoaDao.listarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.pessoasListagem;
	}
	
	public void adicionarEndereco(){
		try {
			Uf uf = ufDao.pesquisarPorId(idUfSelecionada);
			this.endereco.setUf(uf);
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.endereco.setPessoa(pessoa);
		this.enderecos.add(this.endereco);
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
		this.pessoaSelecionada = new Pessoa();
		this.enderecos         = new ArrayList<Endereco>();
		this.endereco          = new Endereco();		
	}
	
	public void excluirEndereco(){
		try {
			enderecoDao.excluir(enderecoParaExcluir);
			FacesMessageUtil.mensagem("Endereço Excluido com sucesso!");
			this.pessoa = pessoaDao.pesquisarPorId(pessoaSelecionada.getId());
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Pessoa> getPessoasListagem() {
		return pessoasListagem;
	}

	public void setPessoasListagem(List<Pessoa> pessoasListagem) {
		this.pessoasListagem = pessoasListagem;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
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

	public Long getIdUfSelecionada() {
		return idUfSelecionada;
	}

	public void setIdUfSelecionada(Long idUfSelecionada) {
		this.idUfSelecionada = idUfSelecionada;
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
}
