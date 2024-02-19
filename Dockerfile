FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY src /app/src
COPY pom.xml /app/pom.xml
RUN mvn -f pom.xml clean package -Dmaven.test.skip=true

FROM tomcat:9.0.54-jdk17-openjdk-slim
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
CMD catalina.sh run