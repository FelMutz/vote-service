# vote-service
Sistema de votações simples usando Java, Spring Boot e PostgreSQL.

## Regras do Projeto
- Associados podem ser cadastrados com nome e documento (CPF) válido.
- Pautas podem ser criadas com título e descrição.
- Sessões de votação podem ser abertas para uma pauta, com duração configurável (padrão de 1 minuto).
- Associados podem votar “SIM” ou “NÃO” em uma pauta durante a sessão de votação.
- Um associado só pode votar uma vez por pauta.
- Um job agendado verifica sessões encerradas, contabiliza votos e atualiza o status da pauta (aprovada ou não aprovada).
- Pautas sem votos ou com votos empatados são consideradas não aprovadas.
- Sessões não podem ser abertas para pautas que já possuam uma sessão ativa.
- Sessões só podem ser abertas para pautas que já foram auditadas.
- Votos são validados considerando todas as sessões já criadas para a pauta.
- Pautas e sessões não podem ser alteradas ou excluídas após a criação, preservando o histórico.
- A validação de CPF é feita através da API externa: https://cpf-validator-service.onrender.com/{cpf}.
- Foi configurado o circuito breaker para a chamada da API externa usando Resilience4j.
- Se o circuito estiver aberto, o cpf será considerado inválido.

## Construir e executar

### Construir artefato
Executar o comando abaixo para executar testes e gerar o jar executável do Spring boot:
```
 ./gradlew clean build
```

### Executar Docker Compose
Rodar o docker-compose para subir toda a infra e o serviço:
```
docker-compose up --build -d
```

### Mostrar logs do serviço
Rodar o comando abaixo para visualizar os logs do serviço:
```
docker logs -f voteservice
```

### Swagger
Abrir a seguinte URL no browser para visualizar o Swagger UI:
```
http://localhost:8080/swagger-ui/index.html#/
```

### Destruir o serviço
```
docker-compose down
```

### Acessar o health da aplicação
```
http://localhost:8080/actuator/health
``` 

### Acessar o health do banco de dados
```
http://localhost:8080/actuator/health/db
```

### Acessar o status do circuito breaker
```
http://localhost:8080/actuator/circuitbreakers
``` 

## 📌 Entidades do Projeto

Este projeto contém as seguintes entidades principais:

---

### AssociateEntity
Representa um associado.

- `id`: identificador único
- `name`: nome do associado
- `document`: documento de identificação (ex: CPF)
- `createDate`: data de criação
- `deleteDate`: data de exclusão lógica (soft delete)

---

### AgendaEntity
Representa uma pauta a ser votada.

- `id`: identificador único da pauta
- `title`: título da pauta
- `describe`: descrição da pauta
- `createDate`: data de criação
- `aproved`: status de aprovação (`true` se aprovada, `false` caso contrário)

---

### SessionAgendaEntity
Representa uma sessão de votação vinculada a uma pauta.

- `id`: identificador único da sessão
- `agendaId`: identificador da pauta associada
- `startDate`: data/hora de início
- `finishDate`: data/hora de término
- `open`: indica se a sessão está aberta para votação

---

### VoteAgendaEntity
Representa um voto registrado em uma pauta.

- `id`: identificador único do voto
- `agendaId`: identificador da pauta
- `associateId`: identificador do associado que votou
- `vote`: valor do voto (`true` para SIM, `false` para NÃO)
- `createDate`: data em que o voto foi registrado

---

## Job Agendado
O projeto inclui um job agendado que executa a cada 1 minuto para verificar sessões de votação que foram encerradas. Quando uma sessão é encerrada, o job contabiliza os votos e atualiza o status da pauta associada (aprovada ou não aprovada).
O job utiliza o scheduleLock para evitar execuções concorrentes e permitir que a aplicação seja escalada horizontalmente.

## Flyway
O projeto utiliza Flyway para gerenciar migrações de banco de dados. As migrações são armazenadas na pasta `src/main/resources/db/migration` e seguem a convenção de nomenclatura `V{version}__{description}.sql`.
