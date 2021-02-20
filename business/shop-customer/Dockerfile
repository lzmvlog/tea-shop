# 指定基础镜像
FROM openjdk:8
# 复制文件
ADD target/customer.jar customer.jar
ENTRYPOINT ["java", "-jar", "customer.jar"]
# 开启指定的端口
EXPOSE 8091