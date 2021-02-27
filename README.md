# tea-shop

#### 介绍

茶叶商城 采用微服务架构设计实现商城系统 提升用户精致化消费体验

#### 软件架构

1. `nacos-getway`网关
2. `common`相关共同依赖
3. `business`业务实现 - 后续服务创建在当前文件下  
   `goods`服务为商品服务   
   `customer`服务为用户服务    
   `order`服务为订单服务

#### 待实现

1. 商品服务
2. 订单服务
3. 文件服务
4. 用户服务

#### 使用说明

依赖版本

```
SpringBoot 2.1.9.RELEASE
SpringCloud Greenwich.SR3
mybatis-plus 3.3.2
```

#### 安装教程

1. 安装`nacos`注册中心 用于服务配置 例如：

```yaml
# 这里的应用名对应 Nacos Config 中的 Data ID，实际应用名称以配置中心的配置为准
spring.application.name=nacos-gateway
  # 指定查找名为 nacos-gateway.yaml 的配置文件
spring.cloud.nacos.config.file-extension=yaml
  # Nacos Server 的地址
spring.cloud.nacos.config.server-addr=ip:port 
```

2. 启动`nacos-getway`用与注册服务，通过网关请求具体业务系统

#### 部署说明

##### docker 部署

##### docker-compose 部署
安装docker-compose
```shell
wget https://github.com/docker/compose/releases/download/1.25.0/docker-compose-$(uname -s)-$(uname -m) -O /usr/local/bin/docker-compose

#读写权限
sudo chmod +x /usr/local/bin/docker-compose

#查看 docker compose 版本
docker-compose version
```
配置网络
```
创建网络
docker network create <Network>

查看已存在的网络
docker network tea-shop
```
```yaml
network:
   default:
      external:
         name: mysql
```
```yaml
version: '3.1'
services:
  mysql:
    restart: always
    image: mysql
    environment:
      TZ: Asia/Shanghai
      MYSQL_ROOT_PASSWORD: root
    command:
      --character-set-server=utf8mb4
      --collation-server=utf8mb4_general_ci
      --explicit_defaults_for_timestamp=true
    ports:
      - 3306:3306
    volumes:
      - ./data:/var/lib/mysql
      - ./config:/etc/mysql/conf.d
      - ./log:/var/log/mysql

  nacos-gateway:
    restart: always
    image: tea-shop/nacos-gateway:latest
    ports:
      - 8090:8090
    volumes:
      - ./data/log/nacos-gateway:/var/log/nacos-gateway

  shop-goods:
    restart: always
    image: tea-shop/shop-goods:latest
    ports:
      - 9091:9091
    volumes:
      - ./var/log/shop-goods:/var/log/goods

  shop-customer:
    restart: always
    image: tea-shop/shop-customer:latest
    ports:
      - 9092:9092
    volumes:
      - ./var/log/shop-customer:/var/log/customer

  shop-order:
    restart: always
    image: tea-shop/shop-order:latest
    ports:
      - 9093:9093
    volumes:
     - ./var/log/shop-order:/var/log/order

network:
  default:
    external:
        name: tea-shop
```

##### jar 文件部署

```shell
打包部署
mvn clean instll 

jave -jar  xxx.jar
```

#### 域名配置说明

1. 安装 nginx

```shell
yum -y install nginx
```

ngin.conf配置

```shell
server {
    listen 80;
    server_name www.yuming.com;
    rewrite ^(.*)$ https://$host$1 permanent;
    client_max_body_size 1024m;
    location / {
        proxy_set_header HOST $host;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass http://127.0.0.1:8080/;
    }
}
```

#### Git提交规范
   <b>提交格式：type(scope) : subject</b>

#####type（必须）commit 的类别
######1. feat : 新功能
######2. fix : 修复bug
######3. docs : 文档改变
######4. style : 代码格式改变
######5. refactor : 某个已有功能重构
######6. perf : 性能优化
######7. test : 增加测试
######8. chore : 构建过程或辅助工具的变动

#####scope（可选） 说明 commit 影响的范围

#####subject（必须） commit 的简短描述


#### 参与贡献
[Shao Jie](https://github.com/lzmvlog)
[chenghao](https://github.com/chenghao24)
[AoDIH](https://github.com/AoDIH)
