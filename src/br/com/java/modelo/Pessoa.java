package br.com.java.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_pessoa")
@NamedQuery(name = "pessoaListarTodos", query = "SELECT p FROM Pessoa p")
public class Pessoa implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_id_pessoa", sequenceName = "seq_id_pessoa", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_pessoa")
	private Long id;
	private String nome;
	private Date dataNascimento;
	//@OneToOne
	//private Sexo sexo;
	private String cpf;
	private String cnpj;
	private Date dataCadastro;
	@OneToMany
	@JoinColumn(name = "pessoa_id")
	private List<Endereco> enderecos;
	@OneToMany
	@JoinColumn(name = "pessoa_id")
	private List<Telefone> contatos;
	@OneToMany
	@JoinColumn(name = "pessoa_id")	
	private List<Email> emails;
	
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
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
/*	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
*/	public String getCpf() {
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
	

}
