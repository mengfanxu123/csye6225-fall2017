#!/bin/bash

sudo apt-get update&&
sudo apt-get install python -y&&
curl https://s3.amazonaws.com/aws-cloudwatch/downloads/latest/awslogs-agent-setup.py -O&&
chmod +x ./awslogs-agent-setup.py&&
sudo python ./awslogs-agent-setup.py -n -r us-east-1 -c awslogs.conf&&

sudo systemctl start awslogs.service&&
sudo service tomcat8 restart&&
echo done