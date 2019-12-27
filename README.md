# ColetaAPI
Esta API têm por objetivo coletar e armazenar dados de evento, cada 
evento corresponde as informações de navegação dos usuários em um site.

Este projeto foi gerado pelo [Spring Initializr](https://start.spring.io/) com as bibliotecas `spring-boot-starter-webflux`, `h2`, `lombok` e `spring-boot-starter-data-jpa`.

A biblioteca `spring-boot-starter-webflux` é um módulo do Spring Framework 5 que provê recursos para trabalhar com aplicações web reativas no lado do back-end.
A biblioteca `h2` é um módulo para trabalhar com banco de dados embarcado relacional escrito em java. 
E o `lombok` é uma API para simplificar o uso de métodos gets e sets com o código mais limpo, esta biblioteca foi usada no pacote model

## EndPoints

* `event`: POST - Inseri um evento na base de dados.  <br />
Exemplo: 
````
{
  "event": "COMPRAR",
  "timestamp": "2019-12-26T17:13:44.560-02:00"
}
````


* `event/:name`: GET - Busca os eventos por nome. <br />
Exemplo: 
````
localhost:8080/event/co
````
* `timeline/purchases`: GET -  Retorna o autocomplete, 
esta requisição agrupa a lista de compras pelos eventos disponibilizado no endpoint https://storage.googleapis.com/dito-questions/events.json <br />
Exemplo: `localhost:8080/timeline/purchases` 


## Execução
#### Executar a aplicação na máquina física:
Abra o prompt de comando ou shell na raiz do projeto e faça a seguinte intrução:
`mvnw clean package && java -jar target/coletaAPI-0.0.1-SNAPSHOT.jar`

#### Executar a aplicação em container docker:
Faça conforme o exemplo acima:
`mvnw clean package && java -jar target/coletaAPI-0.0.1-SNAPSHOT.jar`, 
em seguida crie a imagem docker com a seguinte instrução:
`docker build -t dito/coletorAPI .`
E por fim faça o seguinte comando para rodar a aplicação em um container docker:
`docker run -p 8080:8080 -t dito/coletorAPI`
