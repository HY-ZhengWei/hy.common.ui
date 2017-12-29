#!/bin/sh

cd ./bin

rm -R ./org/hy/common/ui/junit

jar cvfm hy.common.ui.jar MANIFEST.MF META-INF org

cp hy.common.ui.jar ..
rm hy.common.ui.jar
cd ..

