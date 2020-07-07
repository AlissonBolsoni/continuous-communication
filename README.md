# Continuous Communication

O `Continuous Communication` é uma API para gerenciamento de mensagens que 
serão enviadas por diversos canais, como por exemplo `E-MAIL`, `SMS`, `PUSH` e `WHATSAPP`.
O envio das mensagens salvas será através de um serviço paralelo de mensageria.

## Entradas Da API
O `Continuous Communication` pussui um grupo de entradas, que é o controlador de mensagens.  
Esse controlador possui três entradas, que estão listadas abaixo.
>1. (**GET**) Consulta através do Id da mensagem que salva no banco de dados (***/message/{id}***)
>2. (**DELETE**) Remove uma mensagem através do Id (***/message/{id}***)
>3. (**POST**) Salva uma nova mensagem no banco de dados (***/message/register***)

### Comunicação com a API
>1. ***Buscar Mensagem Pelo Id***
#### Envio
```curl
curl -X GET \
  http://localhost:9999/message/2ce28328-458f-439d-aa3b-5f3848f3ac87 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: c2b2a103-1a76-bc53-48d7-a5f556380e26'
```
#### Retorno
```json
{
    "uuid": "2ce28328-458f-439d-aa3b-5f3848f3ac87",
    "message": "Mensagem que irá no email",
    "destiny": [
        "email1@teste.com.br",
        "email2@teste.com.br"
    ],
    "messageType": "email",
    "sendTime": "2020-07-08T02:16:45.000+00:00",
    "messageStatus": "SENT"
}
```
>2. ***Remover Mensagem Pelo Id***
#### Envio
```curl
curl -X DELETE \
  http://localhost:9999/message/2ce28328-458f-439d-aa3b-5f3848f3ac87 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 9f84a105-3f2f-9906-55af-13063628c032'
```
#### Retorno
```json
{
    "uuid": "2ce28328-458f-439d-aa3b-5f3848f3ac87",
    "message": "Mensagem que irá no email",
    "destiny": [
        "email1@teste.com.br",
        "email2@teste.com.br"
    ],
    "messageType": "email",
    "sendTime": "2020-07-08T02:16:45.000+00:00",
    "messageStatus": "SENT"
}
```
>3. ***Registrar Mensagem***
#### Envio
```curl
curl -X POST \
  http://localhost:9999//message/register \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ac0f8976-b4be-4293-49fe-5bc1de0a4023' \
  -d '{
  "destiny": [
    "email3@teste.com.br",
    "email4@teste.com.br",
    "email5@teste.com.br"
  ],
  "message": "Mensagem para os emails 3, 4, 5",
  "messageStatus": "",
  "messageType": "email",
  "sendTime": "2020-07-08T03:20:39.104Z",
  "uuid": ""
}'
```
#### Retorno
```json
{
    "uuid": "732eaefb-d23d-4e3e-8c2e-aad4a3053a1e",
    "message": "Mensagem para os emails 3, 4, 5",
    "destiny": [
        "email3@teste.com.br",
        "email4@teste.com.br",
        "email5@teste.com.br"
    ],
    "messageType": "email",
    "sendTime": "2020-07-08T03:20:39.104+00:00",
    "messageStatus": "WAITING"
}
```

## Tecnologias Utilizadas
- Spring Boot
- MySQL
- Swagger
- JPA
- Hibernate
- Docker
- JUnit5
- Mockito
- RabbitMQ

## Executar a aplicação
Estando na raiz do projeto utilize os comandos abaixo `docker-compose up --build -d` para executar o **Docker Compose** que contém o banco de dados
e servidor `RABBIT-MQ`.  
Com o banco de dados executando basta executar o projeto.

>1. **docker-compose up --build -d**  
    Para iniciar o `Docker` com o banco de dados `MySQL` e o sevidor `RabbitMQ`.
>2. **mvn clean install**  
    Para fazer o build da aplicação e execução de testes unitários.
>3. **mvn spring-boot:run**  
    Comando para iniciar a aplicação.


## Swagger
Para testar a aplicação com o `Swagger` basta estar com o projeto executando e acessar a url `http://localhost:9999/swagger-ui.html`