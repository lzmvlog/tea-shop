# 指定基础镜像
FROM openjdk:8
# 复制文件
ADD target/order.jar order.jar
ENTRYPOINT ["java", "-jar", "order.jar"]
# 开启指定的端口
EXPOSE 8091