package br.com.jave.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

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
	@NamedQuery(name = "produtoPesquisarPorId", query = "SELECT p FROM Produto p WHERE p.id = :id")
})
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "seq_id_produto", sequenceName = "seq_id_produto", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_produto")
	private Long id;	
	
	private String nome;
	private String codigoDeBarras;
	private String qrCode;
	private String nomeFabricante;
	private String codigoReferencia;
	private String lote;
	private Boolean perecivel;
	private String tipoMedida; //criar um ENUM de unidade de medida ou uma tabela de medida (quilo, unidade, litro e etc)
	private Float quantidadeVolume;
	private BigDecimal preco;
	private Float descontoPercentual;
	private Integer quantidadeDisponivel;
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
	
	
	

}
