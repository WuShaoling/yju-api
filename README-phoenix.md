# 部署步骤

# 配置
1. vi src/main/resources/application.properties

## API
1. ./gradlew clean build
2. docker build -t phoenix-api .
3. docker run -d --restart=always -p 13001:8080 --name phoenix-api -h phoenix-api  phoenix-api


## DB
1. docker run -d --restart=always -p 13002:3306 -e  MYSQL_ROOT_PASSWORD=guanshantech --name phoenix-db -h phoenix-db  mysql:latest

## 导出数据库
1. mysqldump -u root -pguanshantech -h127.0.0.1 -P13002 phoenix > /tmp/phoenix-db.sql

## 导入数据库
1. mysql -uroot -pguanshantech -h127.0.0.1 -P13002 
2. create database phoenix default charset utf8 collate utf8_general_ci;
3. use phoenix
4. source /tmp/phoenix-db.sql