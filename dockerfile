# Estágio 1: Build (Compilação da Aplicação)

FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo de build (pom.xml) para resolver as dependências
COPY pom.xml .

# Copia o código fonte da aplicação
COPY src ./src

# Executa o build do projeto Spring Boot
# O comando '--no-transfer-progress' é para evitar logs verbosos
# 'clean install' limpa e instala o pacote Maven
RUN mvn clean package -DskipTests

# Estágio de execução - utiliza uma imagem base menor e mais segura
# A imagem 'jammy-jre' é ideal por ter apenas o JRE
FROM eclipse-temurin:21-jre-jammy

# Define o diretório de trabalho para a aplicação em produção
WORKDIR /app

# Copia o arquivo JAR do estágio de build
# O Spring Boot gera um arquivo JAR executável
# Substitua 'seu-projeto' pelo nome do seu artefato JAR
#COPY --from=build /app/target/backend-app-0.0.1-SNAPSHOT.jar app.jar
COPY --from=build /app/target/*.jar app.jar

# Em produção, use variáveis de ambiente do container

# Define a porta que a aplicação Spring Boot usará
EXPOSE 8080

# Comando para rodar a aplicação quando o container iniciar
# Usa 'java -jar' para executar o arquivo JAR
ENTRYPOINT [ "java", "-jar", "app.jar" ]