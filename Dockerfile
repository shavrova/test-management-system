FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD target/test-management-system-0.0.1-SNAPSHOT.jar test-management-system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","test-management-system-0.0.1-SNAPSHOT.jar"]