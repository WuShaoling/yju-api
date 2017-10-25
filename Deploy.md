## 部署

- 发送war包
scp <your file path>/phoenix-api-0.0.1-SNAPSHOT.war root@www.x-lab.ac:/data/dockercompose/phoenix/api/webapps/_data

- 重启container
cd /data/dockercompose/phoenix/api
docker-compose restart

## 访问

后端地址：www.x-lab.ac:13001