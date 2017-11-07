## 镜像制作步骤

1. cp build/libs/phoenix-api-0.0.1-SNAPSHOT.jar ./
2. cp src/main/resources/application.properties ./
3. 修改application.properties 中相关配置信息
4. docker build -t phoenix_api:1.0.0 ./
5. docker save phoenix_api:1.0.0 phoenix_api.dk.tar

## 导入镜像步骤
1. docker load < phoenix_api.dk.tar