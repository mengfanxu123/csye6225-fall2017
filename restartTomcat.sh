#!/bin/bash

cd /var/lib/tomcat8/webapps&&
sudo rm -r ROOT&&
sudo service tomcat8 restart&&
echo done