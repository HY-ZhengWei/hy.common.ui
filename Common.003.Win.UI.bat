

del /Q hy.common.ui.jar
del /Q hy.common.ui-sources.jar


call mvn clean package
cd .\target\classes


rd /s/q .\org\hy\common\ui\junit



jar cvfm hy.common.ui.jar META-INF/MANIFEST.MF META-INF org

copy hy.common.ui.jar ..\..
del /q hy.common.ui.jar
cd ..\..





cd .\src\main\java
xcopy /S ..\resources\* .
jar cvfm hy.common.ui-sources.jar META-INF\MANIFEST.MF META-INF org 
copy hy.common.ui-sources.jar ..\..\..
del /Q hy.common.ui-sources.jar
rd /s/q META-INF
cd ..\..\..

pause