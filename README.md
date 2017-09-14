运行 gradle :bootRun

```
.
├── README.md
├── build.gradle
├── dependencies.gradle
├── gradlew
├── gradlew.bat
├── opera-api.iml
├── settings.gradle
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── guanshan
│   │   │           └── opera
│   │   │               └── webapp                                          //
│   │   │                   ├── Application.java                            //spring boot 应用主入口
│   │   │                   ├── appConfig.java                              //spring boot 配置
│   │   │                   ├── controller                                  //控制器
│   │   │                   │   ├── ModelController.java
│   │   │                   │   └── UserController.java
│   │   │                   ├── dao                                         //dao
│   │   │                   │   ├── BaseDao.java
│   │   │                   │   ├── ModelDao.java
│   │   │                   │   └── UserDao.java
│   │   │                   ├── middleware                                  //mq中间件
│   │   │                   │   ├── ConsumerMessageListener.java
│   │   │                   │   ├── MessageSender.java
│   │   │                   │   └── Receiver.java
│   │   │                   ├── rds
│   │   │                   │   ├── BaseDao.java
│   │   │                   │   └── ModelDaoImpl.java
│   │   │                   ├── security                                    //spring security
│   │   │                   │   ├── ClientCredentialsTokenEndpointFilter.java
│   │   │                   │   ├── Insight365User.java
│   │   │                   │   ├── Insight365UserDetails.java
│   │   │                   │   ├── Insight365UserRepository.java
│   │   │                   │   ├── Insight365UserServiceImpl.java
│   │   │                   │   ├── RoleVoter.java
│   │   │                   │   └── UnanimousBased.java
│   │   │                   ├── service                                     //服务层
│   │   │                   │   ├── ModelService.java
│   │   │                   │   └── UserService.java
│   │   │                   └── shared
│   │   │                       ├── entity                                  //POJO
│   │   │                       │   ├── Model.java
│   │   │                       │   └── User.java
│   │   │                       ├── logic
│   │   │                       │   └── Logic.java
│   │   │                       └── util                                    //通用工具
│   │   │                           ├── JPush                               //推送
│   │   │                           │   ├── JMessageGroup.java
│   │   │                           │   ├── JMessageHelper.java
│   │   │                           │   ├── JMessageIM.java
│   │   │                           │   ├── JMessageUser.java
│   │   │                           │   ├── JPushHelper.java
│   │   │                           │   └── JSMSHelper.java
│   │   │                           ├── codec                               //加密等方法
│   │   │                           │   ├── Const.java
│   │   │                           │   ├── EncryptBase64.java
│   │   │                           │   ├── HtmlParser.java
│   │   │                           │   ├── HttpClient4Utils.java
│   │   │                           │   ├── MD5.java
│   │   │                           │   ├── ResponseMessage.java
│   │   │                           │   ├── SHA1.java
│   │   │                           │   ├── SpringDataPageable.java
│   │   │                           │   └── StrGenerator.java
│   │   │                           └── string                              //字符串处理
│   │   │                               └── RandomStringGenerator.java
│   │   └── resources
│   │       ├── config                                                      //spring boot 配置文件
│   │       │   ├── application-dev.properties
│   │       │   ├── application-prod.properties
│   │       │   └── application.properties
│   │       ├── mapper                                                      //mybatis mapping
│   │       │   ├── ModelMapper.xml
│   │       │   └── UserMapper.xml
│   │       └── spring                                                      //spring配置文件
│   │           ├── spring-db.xml
│   │           ├── spring-jms.xml
│   │           └── spring-security-oauth2.xml
│   └── test
│       └── java
│           └── com
│               └── guanshan
│                   └── opera
│                       └── ut
│                           ├── IndexControllerTest.java
│                           ├── TestApplication.java
│                           └── UserControllerTest.java
├── task.gradle
```