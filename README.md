# ERP Distribuido

ERP Distribuído com 3 módulos:

### Random Shop
Gera dados randômicos de compras por cliente.

### Shop Storage
Armazena dados das compras randômicas e fornece API´s para relatórios
vendas totais por clientes e quantidade de vendas por dia.

### Buyer Bot
Serviço agendado que periodicamente requisita compras randômicas
de Random Shop e envia para Shop Storage.


## Instalação

Após baixar o código fonte da aplicação, executar o comando abaixo
para realizar o build da aplicação e gerar imagens docker com o Maven
e executar os testes unitários:

No diretório raiz da aplicação:
```js
./mvnw spring-boot:build-image
```

## Execução

Após compilar o código com sucesso, pode-se executar a aplicação utilizando os comando abaixo:
 - Iniciar os microserviços com docker compose:
```js
docker-compose up
```

## Relatórios
Pode-se verificar a execução da aplicação e armazenagem dos dados no serviço Shop Storage
a partir dos endpoints abaixo:
- Lista contendo a lista de clientes com a soma total do VALOR gasto pelo cliente:
```js
http://localhost:8070/api/v1/shop-store/reports/clientes
```
- Quantidade total de itens vendidos em cada data: 
```js
http://localhost:8070/api/v1/shop-store/reports/vendas
```