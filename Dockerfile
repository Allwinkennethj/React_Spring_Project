FROM openjdk:17
EXPOSE 8080
ADD target/backend-0.0.1-SNAPSHOT.jar devops.jar
ENTRYPOINT ["java","-jar","/devops.jar"]
