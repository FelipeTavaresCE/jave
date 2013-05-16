--teste para a tela de cadastro de clientes
create or replace function insereUmMilhaoCliente() returns void as $$
declare
  seqPessoa bigint;
  seqCliente bigint;
  seqEndereco bigint;
  seqTelefone bigint;
  seqEmail bigint;
begin
 for i in 1..10000 loop

   seqPessoa := nextval('seq_id_pessoa');
   seqCliente := nextval('seq_cod_cliente');
   seqEndereco := nextval('seq_id_endereco');
   seqTelefone := nextval('seq_id_telefone');
   seqEmail := nextval('seq_id_email');

   insert into tb_pessoa values(seqPessoa, seqPessoa, seqPessoa, now(), '1986-08-14', 'CLIENTE '||seqPessoa, 'MASCULINO', null);
   insert into tb_cliente values(seqCliente, now(), seqPessoa);
   insert into tb_endereco values(seqEndereco, 'BAIRRO '||seqEndereco, seqEndereco, 'CIDADE '||seqEndereco, null, seqEndereco, null, 'RUA '||seqEndereco, seqPessoa, 1);
   insert into tb_telefone values (seqTelefone, '85', '878501888', seqPessoa);
   insert into tb_email values(seqEmail, 'email@'||seqEmail||'com', seqPessoa);   
 end loop;
end;
$$ LANGUAGE plpgsql;

select insereUmMilhaoCliente();
