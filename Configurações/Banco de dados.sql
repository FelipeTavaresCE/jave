--------------Criação das Sequences------------------------
DROP SEQUENCE IF EXISTS seq_id_email;

CREATE SEQUENCE seq_id_email
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_endereco;

CREATE SEQUENCE seq_id_endereco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_pessoa;

CREATE SEQUENCE seq_id_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_telefone;

CREATE SEQUENCE seq_id_telefone
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_uf;

CREATE SEQUENCE seq_id_uf
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_usuario_sistema;

CREATE SEQUENCE seq_id_usuario_sistema
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_cliente;

CREATE SEQUENCE seq_id_cliente
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_configuracoes_sistema;

CREATE SEQUENCE seq_id_configuracoes_sistema
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_produto;

CREATE SEQUENCE seq_id_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
DROP SEQUENCE IF EXISTS seq_id_pedido;

CREATE SEQUENCE seq_id_pedido
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_pedido_item;

CREATE SEQUENCE seq_id_pedido_item
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

DROP SEQUENCE IF EXISTS seq_id_estoque;

CREATE SEQUENCE seq_id_estoque
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--------------Fim Criação das Sequences------------------------

--------------Criação das tabelas------------------------
--tb_telefone
DROP TABLE IF EXISTS tb_telefone;

CREATE TABLE tb_telefone (
    id bigint NOT NULL,
    codigo_de_area varchar(3),
    numero varchar(10),
    pessoa_id bigint
);

--tb_email
DROP TABLE IF EXISTS tb_email;

CREATE TABLE tb_email (
    id bigint NOT NULL,
    email varchar(100),
    pessoa_id bigint
);

--tb_endereco
DROP TABLE IF EXISTS tb_endereco cascade;

CREATE TABLE tb_endereco (
    id bigint NOT NULL,
    bairro varchar(100),
    cep varchar(10),
    cidade varchar(100),
    complemento varchar(255),
    numero integer,
    ponto_de_referencia varchar(255),
    rua varchar(100),
    pessoa_id bigint,
    uf_id bigint
);

--tb_usuario_sistema_perfil_acesso
DROP TABLE IF EXISTS tb_usuario_sistema_perfil_acesso;

CREATE TABLE tb_usuario_sistema_perfil_acesso(
   usuario_login varchar(50) not null,
   perfil_acesso_nome varchar(20) not null
);

--tb_usuario_sistema
DROP TABLE IF EXISTS tb_usuario_sistema;

CREATE TABLE tb_usuario_sistema(
    login varchar(50) not null,
    senha varchar(100) not null,
    ativo boolean not null,
    pessoa_id bigint not null
);

--tb_perfil_acesso
DROP TABLE IF EXISTS tb_perfil_acesso;

CREATE TABLE tb_perfil_acesso(
    nome varchar(20) not null,
    descricao varchar(50) NOT NULL
);

--tb_cliente
DROP TABLE IF EXISTS tb_cliente CASCADE;

CREATE TABLE tb_cliente(
    id bigint not null,
    dt_cadastro date not null,
    pessoa_id bigint not null
);

--tb_uf
DROP TABLE IF EXISTS tb_uf;

CREATE TABLE tb_uf (
    id bigint NOT NULL,
    nome varchar(50),
    sigla varchar(2)
);

--tb_configuracoes_sistema
DROP TABLE IF EXISTS tb_configuracoes_sistema;

CREATE TABLE tb_configuracoes_sistema(
    id bigint not null,
    logomarca bytea,
    mensagem_inicial varchar(100),
    sub_mensagem_inicial varchar(100)
    
);

--tb_pessoa
DROP TABLE IF EXISTS tb_pessoa;

CREATE TABLE tb_pessoa (
    id bigint NOT NULL,
    cnpj varchar(14),
    cpf varchar(11),
    datacadastro timestamp without time zone,
    datanascimento date,
    nome varchar(100),
    sexo varchar(10),
    foto bytea
);

