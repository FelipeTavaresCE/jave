package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.PessoaDao;
import br.com.jave.modelo.Pessoa;
import br.com.jave.util.FacesMessageUtil;

@Controller
@ManagedBean
@SessionScoped
public class PesquisaPessoaMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private PessoaDao pessoaDao;
	private String nome;
	private String cpf;
	private String cnpj;
	private List<Pessoa> pessoasListagem = new ArrayList<Pessoa>();
	
	public PesquisaPessoaMB(){}
	
	@Autowired
	public PesquisaPessoaMB(PessoaDao pessoaDao){
		this.pessoaDao = pessoaDao;
	}
	
	public void pesquisar() {
		try {
			pessoasListagem = pessoaDao.pesquisarPorNomeCpfCnpj(nome.toUpperCase(), cpf, cnpj);
		} catch (Exception e) {
			FacesMessageUtil.aviso("Erro ao Listar as pessoas.");
			e.printStackTrace();
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Pessoa> getPessoasListagem() {
		return pessoasListagem;
	}

	public void setPessoasListagem(List<Pessoa> pessoasListagem) {
		this.pessoasListagem = pessoasListagem;
	}

	
}
