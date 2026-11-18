# Senta Aí - Backend

API REST do projeto **Senta Aí**, desenvolvida para a Global Solution 2026.2 (CitizenTech) - FIAP, disciplina de Domain Driven Design - Java.

## Sobre o projeto

Quem espera ônibus não sabe se o próximo vai chegar lotado. O Senta Aí resolve isso com um sensor (Arduino) na porta do ônibus que conta quantas pessoas entram e saem em cada ponto. A API recebe essas leituras, calcula a ocupação em tempo real e o app mostra para quem está no ponto se o ônibus **tem lugar**, está **cheio** ou **lotado**.

## Regras de negócio

- Cada leitura do sensor atualiza a ocupação do ônibus: `ocupação = ocupação + entradas - saídas`
- Não podem sair mais pessoas do que estão a bordo
- A ocupação só muda por leituras do sensor, nunca editando o ônibus direto
- Status da lotação: abaixo de 60% **Tem lugar**, de 60% a 90% **Cheio**, acima de 90% **Lotado**
- Código da linha e placa do ônibus não podem repetir
- Um ponto não pode aparecer duas vezes no trajeto da mesma linha


## Endpoints

Base URL: `https://senta-ai-backend.onrender.com`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | /linhas | Lista as linhas |
| GET | /linhas/{id} | Busca uma linha |
| GET | /linhas/{id}/ocupacao | Lotação de todos os ônibus da linha |
| GET | /linhas/{id}/pontos | Trajeto da linha em ordem |
| POST | /linhas | Cadastra linha |
| PUT | /linhas/{id} | Atualiza linha |
| DELETE | /linhas/{id} | Remove linha |
| GET | /pontos | Lista os pontos |
| GET | /pontos/{id} | Busca um ponto |
| POST | /pontos | Cadastra ponto |
| PUT | /pontos/{id} | Atualiza ponto |
| DELETE | /pontos/{id} | Remove ponto |
| GET | /linha-pontos | Lista os vínculos linha-ponto |
| GET | /linha-pontos/{id} | Busca um vínculo |
| POST | /linha-pontos | Adiciona ponto ao trajeto de uma linha |
| PUT | /linha-pontos/{id} | Atualiza vínculo |
| DELETE | /linha-pontos/{id} | Remove vínculo |
| GET | /onibus | Lista os ônibus |
| GET | /onibus/{id} | Busca um ônibus |
| GET | /onibus/{id}/ocupacao | Mostra se o ônibus tem lugar |
| POST | /onibus | Cadastra ônibus |
| PUT | /onibus/{id} | Atualiza ônibus |
| DELETE | /onibus/{id} | Remove ônibus |
| GET | /leituras | Lista as leituras |
| GET | /leituras/{id} | Busca uma leitura |
| GET | /leituras/onibus/{onibusId} | Leituras de um ônibus |
| POST | /leituras | Sensor envia leitura (entradas/saídas) |
| PUT | /leituras/{id} | Corrige leitura |
| DELETE | /leituras/{id} | Remove leitura |

### Exemplos de JSON

```json
// POST /linhas
{ "codigo": "8000-10", "nome": "Lapa - Paulista" }

// POST /pontos
{ "nome": "Ponto FIAP", "endereco": "Av. Paulista, 1106" }

// POST /onibus
{ "placa": "ABC1D23", "capacidade": 80, "linhaId": 1 }

// POST /leituras
{ "onibusId": 1, "pontoId": 1, "entradas": 12, "saidas": 3 }
```

### Códigos de erro

| Status | Quando acontece |
|---|---|
| 400 | Campos inválidos, JSON mal formatado ou regra de negócio violada |
| 404 | Registro ou endpoint não encontrado |
| 405 | Método HTTP não suportado |
| 409 | Registro duplicado ou em uso por outra tabela |
| 500 | Erro interno |


## Tecnologias

| Ferramenta | Versão |
|---|---|
| Java (JDK) | 21 |
| Spring Boot | 4.1.2 |
| Maven | 3.9 |
| Oracle Database (FIAP) | 19c |
| IntelliJ IDEA | 2026.2 |
| Postman | testes dos endpoints |
| Render | deploy (Docker) |

## Como executar localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/SentaAI/senta-ai-backend.git
   ```
2. Abra a pasta no IntelliJ e espere o Maven baixar as dependências
3. Em **Run > Edit Configurations > Environment variables**, configure:
DB_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL;DB_USER=seu_rm;DB_PASSWORD=sua_senha

4. Rode a classe `SentaaiApplication`. As tabelas são criadas automaticamente no Oracle
5. Teste em `http://localhost:8080/linhas`

## Deploy

A API está publicada no Render: https://senta-ai-backend.onrender.com

O plano gratuito desliga o servidor depois de 15 minutos sem uso, então a primeira requisição pode demorar cerca de 1 minuto.

## Integrantes

| Nome | RM |
|---|---|
| Levi de Jesus | RM563279 |
| Ulisses Abreu | RM562230 |
| Luigi Borghi | RM563096 |
| Arthur Berlofa Bosi | RM564438 |

