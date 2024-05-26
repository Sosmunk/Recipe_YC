FROM openjdk:17-alpine
WORKDIR /app
COPY target/recipe_yc-*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080
