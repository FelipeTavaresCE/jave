package br.com.jave.managedbean;

import java.io.Serializable;

import javax.persistence.NoResultException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.jave.dao.ConfiguracoesDoSistemaDao;
import br.com.jave.modelo.ConfiguracoesDoSistema;
import br.com.jave.util.FacesMessageUtil;
import br.com.jave.util.UploadDeImagem;

@Component
@Scope("singleton")
public class LogomarcaUpload implements Serializable{

	private static final long serialVersionUID = 1L;
	private DefaultStreamedContent fotoGerada;
	private ConfiguracoesDoSistemaDao configuracoesDoSistemaDao;
	private ConfiguracoesDoSistema configuracoesDoSistema;
	private boolean logoAlterada = false;
	private byte[] conteudoDoArquivo;
	
	public LogomarcaUpload(){
	}
	
	@Autowired
	public LogomarcaUpload(ConfiguracoesDoSistemaDao configuracoesDoSistemaDao){
		this.configuracoesDoSistemaDao = configuracoesDoSistemaDao;
	}	
	
	public void upload(FileUploadEvent event) throws Exception {
		this.logoAlterada = true;
		this.conteudoDoArquivo = event.getFile().getContents();		
		this.fotoGerada = UploadDeImagem.gerarApresentacaoTela(this.conteudoDoArquivo);
    }
	
	public void carregarConfiguracoes(){
		try {
			this.configuracoesDoSistema = configuracoesDoSistemaDao.pesquisarPorId(1L);
			if(this.configuracoesDoSistema == null){
				FacesMessageUtil.aviso("Por favor inserir logomarca!");
		}
		
		if(logoAlterada == false){
			this.fotoGerada = UploadDeImagem.gerarApresentacaoTela(configuracoesDoSistema.getLogomarca());
		}else{
			this.fotoGerada = UploadDeImagem.gerarApresentacaoTela(this.conteudoDoArquivo);
		}
		
		} catch (NoResultException e) {
			FacesMessageUtil.aviso("As configurações do sistema ainda não foram setadas!");
		}
		catch (NullPointerException ne) {
			FacesMessageUtil.aviso("Por favor inserir logomarca!");
		}
		catch(Exception e){
			FacesMessageUtil.aviso("Erro ao carregar as configurações do sistema!\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void gravarConfiguracoes(){
		try {
			if(logoAlterada == true){
				configuracoesDoSistema.setLogomarca(conteudoDoArquivo);
			}
			this.configuracoesDoSistemaDao.gravar(configuracoesDoSistema);
			logoAlterada = false;
			FacesMessageUtil.mensagem("Configurações gravadas com sucesso!");
		} catch (Exception e) {
			FacesMessageUtil.erro("Erro ao gravar as configurações.\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void removerLogo(){
		this.conteudoDoArquivo = null;
		logoAlterada = true;		
	}

	public DefaultStreamedContent getFotoGerada() {
		return fotoGerada;
	}

	public void setFotoGerada(DefaultStreamedContent fotoGerada) {
		this.fotoGerada = fotoGerada;
	}
	
	public ConfiguracoesDoSistema getConfiguracoesDoSistema() {
		return configuracoesDoSistema;
	}

	public void setConfiguracoesDoSistema(
			ConfiguracoesDoSistema configuracoesDoSistema) {
		this.configuracoesDoSistema = configuracoesDoSistema;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}