--------------Criação do Usuario---------------------------
CREATE ROLE jave
  SUPERUSER CREATEDB CREATEROLE REPLICATION
   VALID UNTIL 'infinity';

--------------- Fim da Criação do Usuario -----------------

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
--------------Fim Criação das Sequences------------------------

--------------Criação das tabelas------------------------
DROP TABLE IF EXISTS tb_telefone;

CREATE TABLE tb_telefone (
    id bigint NOT NULL,
    codigodearea varchar(3),
    numero varchar(10),
    pessoa_id bigint
);

DROP TABLE IF EXISTS authority;

CREATE TABLE authority
(
  name character varying(255) NOT NULL,
  CONSTRAINT authority_pkey PRIMARY KEY (name )
);

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  username character varying(40) NOT NULL,
  enable boolean,
  password character varying(40),
  CONSTRAINT users_pkey PRIMARY KEY (username )
);

DROP TABLE IF EXISTS user_auth;

CREATE TABLE user_auth
(
  user_username character varying(40) NOT NULL,
  auth_authority character varying(255) NOT NULL,
  CONSTRAINT fk1434519c4cf16b0b FOREIGN KEY (user_username)
      REFERENCES users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk1434519c7b6bb1f9 FOREIGN KEY (auth_authority)
      REFERENCES authority (name) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)


DROP TABLE IF EXISTS tb_email;

CREATE TABLE tb_email (
    id bigint NOT NULL,
    email varchar(100),
    pessoa_id bigint
);

DROP TABLE IF EXISTS tb_endereco;

CREATE TABLE tb_endereco (
    id bigint NOT NULL,
    bairro varchar(100),
    cep varchar(10),
    cidade varchar(100),
    complemento varchar(255),
    numero integer,
    pontodereferencia varchar(255),
    rua varchar(100),
    pessoa_id bigint,
    uf_id bigint
);

DROP TABLE IF EXISTS tb_pessoa;

CREATE TABLE tb_pessoa (
    id bigint NOT NULL,
    cnpj varchar(14),
    cpf varchar(11),
    datacadastro timestamp without time zone,
    datanascimento timestamp without time zone,
    nome varchar(100),
    sexo varchar(10),
    foto bytea
);

DROP TABLE IF EXISTS tb_uf;

CREATE TABLE tb_uf (
    id bigint NOT NULL,
    nome varchar(50),
    sigla varchar(2)
);

DROP TABLE IF EXISTS tb_usuario_sistema;

CREATE TABLE tb_usuario_sistema(
    id bigint NOT NULL,
    login varchar(50) not null,
    senha varchar(100) not null,
    ativo boolean not null,
    pessoa_id bigint not null
);

DROP TABLE IF EXISTS tb_perfil_acesso;

CREATE TABLE tb_perfil_acesso(
    nome varchar(20) not null
);

DROP TABLE IF EXISTS tb_usuario_sistema_perfil_acesso;

CREATE TABLE tb_usuario_sistema_perfil_acesso(
   usuario_login varchar(50) not null,
   perfil_acesso_nome varchar(20) not null
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
    ADD CONSTRAINT pk_usuario_sistema PRIMARY KEY (id);

ALTER TABLE ONLY tb_perfil_acesso
    ADD CONSTRAINT pk_perfil_acesso PRIMARY KEY (nome);

-------------- Fim da Criação das Primary Keys ------------------------

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

-------------- Fim Criação das Foreign Keys ------------------------

-------------- Criação dos indices ------------------------
CREATE UNIQUE INDEX unq_idx_login ON tb_usuario_sistema(login);
-------------- Fim da Criação dos indices ------------------------

INSERT INTO tb_uf VALUES(nextval('seq_id_uf'), 'Ceará', 'CE');
INSERT INTO tb_uf VALUES(nextval('seq_id_uf'), 'Bahia', 'BA');
INSERT INTO tb_uf VALUES(nextval('seq_id_uf'), 'Pernambuco', 'PE');

INSERT INTO tb_perfil_acesso values('ROLE_ADMIN');
INSERT INTO tb_usuario_sistema values(nextval('seq_id_usuario_sistema'), 'admin', md5('admin'), true, 1);
INSERT INTO tb_usuario_sistema_perfil_acesso values('admin', 'ROLE_ADMIN');
