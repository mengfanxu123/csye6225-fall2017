cd /etc/tomcat8/bin&&
sh shutdown.sh stop&&
wait ${!}&&
sh startup.sh start&&
echo done