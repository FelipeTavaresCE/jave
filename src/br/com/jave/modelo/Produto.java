package br.com.jave.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
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
	
	
	

}
