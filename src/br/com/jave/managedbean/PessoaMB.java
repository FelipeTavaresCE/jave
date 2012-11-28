package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.PessoaDao;
import br.com.jave.enums.Sexo;
import br.com.jave.modelo.Pessoa;
import br.com.jave.util.FacesMessageUtil;

@Controller
@ManagedBean
@ViewScoped
public class PessoaMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private PessoaDao pessoaDao;
	private Pessoa pessoa = new Pessoa();
	private List sexo;
	
	@Autowired
	public PessoaMB(PessoaDao pessoaDao){
		this.pessoaDao = pessoaDao;
		this.sexo = Arrays.asList(Sexo.values());  
	}
	
	public void gravar(){
		System.out.println("Entrou no metodo de salvar");
		FacesMessageUtil.mensagem("entrou na função");
		try {
			pessoaDao.gravar(this.pessoa);
			this.pessoa = new Pessoa();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao gravar a Pessoa.\n" + e.getMessage());
		}
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

	

	
	

}
