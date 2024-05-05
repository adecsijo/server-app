FROM alpine:latest

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'
ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV DBCONNECT="jdbc:mariadb://login_db:3306/shopping_list"

RUN apk add --no-cache openjdk21-jre && mkdir -p /opt/thesis

COPY target/server-shopping-list-1.0.0-SNAPSHOT-runner.jar /opt/thesis

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/opt/thesis/server-shopping-list-1.0.0-SNAPSHOT-runner.jar" ]