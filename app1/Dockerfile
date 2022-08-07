FROM adoptopenjdk/openjdk15:x86_64-ubuntu-jre-15.0.2_7
ARG JAR=build/libs/app1.jar
RUN mkdir /opt/app
COPY ${JAR} /opt/app
CMD ["java", "-jar", "/opt/app/app1.jar"]