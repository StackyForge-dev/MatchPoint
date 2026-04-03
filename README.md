
# ⚽ MatchPoint API

> "O jogo não para. A gente também não."

![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-warning)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)

## 📖 Sobre o Projeto
**MatchPoint** é uma API RESTful desenvolvida para o gerenciamento completo de campeonatos esportivos e partidas. O sistema permite a administração de usuários, o cadastro de jogadores e times, e o registro em tempo real das métricas das partidas (como saldo de gols e cartões), automatizando a geração de rankings dinâmicos de torneios.

A arquitetura foi pensada para ser escalável e robusta, isolando a camada de dados através do padrão DTO com MapStruct.

## 🚀 Tecnologias Utilizadas
* **Backend:** Java 21, Spring Boot (Web, Data JPA, Validation)
* **Banco de Dados:** PostgreSQL (Produção/Dev) e H2 (Testes em memória)
* **Mapeamento de Objetos:** MapStruct
* **Segurança:** Spring Security Crypto (BCrypt)
* **Infraestrutura:** Docker e Docker Compose
* **Testes:** JUnit 5, AssertJ

## ⚙️ Funcionalidades Principais
- [x] **Gestão de Usuários:** CRUD completo com senhas criptografadas.
- [x] **Gerenciamento de Campeonatos:** Criação de torneios e listagem de rankings gerais e específicos.
- [x] **Controle de Jogadores:** Cadastro e cálculo automático do saldo de gols.
- [x] **Registro de Partidas:** Sistema de anotação de gols e cartões amarelos vinculados aos jogadores.
- [ ] *Em breve: Tratamento global de exceções (@ControllerAdvice).*
- [ ] *Em breve: Autenticação baseada em JWT.*

## 🐳 Como Executar o Projeto

Graças ao Docker Compose, levantar o ambiente completo (Aplicação + Banco de Dados) é extremamente simples.

### Pré-requisitos
* Docker e Docker Compose instalados na máquina.

### Passos para rodar:
1. Clone este repositório:
   ```bash
   git clone [https://github.com/stackyforge-dev/matchpoint.git](https://github.com/stackyforge-dev/matchpoint.git)
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd matchpoint
   ```
3. Suba os containers usando o Docker Compose:
   ```bash
   docker-compose up -d --build
   ```
4. A API estará rodando e acessível na porta `8080`.
    * **URL Base:** `http://localhost:8080/api/v1`

### Acesso ao Banco de Dados Externo
O banco PostgreSQL também fica exposto para gerenciamento na porta `6789` da sua máquina host com as seguintes credenciais padronizadas:
* **Usuário:** postgres
* **Senha:** manager
* **Database:** db_matchpoint

## 🧪 Rodando os Testes
A aplicação possui uma suíte de testes de integração com o banco em memória H2. Para executar:
```bash
mvn clean test
```

## 🤝 Colaborador(es)
<table>
  <tr>
    <td align="center">
      <a href="https://linkedin.com/in/igorryan" title="link do linkedin de Igor Alves">
        <img src="https://media.licdn.com/dms/image/v2/D4D03AQGopZX3ceB7cg/profile-displayphoto-scale_200_200/B4DZkU851wHYAY-/0/1756993135190?e=1776297600&v=beta&t=2s_GT7DzO8M0cl8OGX_IVVrxP97A-COkiL1ecd8eiL0" alt="Foto do Igor Alves"/><br>
        <sub>
          <b>Igor Alves</b>
        </sub>
      </a>
    </td>
  </tr>
</table>  