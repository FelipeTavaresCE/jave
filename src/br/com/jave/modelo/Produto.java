package br.com.jave.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tb_produto")
@NamedQueries({
	@NamedQuery(name = "produtoListarTodos", query = "SELECT p FROM Produto p"),
	@NamedQuery(name = "produtoPesquisarPorCodBarras", query = "SELECT p FROM Produto p WHERE p.codigoDeBarras = :codigoDeBarras")
})
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_id_produto", sequenceName = "seq_id_produto", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_produto")
	private Long id;	
	
	
	private String nome;
	
	@Column(name="codigo_de_barras")
	private String codigoDeBarras;
	
	@Column(name="qr_code")
	private String qrCode;
	
	@Column(name="nome_fabricante")
	private String nomeFabricante;
	
	@Column(name="codigo_referencia")
	private String codigoReferencia;
	
	private String lote;
	
	private Boolean perecivel;
	
	@Column(name="tipo_medida")
	private String tipoMedida; //criar um ENUM de unidade de medida ou uma tabela de medida (quilo, unidade, litro e etc)
	
	@Column(name="quantidade_volume")
	private Float quantidadeVolume;
	
	private BigDecimal preco;
	
	@Column(name="desconto_percentual")
	private Float descontoPercentual;
	
	@Column(name="quantidade_disponivel")
	private Integer quantidadeDisponivel;
	
	@Column(name="estoque_minimo")
	private Integer estoqueMinimo;
	
	private Boolean ativo;
	
	
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
	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}
	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getNomeFabricante() {
		return nomeFabricante;
	}
	public void setNomeFabricante(String nomeFabricante) {
		this.nomeFabricante = nomeFabricante;
	}
	public String getCodigoReferencia() {
		return codigoReferencia;
	}
	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public Boolean getPerecivel() {
		return perecivel;
	}
	public void setPerecivel(Boolean perecivel) {
		this.perecivel = perecivel;
	}
	public String getTipoMedida() {
		return tipoMedida;
	}
	public void setTipoMedida(String tipoMedida) {
		this.tipoMedida = tipoMedida;
	}
	public Float getQuantidadeVolume() {
		return quantidadeVolume;
	}
	public void setQuantidadeVolume(Float quantidadeVolume) {
		this.quantidadeVolume = quantidadeVolume;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Float getDescontoPercentual() {
		return descontoPercentual;
	}
	public void setDescontoPercentual(Float descontoPercentual) {
		this.descontoPercentual = descontoPercentual;
	}
	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}
	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}
	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}
	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
