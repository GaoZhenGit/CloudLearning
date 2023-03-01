FROM openjdk:8-jdk-alpine
ADD ./target/service-database-1.0.0.jar /app.jar
EXPOSE 20201
ENTRYPOINT ["java","-jar","app.jar", \
"--spring.profiles.active=docker", \
"--spring.cloud.nacos.discovery.group=TEST_GROUP", \
"--spring.cloud.nacos.discovery.server-addr=nacos:8848", \
"--spring.cloud.nacos.config.group=TEST_GROUP", \
"--spring.cloud.nacos.config.server-addr=nacos:8848"]
