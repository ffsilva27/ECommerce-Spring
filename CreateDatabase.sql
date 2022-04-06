create database loja;

create table produto (
    id serial PRIMARY KEY,
    codigo varchar NOT NULL,
    nome varchar NOT NULL,
    preco double precision CHECK(preco > 0),
    qtde_disponivel int NOT NULL 
);

create table compra (
    id serial PRIMARY KEY,
    data_compra TIMESTAMP NOT NULL,
    cpf_cliente varchar NOT NULL,
    valor_total_compra double precision NOT NULL     
);

create table compra_produto(
    id_produto integer references produto(id),
    id_compra integer references compra(id),
    quantidade integer not null default 1,
    primary key(id_produto, id_compra)
);
   

insert into produto (codigo, nome, preco, qtde_disponivel) values ('A123', 'PC', 5000.0, 5);
insert into produto (codigo, nome, preco, qtde_disponivel) values ('B123', 'Iphone', 10000.0, 10);
  
insert into compra (data_compra, cpf_cliente, valor_total_compra) values ('2022-03-20', '12345',5000.0);
insert into compra (data_compra, cpf_cliente, valor_total_compra) values ('2022-03-21', '12345',15000.0);

insert into compra_produto values(1,2);
insert into compra_produto values(2,2);
insert into compra_produto values(1,1);

-- ESSE SELECT RELACIONA AS 2 TABELAS E SOMA O TOTAL DE PRODUTOS DE ACORDO COM O ID DA COMPRA
select sum(preco) from compra c 
inner join compra_produto cp on c.id = cp.id_compra 
inner join produto p on cp.id_produto = p.id
where id_compra = 1

-- ESSE SELECT SOMA AS COMPRAS E AGRUPA POR CPF SEPARANDO POR COMPRAS DIFERENTES
select sum(c.valor_total_compra), c.cpf_cliente  from compra c
group by c.cpf_cliente, c.id


-- CASO PRECISE DELETAR AS TABELAS
--DROP TABLE compra_produto 
--DROP TABLE produto 
--DROP TABLE compra 
