#GENERAR LA IMAGEN
# docker build -t app-authors .
#listo para subir docker hub
# docker build -t daveq93/app-authors .
#docker login
  #usuario y contraseña
#docker push daveq93/app-authors

FROM eclipse-temurin:17.0.11_9-jre-jammy

RUN mkdir /app
WORKDIR /app

COPY build/quarkus-app/app ./app
COPY build/quarkus-app/lib/ ./lib
COPY build/quarkus-app/quarkus/ ./quarkus
COPY build/quarkus-app/quarkus-run.jar app.jar


CMD ["java","-jar","app.jar"]