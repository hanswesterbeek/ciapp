FROM openjdk:11-jre
ENV JAVA_OPTS "-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
ENV APP_HOME /opt/myhome

RUN mkdir -p "${APP_HOME}" && \
  sh -c "touch ${APP_HOME}/app.jar" && apt-get update && apt-get install -y curl

WORKDIR ${APP_HOME}
ENTRYPOINT exec java ${JAVA_OPTS} app.jar
EXPOSE 8080/tcp
HEALTHCHECK --interval=1s --timeout=1s CMD curl --fail http://localhost:8080/actuator/health || exit 1
COPY target/app.jar ${APP_HOME}/app.jar
