package br.com.jave.modelo;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_configuracoes_sistema")
public class ConfiguracoesDoSistema implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(sequenceName = "seq_id_configuracoes_sistema", name = "seqIdConguracoesSistema", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqIdConguracoesSistema")
	private Long id;
	private byte[] logomarca;
	@Column(name = "mensagem_inicial")
	private String mensagemInicial;
	@Column(name = "sub_mensagem_inicial")
	private String subMensagemInicial;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getLogomarca() {
		return logomarca;
	}
	public void setLogomarca(byte[] logomarca) {
		this.logomarca = logomarca;
	}
	public String getMensagemInicial() {
		return mensagemInicial;
	}
	public void setMensagemInicial(String mensagemInicial) {
		this.mensagemInicial = mensagemInicial;
	}
	public String getSubMensagemInicial() {
		return subMensagemInicial;
	}
	public void setSubMensagemInicial(String subMensagemInicial) {
		this.subMensagemInicial = subMensagemInicial;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(logomarca);
		result = prime * result
				+ ((mensagemInicial == null) ? 0 : mensagemInicial.hashCode());
		result = prime
				* result
				+ ((subMensagemInicial == null) ? 0 : subMensagemInicial
						.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfiguracoesDoSistema other = (ConfiguracoesDoSistema) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(logomarca, other.logomarca))
			return false;
		if (mensagemInicial == null) {
			if (other.mensagemInicial != null)
				return false;
		} else if (!mensagemInicial.equals(other.mensagemInicial))
			return false;
		if (subMensagemInicial == null) {
			if (other.subMensagemInicial != null)
				return false;
		} else if (!subMensagemInicial.equals(other.subMensagemInicial))
			return false;
		return true;
	}

}
