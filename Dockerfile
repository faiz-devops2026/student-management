FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY target/*.jar app.jar

# optional but safe
RUN mkdir -p /app/uploads/students

EXPOSE 8089
ENTRYPOINT ["java","-jar","app.jar"]
