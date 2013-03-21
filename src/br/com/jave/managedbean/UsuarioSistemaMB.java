package br.com.jave.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jave.dao.PerfilDeAcessoDao;
import br.com.jave.dao.UsuarioSistemaDao;
import br.com.jave.enums.Sexo;
import br.com.jave.modelo.PerfilDeAcesso;
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
	private List<PerfilDeAcesso> perfiesDeAcessoOrigem;  
    private List<PerfilDeAcesso> perfiesDeAcessoDestino;	
	
	private DualListModel<PerfilDeAcesso> perfiesDeAcesso;
	
	public UsuarioSistemaMB(){}
	
	@Autowired
	public UsuarioSistemaMB(UsuarioSistemaDao usuarioSistemaDao, UsuarioSistema usuarioSistema, PerfilDeAcessoDao perfilDeAcessoDao){
		this.usuarioSistemaDao = usuarioSistemaDao;
		this.usuarioSistema = usuarioSistema;
		this.perfilDeAcessoDao = perfilDeAcessoDao;
		
        try{
    		perfiesDeAcessoOrigem = perfilDeAcessoDao.listarTodos();  
            perfiesDeAcessoDestino = new ArrayList<PerfilDeAcesso>();
            setPerfiesDeAcesso(new DualListModel<PerfilDeAcesso>(perfiesDeAcessoOrigem, perfiesDeAcessoDestino));
        	
        }catch(Exception e){
        	System.out.println("ERRO AO LISTAR OS PERFIES DE ACESSOS" + e.getMessage());
        	e.printStackTrace();
        }
        
	}
	
	public void gravar(){
		try{
			usuarioSistemaDao.gravar(usuarioSistema);
			FacesMessageUtil.mensagem("Usuário do sistema gravado com sucesso.");
		}catch(Exception e){
			FacesMessageUtil.erro("Erro ao gravar o usuario", e.getMessage());
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
		senhaDeConfirmacao = null;
		sexo = null;
		System.out.println("Entrou na função de adicionar novo usuario.");
	}
	
	public void selecionarPessoa(){
		
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

	public DualListModel<PerfilDeAcesso> getPerfiesDeAcesso() {
		return perfiesDeAcesso;
	}

	public void setPerfiesDeAcesso(DualListModel<PerfilDeAcesso> perfiesDeAcesso) {
		this.perfiesDeAcesso = perfiesDeAcesso;
	}

}
