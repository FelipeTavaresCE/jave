package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.PerfilDeAcessoDao;
import br.com.jave.dao.UsuarioSistemaDao;
import br.com.jave.enums.Sexo;
import br.com.jave.modelo.Pessoa;
import br.com.jave.modelo.UsuarioSistema;
import br.com.jave.util.FacesMessageUtil;

@ManagedBean
@RequestScoped
@Controller
public class UsuarioSistemaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioSistemaDao usuarioSistemaDao;
	private List<UsuarioSistema> usuariosSistemaListagem;
	private UsuarioSistema usuarioSistema;
	private PerfilDeAcessoDao perfilDeAcessoDao;
	private String senhaDeConfirmacao;
	private Sexo sexo;
	
	public UsuarioSistemaMB(){}
	
	@Autowired
	public UsuarioSistemaMB(UsuarioSistemaDao usuarioSistemaDao, UsuarioSistema usuarioSistema){
		this.usuarioSistemaDao = usuarioSistemaDao;
		this.usuarioSistema = usuarioSistema;
	}
	
	public void gravar(){
		try{
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("teste manual");
			pessoa.setSexo(Sexo.MASCULINO);
			pessoa.setCpf("00434926302");
			pessoa.setCnpj("00000000000");
			pessoa.setDataNascimento(new Date());
			
			usuarioSistema.setPessoa(pessoa);
			usuarioSistemaDao.gravar(usuarioSistema);
			FacesMessageUtil.mensagem("Usuário do sistema gravado com sucesso.", null);
		}catch(Exception e){
			FacesMessageUtil.erro("Erro ao gravar o usuario: \n", e.getMessage());
			System.out.println("Erro ao gravar o usuario: \n" +  e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<UsuarioSistema> listarUsuarios(){
		try {
			usuariosSistemaListagem = usuarioSistemaDao.listarTodos();
		} catch (Exception e) {
			FacesMessageUtil.erro("Erro ao Listar os clientes.", e.getMessage());
			e.printStackTrace();
		}
		return usuariosSistemaListagem;
	}
	
	public void adicionarNovoUsuario(){
		usuarioSistema = new UsuarioSistema();
		usuarioSistema.setPessoa(new Pessoa());
		senhaDeConfirmacao = null;
		sexo = null;
		System.out.println("Entrou na função de adicionar novo usuario.");
	}
	
	public List<UsuarioSistema> getUsuariosSistemaListagem() {
		return usuariosSistemaListagem;
	}

	public void setUsuariosSistemaListagem(
			List<UsuarioSistema> usuariosSistemaListagem) {
		this.usuariosSistemaListagem = usuariosSistemaListagem;
	}

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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}
