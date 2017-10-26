#!/bin/bash

i=0
while [ $i -le 100 ] 
do
instanceid=`aws ec2 describe-instances --filters "Name=image-id,Values = ami-cd0f5cb6" | jq -r '.Reservations['$i'].Instances[0].InstanceId'`&&
#instanceid=`jq -r '.Reservations['$i'].Instances[0].InstanceId' ec2Inst.json`&&
if [ "$instanceid" = "null" ]
then
break;
else
echo $instanceid
aws ec2 terminate-instances --instance-ids $instanceid&&
i=$((i+1))
echo "$i"
fi
done

sleep 120s&&
#groupid=`jq -r '.Reservations[0].Instances[0].SecurityGroups[0].GroupId' ec2Inst.json`&&
#echo $groupid
#aws ec2 delete-security-group --group-id $groupid&&
aws ec2 delete-security-group --group-name csye6225-fall2017-webapp&&
#aws ec2 delete-key-pair --key-name MyKeyPair&&
#sudo rm MyKeyPair.pem;
rm test.json;
rm test1.json;
#rm ec2Inst.json;
aws ec2 describe-instances&&
echo done
