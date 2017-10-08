#!/bin/bash
aws cloudformation create-stack --stack-name myteststack --template-body file:///home/xss/cloudFormation.json&&

sleep 60s&&

aws ec2 describe-instances --filters "Name=image-id,Values = ami-cd0f5cb6" >cloudFormation-ec2Inst.json&&
i=0
while [ $i -le 100 ] 
do
ipa=`jq -r '.Reservations['$i'].Instances[0].PublicIpAddress' cloudFormation-ec2Inst.json`&&
echo $ipa
if [ "$ipa" = "null" ]
then
i=$((i+1))
echo "$i"
else
#aws ec2 terminate-instances --instance-ids $instanceid&&
break;
fi
done
#ip=`jq -r '.Reservations[0].Instances[0].PublicIpAddress' ec2Inst.json`&&
#echo $ip
jq -r '.Resources.myDNSRecord2.Properties.ResourceRecords[0]="'$ipa'"' cloudFormation1.json >cloudFormation2.json&&

sleep 60s

aws cloudformation update-stack --stack-name myteststack --template-body file:///home/xss/cloudFormation2.json
echo done
#aws ec2 delete-security-group --group-name my-sg
#aws ec2 delete-security-group --group-id
#aws ec2 delete-key-pair --key-name MyKeyPair
#aws ec2 terminate-instances --instance-ids 


