FROM gradle as builder

WORKDIR /app

COPY . /app/

RUN gradle clean && gradle build


FROM java:openjdk-8-alpine

COPY --from=builder /app/build/libs/phoenix-api-1.0.0.jar app.jar
COPY --from=builder /app/src/main/resources/application.properties  application.properties
EXPOSE 8080
VOLUME ["/home/uploadFiles"]
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar --spring.config.location=/application.properties" ]
