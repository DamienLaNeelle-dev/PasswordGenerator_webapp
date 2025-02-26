# Étape 1 : Utiliser une image Java 21 pour Spring Boot
FROM openjdk:21-jdk-slim

# Installer Maven (nécessaire pour compiler ton projet)
RUN apt-get update && apt-get install -y maven && apt-get clean

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances Maven
COPY PasswordGenerator_webapp/pom.xml ./
RUN mvn dependency:go-offline

# Copier le code source du backend (dossier Spring Boot)
COPY PasswordGenerator_webapp/ ./

# Compiler l'application Spring Boot et créer le fichier JAR
RUN mvn clean package -DskipTests

# Copier le dossier de build React dans le dossier static de Spring Boot
COPY password-generator-frontend/build/ /app/PasswordGenerator_webapp/src/main/resources/static/

# Exposer le port 8080
EXPOSE 8080

# Exécuter l'application Spring Boot avec la commande `java -jar`
CMD ["java", "-jar", "/app/target/PasswordGenerator_webapp-0.0.1-SNAPSHOT.jar"]
