#!/bin/bash
sudo rm -r /var/lib/tomcat8/webapps/ROOT&&
sudo rm -r /var/lib/tomcat8/webapps/ROOT.war&&
sudo service tomcat8 restart&&
echo done