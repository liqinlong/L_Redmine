set WORK=%cd%
set classpath=.;
del -f proxycupd.jar
cd %WORK%\bin
jar -cvfm redmine.jar MANIFEST.MF com liql nosubmit org
copy redmine.jar %WORK%
del -f redmine.jar
PAUSE