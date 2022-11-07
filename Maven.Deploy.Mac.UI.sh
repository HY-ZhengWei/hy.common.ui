#!/bin/sh

mvn install:install-file -Dfile=hy.common.ui.jar                              -DpomFile=./src/main/resources/META-INF/maven/cn.openapis/hy.common.ui/pom.xml
mvn install:install-file -Dfile=hy.common.ui-sources.jar -Dclassifier=sources -DpomFile=./src/main/resources/META-INF/maven/cn.openapis/hy.common.ui/pom.xml

mvn deploy:deploy-file   -Dfile=hy.common.ui.jar                              -DpomFile=./src/main/resources/META-INF/maven/cn.openapis/hy.common.ui/pom.xml -DrepositoryId=thirdparty -Durl=http://HY-ZhengWei:8081/repository/thirdparty
mvn deploy:deploy-file   -Dfile=hy.common.ui-sources.jar -Dclassifier=sources -DpomFile=./src/main/resources/META-INF/maven/cn.openapis/hy.common.ui/pom.xml -DrepositoryId=thirdparty -Durl=http://HY-ZhengWei:8081/repository/thirdparty
