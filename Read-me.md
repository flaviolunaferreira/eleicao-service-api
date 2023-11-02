Eleger
Build & Deploy
Este Projeto Requerr JDK 11 para build e executar à aplicação. Ele também usa o Heroku para executar na Nuvem. Você pode usar a vontade e fazer auterações que achar necessária para fins de estudo sem precisar de atorização prévia em https://github.com/flaviolunaferreira/eleicao-service-api

Se a sua estação de trabalho não tem o Java11 instalada vc pode fazer a migrração do projeto para a versão 1.8.

Run & Test: Para rodar localmente você pode usar o sua IDE de preferencia ou linha de comando para o builder Mavem primeiro
para compilar o projeto e depois executar


Para Compilar (e executar incluindo os testes de unidade) rode a linha de comando
./gradlew build
Para rodar o projeto (e iniciar o projeto spring com o tom cat embarcado) execute a linha de comando:
./gradlew bootRun
Nota: Windows nao usa ./ no inicio dos comandos Mavem.

Algumas notas importantes sobre a execuçao local.

Sua aplicaçao vai rodar por padrao na porta 8080, para executar os teste locais use : http://localhost:8080/actuator/health


Para finalizar a execuçao de sua aplicaçao , use ^C (control-C). para terminar o processo.

IDE
Sua Versao de IDE devem suportar o Java 11 — i.e. Eclipse (>= 2018-09), IntelliJ (>= 2018.2).

Voce pricesa instalar o Lombok na sua IDE.

Swagger-UI voce pode fazer testes na URL http://localhost:8080/swagger-ui.html
Nota: Algumas rotas precisam de autorizaçao

API docs podem ser acessadas em URL http://localhost:8080/v2/api-docs
Isso pra precisar de suas credenciais


Bibliotecas usadas — Spring Boot 2 , Gradle 6 , Java 11 , Gradle Boost Plugin , Lombok , Firewall Friendly

Recomendados Web — Swagger , Actuator , Sleuth , X-Application-Info , X-Request-Info , Common Error Handling


