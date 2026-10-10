# CRUD EM JAVA BÁSICO

## Descrição do projeto

- Este é um projeto de CRUD (Create, Read, Update, Delete) Utilizando java básico.
- Nele o usuario poderá fazer seu cadastro, o programa vai efetuará a validação
dos dados, para realizar com exito o cadastro. 
- Os dados serão mantidos em um banco de dados MYSQL, o programa vai salvar os dados lá e consultar quando usuario quiser fazer login, ou atualizar seus dados.
- O programa salva, consulta, atualiza os dados do usuario e excluir os dados conforme a necessidade do usuario.

## Tecnologias
- Java 17
- MySQL
- Mockito 
- Junit 5

- Projeto criado com finalidade estudantil, para aprendizado e prática de programação em JAVA.

## Instrução para rodar o projeto

### Pré-requisitos

- JAVA JDK 17 ou superior
- MYSQL 8.4.0
- IntelliJ IDEA ou VS Code (opcional)
- 
## Como rodar

1. Clone o repositório

   ```bash
   git clone https://github.com/seu-usuario/projectCrud.git
   ```
   
2. Crie um banco de dados no MySQL, quando o programa for executado ele vai criar a tabela automaticamente.
3. Copie os dados de `db.properties.example` e cole no arquivo `db.properties` na pasta `src/main/resources/`.
4. Altere as configurações do banco de dados no arquivo `db.properties` com seu usúario, senha e nome do banco de dados criado.

   ```properties
   db.url=jdbc:mysql://localhost:3306/seu_banco_de_dados
   db.user=seu_usuario
   db.password=sua_senha
   ```
5. Compile e execute o projeto usando sua IDE ou via terminal da IDE usando esse comando:

   ```bash
   mvn compile exec:java
   ```
   
## Testes
- Para rodar os testes, utilize o seguinte comando:

   ```bash
   mvn test
   ```

## O que aprendi com esse projeto
1. Desenvolvendo o próprio Dev:
- Aprendi a desenvolver com mais facilidade/mais solto.
- Aprendi a ter mais responsabilidade com o projeto.
- Ser mais paciente com os erros e problemas que surgem durante o desenvolvimento.
- Ter mais persistencia até conseguir resolver o problema ou encontrar o caminho correto para resolver o problema.

2. De forma Prática:
- Básico de Java, como manipular dados, criar classes, métodos e objetos.
- Encapsulamento, herança e polimorfismo. (Mesmo não aplicado neste projeto, foi estudado)
- Nomear de forma mais clara e objetiva as variáveis, métodos e classes.
- Tratamento de exceções com `try/catch` usando `throw`ou sem.
- Validação de dados, como validar email, senha, cpf e outros.
- Como conectar e manipular dados em um banco de dados MySQL usando JDBC.
- Como criar testes unitários com JUnit 5 e Mockito para testar métodos e classes de forma isolada.
- Como criar um projeto Maven e organizar as dependências no arquivo `pom.xml`.
- Como criar um arquivo de configuração `db.properties/.env` para armazenar as informações do banco de dados.
- Como criar um arquivo `README.md` para documentar o projeto e fornecer instruções de uso.
- Como usar git para versionar o código com commits, push e pull. E navegar entre branches, dar rollback em um commit comitado errado.
- Criar tags `fix` e `feature` para distinguir o que está sendo feito naquela branch ou o que foi feito no commit.
