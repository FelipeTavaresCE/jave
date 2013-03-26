package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.PerfilDeAcessoDao;
import br.com.jave.dao.UsuarioSistemaDao;
import br.com.jave.modelo.PerfilDeAcesso;
import br.com.jave.modelo.Pessoa;
import br.com.jave.modelo.UsuarioSistema;
import br.com.jave.util.FacesMessageUtil;

@ManagedBean
@RequestScoped
@Controller
public class UsuarioSistemaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioSistemaDao usuarioSistemaDao;
	private UsuarioSistema usuarioSistema = new UsuarioSistema();
	private String senhaDeConfirmacao;
	private Pessoa pessoaJaExistente;
	private PerfilDeAcessoDao perfilDeAcessoDao;
	
	public UsuarioSistemaMB(){
		System.out.println("Entrou no construtor padrão do cadastro de usuário do sistema.");
	}
	
	@Autowired
	public UsuarioSistemaMB(UsuarioSistemaDao usuarioSistemaDao, PerfilDeAcessoDao perfilDeAcessoDao){
		this.usuarioSistemaDao = usuarioSistemaDao;
		this.perfilDeAcessoDao = perfilDeAcessoDao;
		adicionarNovoUsuario();
	}
	
	public void gravar(){
		try{
			usuarioSistema.setPerfilDeAcesso(adicionarPerfilDeAcesso());
			usuarioSistemaDao.gravar(usuarioSistema);
			FacesMessageUtil.mensagem("Usuário do sistema gravado com sucesso.", null);
		}catch(Exception e){
			FacesMessageUtil.erro("Erro ao gravar o usuario: \n", e.getMessage());
			System.out.println("Erro ao gravar o usuario: \n" +  e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void selecionarPessoaJaExistente(){
		this.usuarioSistema.setPessoa(pessoaJaExistente);		
	}
	
	public void adicionarNovoUsuario(){
		usuarioSistema = new UsuarioSistema();
	}
	
	private List<PerfilDeAcesso> adicionarPerfilDeAcesso(){
		if(usuarioSistema.getPerfilDeAcesso() == null)
			usuarioSistema.setPerfilDeAcesso(new ArrayList<PerfilDeAcesso>());
		
		List<PerfilDeAcesso> perfilDeAcessoUsuario = new ArrayList<PerfilDeAcesso>();		
		perfilDeAcessoUsuario.add(perfilDeAcessoDao.pesquisarPorNome("ROLE_ADMIN"));
		
		return perfilDeAcessoUsuario;
	}

	//Métodos GET e SET
	public UsuarioSistema getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public String getSenhaDeConfirmacao() {
		return senhaDeConfirmacao;
	}

	public void setSenhaDeConfirmacao(String senhaDeConfirmacao) {
		this.senhaDeConfirmacao = senhaDeConfirmacao;
	}

	public Pessoa getPessoaJaExistente() {
		return pessoaJaExistente;
	}

	public void setPessoaJaExistente(Pessoa pessoaJaExistente) {
		this.pessoaJaExistente = pessoaJaExistente;
	}
}
