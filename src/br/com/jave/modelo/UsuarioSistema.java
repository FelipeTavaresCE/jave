package br.com.jave.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.jave.util.Criptografia;

@Entity
@Table(name = "tb_usuario_sistema")
@NamedQueries({
	@NamedQuery(name = "usuarioSistemaListarTodos", query = "SELECT u FROM UsuarioSistema u"),
	@NamedQuery(name = "usuarioSistemaValidarUsuario",
	            query = "SELECT u FROM UsuarioSistema u WHERE u.login = :login and u.senha = :senha and u.ativo = 'true'")
})
public class UsuarioSistema implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_id_usuario_sistema", sequenceName = "seq_id_usuario_sistema", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_usuario_sistema")
	private Long id;
	
	private String login;
	
	private String senha;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	private Boolean ativo;
	@ManyToMany
	@JoinTable(name="tb_usuario_sistema_perfil_acesso",
		joinColumns={@JoinColumn(name="usuario_login")},
		inverseJoinColumns={@JoinColumn(name="perfil_acesso_nome")}
	)
	private List<PerfilDeAcesso> perfilDeAcesso;
	
	public UsuarioSistema(){
		setAtivo(true);
		setPessoa(new Pessoa());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = Criptografia.md5(senha);
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	public List<PerfilDeAcesso> getPerfilDeAcesso() {
		return perfilDeAcesso;
	}
	public void setPerfilDeAcesso(List<PerfilDeAcesso> perfilDeAcesso) {
		this.perfilDeAcesso = perfilDeAcesso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		UsuarioSistema other = (UsuarioSistema) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}	
}
