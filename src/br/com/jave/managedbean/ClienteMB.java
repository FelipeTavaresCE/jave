package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.EnderecoDao;
import br.com.jave.dao.PessoaDao;
import br.com.jave.enums.Sexo;
import br.com.jave.modelo.Endereco;
import br.com.jave.modelo.Pessoa;
import br.com.jave.util.FacesMessageUtil;

@Controller
@ManagedBean
@ViewScoped
public class ClienteMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private PessoaDao pessoaDao;
	private EnderecoDao enderecoDao;
	private Pessoa pessoa = new Pessoa();
	private Endereco endereco = new Endereco();
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	private List<Pessoa> pessoasListagem;
	private Pessoa pessoaSelecionada;
	private List sexo;
	
	public ClienteMB(){}
	
	@Autowired
	public ClienteMB(PessoaDao pessoaDao, EnderecoDao enderecoDao){
		this.pessoaDao = pessoaDao;
		this.enderecoDao = enderecoDao;
		this.sexo = Arrays.asList(Sexo.values());
		listarPessoas();
	}
	
	public void gravar(){
		try {			
			pessoa.setEnderecos(enderecos);
			pessoaDao.gravar(this.pessoa);			
			this.pessoa = new Pessoa();
			this.pessoaSelecionada = null;
			listarPessoas();
			FacesMessageUtil.mensagem("Cliente Gravado com sucesso.");
		} catch (Exception e) {
			FacesMessageUtil.erro("Ocorreu um erro ao gravar o cliente." + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEdicao(){
		try {
			this.pessoa = pessoaDao.pesquisarPorId(pessoaSelecionada.getId());
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
		this.enderecos.add(endereco);
		this.endereco = new Endereco();
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
	
	public List getSexo() {
		return sexo;
	}

	public void setSexo(List sexo) {
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
}
