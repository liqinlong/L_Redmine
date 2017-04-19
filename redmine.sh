sum=`ps -ef|grep redmine.jar|grep $LOGNAME|grep -v grep |wc -l`
if [ $sum -ne 0 ]
then
        echo "redmine is up"
        exit
else
        echo "redmine will up"
fi

pwd=$PWD
CURD=`date +%Y%m%d`
WORK=/home/ubuntu/redmine
mv $WORK/console_output $WORK/console_output_$CURD
nohup java -server -Dfile.encoding="GBK" -jar redmine.jar >> $WORK/console_output  &
cd $pwd
sleep 3
ps -ef|grep redmine

