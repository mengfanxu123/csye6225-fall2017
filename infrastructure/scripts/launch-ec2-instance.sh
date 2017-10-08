#!/bin/bash
aws ec2 create-key-pair --key-name MyKeyPair --query 'KeyMaterial' --output text >MyKeyPair.pem
#sudo chmod 400 MyKeyPair.pem&&
#aws ec2 describe-key-pairs --key-name MyKeyPair&&
aws ec2 create-security-group --group-name csye6225-fall2017-webapp --description "My security group"&&
#aws ec2 authorize-security-group-ingress --group-name my-sg --protocol tcp --port 3389 --cidr 0.0.0.0/0
aws ec2 authorize-security-group-ingress --group-name csye6225-fall2017-webapp --protocol tcp --port 22 --cidr 0.0.0.0/0&&
aws ec2 authorize-security-group-ingress --group-name csye6225-fall2017-webapp --protocol tcp --port 80 --cidr 0.0.0.0/0&&
aws ec2 authorize-security-group-ingress --group-name csye6225-fall2017-webapp --protocol tcp --port 443 --cidr 0.0.0.0/0&&
aws ec2 run-instances --image-id ami-cd0f5cb6 --count 1 --instance-type t2.micro --key-name MyKeyPair --security-groups csye6225-fall2017-webapp&&

sleep 60s&&

aws ec2 describe-instances --filters "Name=image-id,Values = ami-cd0f5cb6" >ec2Inst.json&&
i=0
while [ $i -le 100 ] 
do
ipa=`jq -r '.Reservations['$i'].Instances[0].PublicIpAddress' ec2Inst.json`&&
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

NAME=$(aws route53 list-hosted-zones --query "HostedZones[0].Name" --output text)&&
echo $NAME&&

NAMED=ec.${NAME}&&

jq -r '.Changes[0].ResourceRecordSet.Name="'${NAMED}'"' jsonStr.json >test.json&&

jq -r '.Changes[0].ResourceRecordSet.ResourceRecords[0].Value="'$ipa'"' test.json >test1.json&&

HOSTEDZONE_ID=$(aws route53 list-hosted-zones --query "HostedZones[0].Id" --output text)&&
echo $HOSTEDZONE_ID&&

aws route53 change-resource-record-sets --hosted-zone-id ${HOSTEDZONE_ID} --change-batch file://./test1.json&&
echo done
#aws ec2 delete-security-group --group-name my-sg
#aws ec2 delete-security-group --group-id
#aws ec2 delete-key-pair --key-name MyKeyPair
#aws ec2 terminate-instances --instance-ids 


