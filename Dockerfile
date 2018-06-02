FROM gradle as builder

USER root

WORKDIR /home/gradle/project

COPY . /home/gradle/project/

RUN gradle clean && gradle build


FROM java:openjdk-8-alpine

COPY --from=builder /home/gradle/project//build/libs/phoenix-api-1.0.0.jar app.jar
COPY --from=builder /home/gradle/project//src/main/resources/application.properties  application.properties
EXPOSE 8080
VOLUME ["/home/uploadFiles"]
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar --spring.config.location=/application.properties" ]
