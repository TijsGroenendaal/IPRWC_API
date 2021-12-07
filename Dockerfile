FROM openjdk:16-slim
COPY target/*.jar iprwc-api.jar
VOLUME /data
ENTRYPOINT ["java","-jar","/iprwc-api.jar"]
