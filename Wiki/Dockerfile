FROM maven:3.6-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/Wiki-0.0.1-SNAPSHOT.jar /usr/local/lib/wiki.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/usr/local/lib/wiki.jar"]