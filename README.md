# VollMed - Plataforma de Gerenciamento de Clínica

## Projeto idealizado pelo curso da Alura.

### Descrição
O VollMed é um aplicativo backend desenvolvido para centralizar médicos e facilitar o agendamento de consultas por pacientes. Ele oferece funcionalidades para cadastrar médicos e pacientes, além de permitir o agendamento e cancelamento de consultas. O projeto visa boas práticas de desenvolvimento, escalabilidade e integração com banco de dados relacional, proporcionando uma gestão eficiente e organizada.
### Objetivo da API
A API tem como objetivo fornecer endpoints para realizar operações CRUD sobre médicos, pacientes e consultas. Os dados são organizados de forma a permitir um fácil acesso e modificação, facilitando o gerenciamento das informações da clínica.

### Tecnologias Utilizadas
- **Java**: Linguagem de programação principal para a lógica do sistema.
- **Spring Boot**: Framework utilizado para o desenvolvimento da API RESTful.
- **PostgreSQL**: Banco de dados relacional para armazenamento e consulta de dados.
- **Maven**: Gerenciamento de dependências.
- **Postman**: Ferramenta utilizada para realizar testes na API.

### Funcionalidades Implementadas

#### 1. CRUD de Médicos
- **Cadastro**: Adicionar novos médicos com informações como nome, especialidade, CRM, email, telefone e endereço.
- **Leitura**: Consultar a lista de médicos ou informações específicas de um médico pelo ID.
- **Atualização**: Atualizar os dados de um médico existente.
- **Exclusão**: Remover um médico do sistema.

#### 2. CRUD de Pacientes
- **Cadastro**: Registrar novos pacientes com nome, especialidade, CPF, email, telefone e endereço.
- **Leitura**: Consultar a lista de pacientes ou detalhes de um paciente pelo CPF.
- **Atualização**: Atualizar as informações de um paciente.
- **Exclusão**: Remover um paciente do sistema.

#### 3. CRUD de Consultas
- **Cadastro**: Marcar uma consulta associando um paciente a um médico e definindo a data e hora da consulta.
- **Leitura**: Consultar as consultas agendadas, incluindo dados do médico, paciente e horário.
- **Exclusão**: Cancelar consultas agendadas.

### Funcionalidades em Desenvolvimento
Atualmente, o projeto está finalizando a implementação dos endpoints de **PUT** e **DELETE** para médicos, como parte do CRUD.

### Estrutura do Banco de Dados
- **Médicos**: Contém informações sobre os médicos, incluindo nome, especialidade, CRM, email, telefone e endereço.
- **Pacientes**: Armazena dados sobre os pacientes, como nome, especialidade, CPF, email, telefone e endereço.
- **Consultas**: Registra informações sobre as consultas, incluindo os médicos, pacientes e o horário agendado.

### Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/lrafasouza/vollmed.git

2. Navegue até o diretório do projeto:
   cd vollmed
3. Execute o aplicativo:
   mvn spring-boot:run   
4. LocalHost:
   A API estará disponível em http://localhost:8080.

### Testes (Postman)
- (POST) http://localhost:8080/vollmed/medicos/criar
- (GET) http://localhost:8080/vollmed/medicos/lista
- Em breve...

