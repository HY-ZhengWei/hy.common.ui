#!/bin/sh

mvn deploy:deploy-file -Dfile=hy.common.ui.jar                              -DpomFile=./src/META-INF/maven/org/hy/common/ui/pom.xml -DrepositoryId=thirdparty -Durl=http://218.21.3.19:1481/repository/thirdparty
mvn deploy:deploy-file -Dfile=hy.common.ui-sources.jar -Dclassifier=sources -DpomFile=./src/META-INF/maven/org/hy/common/ui/pom.xml -DrepositoryId=thirdparty -Durl=http://218.21.3.19:1481/repository/thirdparty
