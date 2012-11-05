package br.com.java.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_telefone")
@NamedQuery(name = "telefoneListarTodos", query = "SELECT t FROM Telefone t")
public class Telefone implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seq_id_telefone", sequenceName = "seq_id_telefone", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_telefone")
	private Long id;
	private String codigoDeArea;
	private String numero;
	@ManyToOne
	private Pessoa pessoa;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getCodigoDeArea() {
		return codigoDeArea;
	}
	public void setCodigoDeArea(String codigoDeArea) {
		this.codigoDeArea = codigoDeArea;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoDeArea == null) ? 0 : codigoDeArea.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		Telefone other = (Telefone) obj;
		if (codigoDeArea == null) {
			if (other.codigoDeArea != null)
				return false;
		} else if (!codigoDeArea.equals(other.codigoDeArea))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}
	
}