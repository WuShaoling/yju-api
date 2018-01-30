# 部署步骤

# 配置
1. vi src/main/resources/application.properties

## API
1. ./gradlew clean build
2. docker build -t phoenix-api .
3. docker run -d --restart=always -p 13001:8080 --name phoenix-api -h phoenix-api  phoenix-api


## DB
1. docker run -d --restart=always -p 1302:8080 --name phoenix-db -h phoenix-db  mysql:latest
