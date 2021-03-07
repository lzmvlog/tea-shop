# 指定基础镜像
FROM openjdk:8
# 复制文件
ADD target/auth.jar auth.jar
ENTRYPOINT ["java", "-jar", "auth.jar"]
# 开启指定的端口
EXPOSE 8091