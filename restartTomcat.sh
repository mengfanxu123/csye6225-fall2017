cd /home/rebe/apache-tomcat-8.5.20/bin&&
sh shutdown.sh stop&&
wait ${!}&&
sh startup.sh start&&
echo done