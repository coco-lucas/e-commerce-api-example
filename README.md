# e-commerce-api-example
Uma API básica de e-commerce desenvolvida como referência para projetos futuros.

## Objetivo
Este projeto tem como finalidade servir como base para integrações futuras em sistemas de e-commerce. Foram aplicadas tecnologias com foco em:
* Mensageria e logging: para rastreabilidade e controle das requisições;
* Containerização: para facilitar o deploy e melhorar a familiaridade com ambientes conteinerizados;
* Banco de dados NoSQL: pela flexibilidade no armazenamento e consulta de dados em cenários com estrutura dinâmica, comum em sistemas de e-commerce.

## Stack utilizada
* Java 17
* Spring Boot
* Docker
* RabbitMQ
* MongoDB

## Possíveis melhorias futuras
* Evoluir este projeto para uma solução mais completa e escalável de e-commerce;
* Refatorar a estrutura aplicando Clean Architecture para melhorar a organização, a testabilidade e a manutenção do código;
* Reestruturar os pacotes e módulos visando uma comunicação mais coesa e moderna entre as camadas.

### Como executar
Pré-requisitos: Docker, Docker Compose e MongoDBCompass instalados.

~~~ bash
docker-compose up # para crair os containers

# e depois, executar na sua IDE.
~~~