--tb_produto
DROP TABLE IF EXISTS tb_produto CASCADE;

CREATE TABLE tb_produto(
    id bigint not null,
    nome varchar(60) not null,
    codigo_de_barras varchar(50) null,
    qr_code varchar(50),
    nome_fabricante varchar(50),
    codigo_referencia varchar(30),
    lote varchar(30),
    perecivel boolean,
    tipo_medida varchar(20),
    quantidade_volume numeric,
    preco numeric,
    desconto_percentual numeric,
    quantidade_disponivel integer,
    estoque_minimo integer,
    ativo boolean
);

--tb_status_pedido
DROP TABLE IF EXISTS tb_status_pedido CASCADE;

CREATE TABLE tb_status_pedido(
    id int not null,
    descricao varchar(50) not null
);

--tb_pedido
DROP TABLE IF EXISTS tb_pedido CASCADE;

CREATE TABLE tb_pedido(
    id bigint not null,
    data_hora timestamp without time zone not null, 
    valor_total numeric not null,
    vlr_desconto numeric null,
    status_pedido_id integer not null,
    cliente_id bigint null
);

--tb_pedido_item
DROP TABLE IF EXISTS tb_pedido_item;

CREATE TABLE tb_pedido_item(
    id bigint not null,
    quantidade integer not null,
    pedido_id bigint not null,
    produto_id bigint null
);

--tb_estoque
DROP TABLE IF EXISTS tb_estoque;

CREATE TABLE tb_estoque(
    id bigint not null,
    qtd_disponivel integer not null,
    preco_custo numeric not null,
    preco_venda numeric not null,
    data_reposicao timestamp without time zone,
    produto_id bigint null
);


--------------Fim da Criação das tabelas------------------------

-------------- Criação das Primary Keys ------------------------
ALTER TABLE ONLY tb_telefone
    ADD CONSTRAINT pk_tb_telefone PRIMARY KEY (id);

ALTER TABLE ONLY tb_email
    ADD CONSTRAINT pk_tb_email PRIMARY KEY (id);

ALTER TABLE ONLY tb_endereco
    ADD CONSTRAINT pk_tb_endereco PRIMARY KEY (id);

ALTER TABLE ONLY tb_pessoa
    ADD CONSTRAINT pk_tb_pessoa PRIMARY KEY (id);

ALTER TABLE ONLY tb_uf
    ADD CONSTRAINT pk_tb_uf PRIMARY KEY (id);

ALTER TABLE ONLY tb_usuario_sistema
    ADD CONSTRAINT pk_usuario_sistema PRIMARY KEY (login);

ALTER TABLE ONLY tb_perfil_acesso
    ADD CONSTRAINT pk_perfil_acesso PRIMARY KEY (nome);

ALTER TABLE tb_cliente
    ADD CONSTRAINT pk_cliente PRIMARY KEY (id);

ALTER TABLE tb_configuracoes_sistema
    ADD CONSTRAINT pk_config_sistema PRIMARY KEY (id);

ALTER TABLE tb_produto
    ADD CONSTRAINT pk_produto PRIMARY KEY (id);

ALTER TABLE tb_status_pedido
    ADD CONSTRAINT pk_status_pedido PRIMARY KEY (id);

ALTER TABLE tb_pedido
    ADD CONSTRAINT pk_pedido PRIMARY KEY (id);

ALTER TABLE tb_pedido_item
    ADD CONSTRAINT pk_pedido_item PRIMARY KEY (id);

-------------- Fim da Criação das Primary Keys ------------------------

-------------- Criação dos indices ---------------------------------
CREATE UNIQUE INDEX unq_idx_pessoa_cliente ON tb_cliente(pessoa_id);
-------------- Fim da Criação dos indices --------------------------

-------------- Criação das Foreign Keys ------------------------
ALTER TABLE ONLY tb_telefone
    ADD CONSTRAINT fk_telefone_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa(id);

