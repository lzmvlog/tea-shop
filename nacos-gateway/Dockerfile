# 指定基础镜像
FROM openjdk:8
# 复制文件
ADD target/gateway.jar gateway.jar
ENTRYPOINT ["java", "-jar", "agateway.jar"]
# 开启指定的端口
EXPOSE 8090