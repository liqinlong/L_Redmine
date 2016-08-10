set WORKDIR=%cd%
set classpath=.
cd %WORKDIR%
java -Xms256m -Xmx512m -DWORKDIR=%WORKDIR% -jar redmine.jar
pause