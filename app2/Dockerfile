FROM adoptopenjdk/openjdk15:aarch64-ubuntu-jre-15.0.2_7
ARG JAR=build/libs/app2.jar
RUN mkdir /opt/app
COPY ${JAR} /opt/app
CMD ["java", "-jar", "/opt/app/app2.jar"]