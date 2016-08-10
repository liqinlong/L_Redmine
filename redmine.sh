sum=`ps -ef|grep redmine.jar|grep $LOGNAME|grep -v grep |wc -l`
if [ $sum -ne 0 ]
then
        echo "redmine proxy is up"
        exit
else
        echo "redmine proxy will up"
fi

pwd=$PWD
WORK=/home/epay/jzcupd
nohup java -server -Dfile.encoding="GBK" -jar redmine.jar >> $WORK/console_output  &
cd $pwd
sleep 3
ps -ef|grep redmine

