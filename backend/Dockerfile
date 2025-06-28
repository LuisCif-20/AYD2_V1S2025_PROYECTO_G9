
# imagen base con Java 21
FROM eclipse-temurin:21-jdk-jammy

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/*.jar app.jar

# Expone el puerto en el que corre la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
