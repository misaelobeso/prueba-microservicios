FROM openjdk:8-jre-alpine
COPY build/libs/test-0.0.1-SNAPSHOT.jar test-0.0.1-SNAPSHOT.jar
COPY build/libs/test-0.0.1-SNAPSHOT-plain.jar test-0.0.1-SNAPSHOT-plain.jar
CMD ["java","-jar","test-0.0.1-SNAPSHOT.jar"]
