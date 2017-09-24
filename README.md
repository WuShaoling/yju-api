
```
.
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── guanshan
    │   │           └── phoenix
    │   │               └── webapp
    │   │                   ├── application
    │   │                   │   └── config					// 配置层
    │   │                   ├── authentication				// 认证模块
    │   │                   │   ├── auth
    │   │                   │   ├── authUser
    │   │                   │   └── security
    │   │                   ├── controller					// 控制层
    │   │                   ├── dao							// 持久层
    │   │                   │   ├── entity
    │   │                   │   └── mapper
    │   │                   ├── service						// 服务接口层
    │   │                   │   └── imp						// 服务实现层
    │   │                   │── shared
    │   │                   │    └── util					// 工具类
	│   │                   └── PhoenixApplication.java		// 应用入口
    │   └── resources
    │       └── application.properties						// SpringBoot配置文件
    └── test
```