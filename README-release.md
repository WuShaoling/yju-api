## release分支部署步骤

1. 构建源码
./gradlew clean build

2. 提交分支
git add build/
git ci -am "release message"