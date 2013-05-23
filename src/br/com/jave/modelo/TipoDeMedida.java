package br.com.jave.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import br.com.jave.converter.BaseEntity;

@Component
@Entity
@Table(name = "tb_tipo_medida")
@NamedQuery(name = "tipoDeMedidaListarTodos", query = "SELECT tm FROM TipoDeMedida tm ORDER BY tm.descricao")
public class TipoDeMedida implements BaseEntity,Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_id_tipo_medida", sequenceName = "seq_id_tipo_medida", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_tipo_medida")
	private Long id;
    private String descricao;
    private String sigla;
    private Boolean fl_ativo;
    
    public TipoDeMedida(){
    	setFl_ativo(true);
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public Boolean getFl_ativo() {
		return fl_ativo;
	}
	public void setFl_ativo(Boolean fl_ativo) {
		this.fl_ativo = fl_ativo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((fl_ativo == null) ? 0 : fl_ativo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		TipoDeMedida other = (TipoDeMedida) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fl_ativo == null) {
			if (other.fl_ativo != null)
				return false;
		} else if (!fl_ativo.equals(other.fl_ativo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}
}