ALTER TABLE ONLY tb_endereco
    ADD CONSTRAINT fk_endereco_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa(id);

ALTER TABLE ONLY tb_endereco
    ADD CONSTRAINT fk_endereco_uf_id FOREIGN KEY (uf_id) REFERENCES tb_uf(id);

ALTER TABLE ONLY tb_email
    ADD CONSTRAINT fk_email_id_pessoa FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa(id);

ALTER TABLE ONLY tb_usuario_sistema
    ADD CONSTRAINT fk_usuario_sistema_id_pessoa FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa(id);

ALTER TABLE ONLY tb_usuario_sistema_perfil_acesso 
    ADD CONSTRAINT fk_usu_sis_perfil_login FOREIGN KEY (usuario_login) REFERENCES tb_usuario_sistema(login);

ALTER TABLE ONLY tb_usuario_sistema_perfil_acesso 
    ADD CONSTRAINT fk_usu_sis_perfil_nome FOREIGN KEY (perfil_acesso_nome) REFERENCES tb_perfil_acesso(nome);

ALTER TABLE tb_cliente
    ADD CONSTRAINT fk_cliente_pessoa FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa (id);

ALTER TABLE tb_pedido
    ADD CONSTRAINT fk_pedido_status FOREIGN KEY (status_pedido_id) REFERENCES tb_status_pedido(id);

ALTER TABLE tb_pedido
    ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES tb_cliente(id);

ALTER TABLE tb_pedido_item
    ADD CONSTRAINT fk_pedido_item_pedido FOREIGN KEY (pedido_id) REFERENCES tb_pedido(id);

ALTER TABLE tb_pedido_item
    ADD CONSTRAINT fk_pedido_item_produto FOREIGN KEY (produto_id) REFERENCES tb_produto(id);

-------------- Fim Criação das Foreign Keys ------------------------

--------------Criação do Usuario---------------------------
--DROP ROLE IF EXISTS jave;

--CREATE ROLE jave
--  SUPERUSER CREATEDB CREATEROLE REPLICATION
--   VALID UNTIL 'infinity';

--------------- Fim da Criação do Usuario -----------------


INSERT INTO tb_uf VALUES(nextval('seq_id_uf'), 'Ceará', 'CE');
INSERT INTO tb_uf VALUES(nextval('seq_id_uf'), 'Bahia', 'BA');
INSERT INTO tb_uf VALUES(nextval('seq_id_uf'), 'Pernambuco', 'PE');

INSERT INTO tb_pessoa(id, nome, sexo, datacadastro) values (nextval('seq_id_pessoa'),'Administrador do Sistema', 'MASCULINO', now());
INSERT INTO tb_pessoa(id, nome, sexo, datacadastro) values (nextval('seq_id_pessoa'),'Cadastro', 'MASCULINO', now());

INSERT INTO tb_perfil_acesso values('ROLE_ADMIN', 'Administrador');
INSERT INTO tb_perfil_acesso values('ROLE_CADASTRO', 'Cadastro');
INSERT INTO tb_perfil_acesso values('ROLE_GERENCIA', 'Gerência');

INSERT INTO tb_usuario_sistema values(/*nextval('seq_id_usuario_sistema'), */'admin', md5('admin'), true, 1);
INSERT INTO tb_usuario_sistema values(/*nextval('seq_id_usuario_sistema'),*/ 'cadastro', md5('cadastro'), true, 2);

INSERT INTO tb_usuario_sistema_perfil_acesso values('admin', 'ROLE_ADMIN');
INSERT INTO tb_usuario_sistema_perfil_acesso values('cadastro', 'ROLE_CADASTRO');

INSERT INTO tb_configuracoes_sistema values(1, null, 'Nome da Empresa','Slogan da Empresa');

INSERT INTO tb_status_pedido values (0, 'Orçamento');
INSERT INTO tb_status_pedido values (1, 'Concretizado');
INSERT INTO tb_status_pedido values (2, 'Cancelado');