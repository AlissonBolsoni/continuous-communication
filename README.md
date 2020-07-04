# Continuous Communication

O `Continuous Communication` é uma API para gerenciamento de mensagens que 
serão enviadas por diversos canais, como por exemplo `E-MAIL`, `SMS`, `PUSH` e `WHATSAPP`.
O envio é feito através de um servidor de gestão de filas `RABBIT-MQ`, 
que tem um exchange do tipo `TOPPIC` que encaminha as mensagens para cada canal de transmissão.

## Entradas Da API
O `Continuous Communication` disponibiliza algumas entradas para o sistema
>1. Consulta (**GET**) dos discos cadastrados, url de acesso (***/album***)
>2. Consulta (**GET**) de disco por id, url de acesso (***/album/{id}***)
>3. Consulta (**GET**) dos discos por genero, url de acesso (***/album/genre/{genre}***)
>4. Consulta (**GET**) das vendas por id, url de acesso (***/order/{id}***)
>5. Consulta (**GET**) das vendas por intervalo de data, url de acesso (***/order/date***)
>6. Consulta (**POST**) da venda, url de acesso (***/order/register***)

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
Estando na raiz do projeto utilize o comando `docker-compose up --build -d` para executar o **Docker Compose** que contém o banco de dados
e servidor `RABBIT-MQ`.  
Com o banco de dados executando basta executar o projeto.

## Swagger
Para testar a aplicação com o `Swagger` basta estar com o projeto executando e acessar a url `http://localhost:9999/swagger-ui.html`