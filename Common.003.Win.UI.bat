

cd .\bin

rd /s/q .\org\hy\common\ui\junit

jar cvfm hy.common.ui.jar MANIFEST.MF META-INF org

copy hy.common.ui.jar ..
del /q hy.common.ui.jar
cd ..

