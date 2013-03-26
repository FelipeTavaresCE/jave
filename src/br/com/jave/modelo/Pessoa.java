package br.com.jave.modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.jave.enums.Sexo;

@Entity
@Table(name = "tb_pessoa")
@NamedQueries({
	@NamedQuery(name = "pessoaListarTodos", query = "SELECT p FROM Pessoa p"),
	@NamedQuery(name = "pessoaPesquisarPorNomeCpfCnpj",
				query = "SELECT p FROM Pessoa p WHERE (p.nome like :nome) or (p.cpf = :cpf) or (p.cnpj = :cnpj)")
})
public class Pessoa implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_id_pessoa", sequenceName = "seq_id_pessoa", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_pessoa")
	private Long id;
	
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datanascimento")
	private Date dataNascimento;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	private String cpf;
	private String cnpj;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datacadastro")
	private Date dataCadastro;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="pessoa")
	private List<Endereco> enderecos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="pessoa")
	private List<Telefone> contatos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="pessoa")
	private List<Email> emails;
	
	private byte[] foto;
	
	public Pessoa(){
		this.dataCadastro = new Date();
		System.out.println("NOVA PESSOA PARA USUARIO SISTEMA CRiADO.");
	}
	
	/*Metodos Get e Set*/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
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
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public List<Telefone> getContatos() {
		return contatos;
	}
	public void setContatos(List<Telefone> contatos) {
		this.contatos = contatos;
	}
	public List<Email> getEmails() {
		return emails;
	}
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result
				+ ((contatos == null) ? 0 : contatos.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((emails == null) ? 0 : emails.hashCode());
		result = prime * result
				+ ((enderecos == null) ? 0 : enderecos.hashCode());
		result = prime * result + Arrays.hashCode(foto);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (contatos == null) {
			if (other.contatos != null)
				return false;
		} else if (!contatos.equals(other.contatos))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (emails == null) {
			if (other.emails != null)
				return false;
		} else if (!emails.equals(other.emails))
			return false;
		if (enderecos == null) {
			if (other.enderecos != null)
				return false;
		} else if (!enderecos.equals(other.enderecos))
			return false;
		if (!Arrays.equals(foto, other.foto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}

}
