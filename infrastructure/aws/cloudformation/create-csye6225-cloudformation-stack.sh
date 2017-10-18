#!/bin/bash


VPC_ID=$(aws ec2 describe-vpcs --query "Vpcs[0].VpcId" --output text)&&
echo $VPC_ID&&

SUBNET_ID=$(aws ec2 describe-subnets --filters "Name=vpc-id, Values=$VPC_ID" --query "Subnets[0].SubnetId" --output text)&&
echo $SUBNET_ID&&

HOSTEDZONE_ID=$(aws route53 list-hosted-zones --query "HostedZones[0].Id" --output text)&&
echo $HOSTEDZONE_ID&&

NAME=$(aws route53 list-hosted-zones --query "HostedZones[0].Name" --output text)&&

echo "Enter The Stack Name:"
read stackname

aws cloudformation create-stack --stack-name ${stackname} --template-body file://./cloudFormation.json --parameters ParameterKey=ParamSubnetID,ParameterValue=$SUBNET_ID ParameterKey=ParamVpcID,ParameterValue=$VPC_ID ParameterKey=ParamHostedZoneID,ParameterValue=$HOSTEDZONE_ID ParameterKey=ParamRecordSetsName,ParameterValue=${NAME}&&

echo done
