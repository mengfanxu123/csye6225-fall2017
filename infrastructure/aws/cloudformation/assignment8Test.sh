#!/bin/bash


VPC_ID=$(aws ec2 describe-vpcs --query "Vpcs[0].VpcId" --output text)&&
echo $VPC_ID&&

SUBNET_ID1=$(aws ec2 describe-subnets --filters "Name=vpc-id, Values=$VPC_ID" --query "Subnets[0].SubnetId" --output text)&&
echo $SUBNET_ID1&&


echo "Enter The Stack Name:"
read stackname

aws cloudformation create-stack --stack-name ${stackname} --template-body file://./assignment8.json --parameters ParameterKey=ParamVpcID,ParameterValue=${VPC_ID} ParameterKey=ParamSubnetId1,ParameterValue=${SUBNET_ID1}&&
echo done
