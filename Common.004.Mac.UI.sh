#!/bin/sh

cd ./bin


rm -R ./org/hy/common/ui/junit


jar cvfm hy.common.ui.jar MANIFEST.MF META-INF org

cp hy.common.ui.jar ..
rm hy.common.ui.jar
cd ..





cd ./src
jar cvfm hy.common.ui-sources.jar MANIFEST.MF META-INF org 
cp hy.common.ui-sources.jar ..
rm hy.common.ui-sources.jar
cd ..
