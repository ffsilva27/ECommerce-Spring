# :man_technologist: Curso Web Full Stack - Let's Code :man_student:

## Projeto Módulo 09 - Banco de Dados - PostgreSQL :rocket:

Neste módulo podemos ampliar nossos conhecimentos quanto ao funcionamento do framework Spring(Data JPA / Security / MVC), com a construção de métodos HTTP, 
autenticação para proteção de rota e padrões de arquietetura REST no desenvolvimento de uma API que tem como objetivo possibilitar a criação e compra de produtos, 
e criação de usuários.

___________________________________________________________________________________________________________________________________________________________________

### :scroll: Atividade

Construção de uma API que possibilite:
 - Criação de produtos;
 - Listagem dos produtos cadastrados (com paginação);
 - Informar, na rota acima, dados do produto conforme filtro pelo código;
 - Compra do(s) produto(s) cadastrado(s);
 - Listagem das compras realizadas (com paginação);
 - Informar, na rota acima, dados da compra conforme filtro pelo cpf;
 - Criação de usuário (utilizando criptografia - BCrypt).

#### Requisitos funcionais
Deve ser criada uma API utilizando os padrões REST, assim como todas as rotas devem ter autenticação básica. A aplicação deve usar banco de dados relacional.
___________________________________________________________________________________________________________________________________________________________________

### :chains: Tecnologias
- Java
- Spring (Data JPA / Security / MVC)
- Banco de dados PostgreSQL
- Lombok
- Gradle

Obs.: Todos os comandos SQL para criação das tabelas estão no arquivo CreateDataBase.sql localizado na raiz da aplicação.

___________________________________________________________________________________________________________________________________________________________________

### :man_technologist: Implementações

#### :chart: Criar produtos
O produto deve conter os seguintes dados: código do produto (implementado lógica para geração pela aplicação), preço e quantidade disponível.

##### Método HTTP para criação do produto/JSON
 - (POST) `/produto`
```json
{
	"nome": "Smart Watch",
	"preco": 1500.00,
	"qtdeDisponivel": 22
}
```
##### Retorno da aplicação/JSON
```json
{
	"codigo": "V905",
	"nome": "Smart Watch",
	"preco": 1500.0,
	"qtde_disponivel": 22
}
```
<br>

#### :chart: Listar os produtos cadastrados
A rota deve possibilitar a geração da listagem (paginada) de todos os produtos cadastrados, assim como o filtro por código do produto. 

##### Método HTTP para obter a listagem total dos produtos (paginada)
 - (GET) `/produto`

##### Método HTTP para obter um produto especificando seu código
 - (GET) `/produto?codigo={codigoDoProduto}`

Obs.: Para obtenção das informações de um produtos específico deve-se informar o código deste na query, conforme acima (sem as chaves).

##### Retorno da aplicação/JSON
```json
{
	"content": [
		{
			"codigo": "V905",
			"nome": "Smart Watch",
			"preco": 1500.0,
			"qtde_disponivel": 22
		}
	],
	"pageable": {
		"sort": {
			"empty": true,
			"sorted": false,
			"unsorted": true
		},
		"offset": 0,
		"pageNumber": 0,
		"pageSize": 20,
		"unpaged": false,
		"paged": true
	},
	"last": true,
	"totalElements": 1,
	"totalPages": 1,
	"size": 20,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"first": true,
	"numberOfElements": 1,
	"empty": false
}
```
<br>

#### :chart: Efetuar uma compra
A aplicação possibilita que os produtos cadastrados possam ser adiquiridos, sendo os elementos mínimos para o correto funcionamento
a inserção dos seguintes dados: data da compra, cpf do cliente, código do produto e quantidade.

##### Método HTTP para criação do produto/JSON
 - (POST) `/compra`

```json
{
	"data": "2022-04-02T13:34:00.000",
	"cpf": "123456789",
	"produtos": 
		{
			"V905": 2
		}
}
```

Obs.: É possivel adicionar vários produtos à mesma compra.

##### Retorno da aplicação/JSON
```json
{
	"data_compra": "2022-04-02T13:34:00",
	"cpf_cliente": "123456789",
	"valor_total_compra": 3000.0,
	"produtos": [
		{
			"codigo": "V905",
			"nome": "Smart Watch",
			"preco_unitario": 1500.0,
			"quantidade": 2
		}
	]
}
```
<br>

#### :chart: Listar compras
A rota deve possibilitar a geração da listagem (paginada) de todas as compras realizadas, assim como o filtro por cpf do cliente. 

##### Método HTTP para obter a listagem total dos produtos (paginada)
 - (GET) `/compra`

##### Método HTTP para obter um produto especificando seu código
 - (GET) `/compra?cpf={cpfDoCliente}`

Obs.: Para obtenção das informações de compra sobre um cliente específico, deve-se informar o cpf do cliente na query, conforme acima (sem as chaves).

##### Retorno da aplicação/JSON
```json
{
	"content": [
		{
			"data_compra": "2022-04-02T13:34:00",
			"cpf_cliente": "123456789",
			"valor_total_compra": 3000.0,
			"produtos": [
				{
					"codigo": "V905",
					"nome": "Smart Watch",
					"preco_unitario": 1500.0,
					"quantidade": 2
				}
			]
		}
	],
	"pageable": {
		"sort": {
			"empty": true,
			"sorted": false,
			"unsorted": true
		},
		"offset": 0,
		"pageNumber": 0,
		"pageSize": 20,
		"unpaged": false,
		"paged": true
	},
	"last": true,
	"totalElements": 1,
	"totalPages": 1,
	"size": 20,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"first": true,
	"numberOfElements": 1,
	"empty": false
}
```


#### :chart: Criação de usuário
Todas as rotas devem possuir autenticação, sendo assim para acessa-las o usuário deve possuir cadastro. As informações para a realização
do cadastro do usuário em sistema são: nome de usuário, senha e nível de autoridade.

##### Método HTTP para criação do produto/JSON
 - (POST) `/user`
```json
{
  "userName": "user02",
  "password": "user02",
  "authority": "CLIENTE"
}
```

##### Retorno da aplicação
`Status Code - 201 - Created`


## :man_technologist: Desenvolvedores<br>
[Filipe Silva](https://github.com/ffsilva27) , 
[Felipe Garé](https://github.com/FelipeRodriguesGare)
