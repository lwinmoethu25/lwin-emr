FROM gradle:7.5.1-jdk11-alpine AS build
COPY --chown=gradle:gradle . /app/src
WORKDIR /app/src
RUN gradle build --no-daemon 

FROM openjdk:11-jre-slim
COPY --from=build /app/src/build/libs/emr-0.0.1-SNAPSHOT.jar emr.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/emr.jar"]
