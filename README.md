# Hotel

### Objetivo

Desenvolver uma aplicação (Somente o backend) que possibilite realizar o cadastro de hóspedes e o check in.

### Requisitos funcionais
* Um CRUDL para o cadastro de hóspedes;
* No check in deve ser possível buscar hóspedes cadastrados pelo nome, documento ou telefone;
* Consultar hóspedes que já realizaram o check in e não estão mais no hotel;
* Consultar hóspedes que ainda estão no hotel;
* As consultas devem apresentar o valor (valor total e o valor da última hospedagem) já gasto pelo hóspede no hotel;

### Régras de negócio
* Uma diária no hotel de segunda à sexta custa R$120,00;
* Uma diária no hotel em finais de semana custa R$150,00;
* Caso a pessoa precise de uma vaga na garagem do hotel há um acréscimo diário, sendo R$15,00 de segunda à sexta e R$20,00 nos finais de semana;
* Caso o horário da saída seja após às 16:30h deve ser cobrada uma diária extra;

## Execução

### Dependências necessáris
* Java 8+
* Postgre 9.5+
* Maven

### Passo a passo
Criar o banco de dados 'hotel' no postgre utilizando o código abaixo
```
create database if not exists hotel
```

Acessar o arquivo ***application.properties*** e alterar as os valores das configurações abaixo confome orientação
```
spring.datasource.url=jdbc:postgresql: url de acesso ao bando de dados, ex.: //localhost:5432/hotel
spring.datasource.username= usuário de acesso ao banco de dados, o usuário deve ter permissão para realizar alteraçãos no banco de dados
spring.datasource.password= senha de acesso do usuário
```

Executar o comando abaixo para inicialização da aplicação, ou se preferir, executa-la através do recurso de inicialização da IDE
```
mvn spring-boot:run
```

A aplicação então podera ser acessada através da url `http://localhost:8080`

## Recursos

### Hospede
URL|Método|Descrição
---|---|---
http://localhost:8080/hospede|POST|Cadastra um hóspede no sistema com as informações descritas no corpo da requisição
http://localhost:8080/hospede/{id}|PUT|Atualiza um hóspede ja cadastrado (cujo o id no banco de dados seja igual ao informado na variável {id} na url) no sistema com as informações descritas no corpo da requisição
http://localhost:8080/hospede/{id}|DELETE|Exclui um hóspede (e todas as estadias que ele teve cadastrada no sistema) cujo o id no banco de dados seja igual ao informado na variável {id} na url
http://localhost:8080/hospede|GET|Lista todos os hóspedes cadastrados no sistema
http://localhost:8080/hospede/{id}|GET|Exibe o hóspede cadastrado no sistema cujo o id no banco de dados seja igual ao informado na variável {id} na url
http://localhost:8080/hospede/filtro?nome={nome}&numeroDocumento={numeroDocumento}&numeroTelefone={numeroTelefone}|GET|Lista todos os hóspedes cadastrados no sistema filtrando-os pelo seu nome, número de documento ou número de telefone de acordo com os parâmetros informados na url. Todos os parâmetros são opcionais, mas pelo menos um deles deve ser enviado
http://localhost:8080/hospede/inHotel|GET|Lista todos os hóspedes cadastrados no sistema que estejam em estadia no hotel na data da requisição
http://localhost:8080/hospede/notInHotel|GET|Lista todos os hóspedes cadastrados no sistema que ja tenham incerrado uma estadia no hotel

Exemplo de JSON enviado no corpo da requisição de cadastro/Atualização
```
{
  “nome”: “Fulano da Silva”,
  “numeroDocumento”: “12345678912”,
  “numeroTelefone”: “99252211”
}

```

### Estadia
URL|Método|Descrição
---|---|---
http://localhost:8080/estadia|POST|Cadastra uma estadia no sistema com as informações descritas no corpo da requisição
http://localhost:8080/estadia/{id}|PUT|Atualiza uma estadia ja cadastrada (cujo o id no banco de dados seja igual ao informado na variável {id} na url) no sistema com as informações descritas no corpo da requisição
http://localhost:8080/estadia/{id}|DELETE|Exclui uma estadia cujo o id no banco de dados seja igual ao informado na variável {id} na url
http://localhost:8080/estadia|GET|Lista todas as estadia cadastrados no sistema
http://localhost:8080/estadia/{id}|GET|Exibe a estadia cadastrada no sistema cujo o id no banco de dados seja igual ao informado na variável {id} na url

Exemplo de JSON enviado no corpo da requisição de cadastro/Atualização
```
{
  “hospede”: {...},
  “dataEntrada”: “2018-03-14T08:00:00”,
  “dataSaida”: “2018-03-16T10:17:00”,
  “adicionalVeiculo”: false/true
}

```

