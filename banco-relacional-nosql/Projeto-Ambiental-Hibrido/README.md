# 🌱 Plataforma de Conhecimento Ambiental dos Rios e Biomas Brasileiros

Projeto desenvolvido na disciplina **Banco de Dados Relacional e NoSQL**, da Pós-Graduação em Desenvolvimento de Sistemas WEB e Aplicativos Móveis — **IFSP**.

## 📋 Sobre o projeto

A plataforma tem como objetivo centralizar informações sobre **rios brasileiros e seus biomas**, permitindo o armazenamento e consulta de dados relacionados a:

* 🌊 Rios e seus trechos
* 🌳 Biomas
* 🐾 Espécies de fauna e flora
* 💧 Qualidade da água
* 🔬 Monitoramento ambiental
* 🌱 Projetos ambientais

A solução pode ser utilizada por **pesquisadores, estudantes, órgãos ambientais e pela população**, facilitando o acesso e a análise de informações ambientais.

## 🗄️ Modelos de Banco de Dados

O projeto utiliza duas abordagens para comparação:

### SQL — Banco Relacional

O modelo relacional organiza os dados em diferentes tabelas, utilizando relacionamentos para garantir maior estrutura e consistência.

**Características:**

* Estrutura organizada
* Relacionamentos entre entidades
* Integridade dos dados
* Redução de duplicidade
* Consultas utilizando `JOIN`

### 🍃 MongoDB — NoSQL

No MongoDB, as informações podem ser armazenadas em documentos, permitindo uma estrutura mais flexível.

Um documento de um rio pode conter, por exemplo:

* Informações do rio
* Trechos
* Espécies
* Monitoramentos
* Registros de qualidade da água

Essa abordagem permite alterações na estrutura dos documentos com maior facilidade.

## ⚖️ Comparação

| Característica       | SQL                 | MongoDB                            |
| -------------------- | ------------------- | ---------------------------------- |
| Estrutura            | Tabelas             | Documentos                         |
| Relacionamentos      | Forte               | Mais flexível                      |
| Integridade          | Alta                | Mais responsabilidade da aplicação |
| Flexibilidade        | Menor               | Maior                              |
| Consultas            | Pode exigir `JOINs` | Pode utilizar documentos agrupados |
| Alterações no modelo | Mais rígidas        | Mais simples                       |

## ✅ Vantagens e desvantagens

### SQL

**Vantagens:**

* Maior organização dos dados
* Integridade referencial
* Consistência
* Relacionamentos bem definidos

**Desvantagens:**

* Estrutura mais rígida
* Alterações no modelo podem exigir mudanças no banco
* Consultas podem envolver vários `JOINs`

### MongoDB

**Vantagens:**

* Maior flexibilidade
* Estrutura de documentos
* Facilidade para adicionar novos campos
* Dados relacionados podem ficar agrupados

**Desvantagens:**

* Possibilidade de duplicação de dados
* Menor controle estrutural pelo banco
* Integridade pode depender mais da aplicação

## 🏗️ Modelo escolhido para produção

Para uma aplicação real, a proposta seria utilizar uma **arquitetura híbrida**, aproveitando as características de cada tecnologia.

### 🗃️ SQL

Responsável pelos dados principais e estruturados:

* Rios
* Biomas
* Espécies
* Informações cadastrais

### 🍃 MongoDB

Responsável por dados de monitoramento e históricos:

* Medições da qualidade da água
* Histórico de monitoramento
* Registros ambientais
* Informações com estrutura variável

Essa combinação permite utilizar a **consistência do SQL** nos dados principais e a **flexibilidade do MongoDB** para informações de monitoramento que podem crescer e variar ao longo do tempo.

## 🔎 Consultas SQL

O projeto contempla consultas para responder perguntas como:

1. Quais rios pertencem ao **bioma Cerrado**?
2. Quais rios possuem **qualidade da água Boa**?
3. Quais rios possuem determinada espécie, como a **Espécie 1**?

## 🛠️ Tecnologias

* SQL
* Banco de Dados Relacional
* NoSQL
* MongoDB

## 🎓 Disciplina

**Banco de Dados Relacional e NoSQL**

**Pós-Graduação em Desenvolvimento de Sistemas WEB e Aplicativos Móveis**

**Instituto Federal de São Paulo — IFSP**

## 👨‍💻 Autor

**Carlos Oliveira**
