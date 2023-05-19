FROM openjdk:8-jdk-alpine
ADD ./target/service-consumer-1.0.0.jar /app.jar
ENTRYPOINT ["java","-jar","app.jar", \
"--server.port:20102", \
"--spring.cloud.nacos.discovery.server-addr=gzpi-nacos:8848", \
"--spring.cloud.nacos.config.server-addr=gzpi-nacos:8848"]
