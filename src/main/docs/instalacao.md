# Como rodar a aplicação.

### O que vamos precisar?
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Java 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- A versão do JDK é a 17

### Como rodar? (pelo terminal)
- Clone o repositório
- Abra o terminal na pasta raiz do projeto
- Execute o comando `docker-compose up -d`
- Execute o comando `./mvnw clean install`
- Execute o comando `./mvnw spring-boot:run`

### Como rodar?(Pelo intelij Ou outra IDE)
- Clone o repositório
- Abra o projeto na sua IDE
- Abra o terminal na pasta raiz do projeto
- Execute o comando `docker-compose up -d`
- Configure o que precisa configurar de estrutura do projeto (jdk e afins)
- Inicie o projeto
