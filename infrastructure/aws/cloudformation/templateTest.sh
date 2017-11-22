#!/bin/bash


VPC_ID=$(aws ec2 describe-vpcs --query "Vpcs[0].VpcId" --output text)&&
echo $VPC_ID&&

SUBNET_ID1=$(aws ec2 describe-subnets --filters "Name=vpc-id, Values=$VPC_ID" --query "Subnets[0].SubnetId" --output text)&&
echo $SUBNET_ID1&&

SUBNET_ID2=$(aws ec2 describe-subnets --filters "Name=vpc-id, Values=$VPC_ID" --query "Subnets[1].SubnetId" --output text)&&
echo $SUBNET_ID2&&

SUBNET_ID3=$(aws ec2 describe-subnets --filters "Name=vpc-id, Values=$VPC_ID" --query "Subnets[2].SubnetId" --output text)&&
echo $SUBNET_ID3&&

SUBNET_ID4=$(aws ec2 describe-subnets --filters "Name=vpc-id, Values=$VPC_ID" --query "Subnets[3].SubnetId" --output text)&&
echo $SUBNET_ID4&&

echo "Enter The Stack Name:"
read stackname

aws cloudformation create-stack --stack-name ${stackname} --template-body file://./template.json --parameters ParameterKey=ParamVpcID,ParameterValue=${VPC_ID} ParameterKey=ParamSubnetId1,ParameterValue=${SUBNET_ID1} ParameterKey=ParamSubnetId2,ParameterValue=${SUBNET_ID2} ParameterKey=ParamSubnetId3,ParameterValue=${SUBNET_ID3} ParameterKey=ParamSubnetId4,ParameterValue=${SUBNET_ID4} --capabilities CAPABILITY_NAMED_IAM&&
echo done
