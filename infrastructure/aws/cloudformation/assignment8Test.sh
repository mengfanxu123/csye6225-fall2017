#!/bin/bash



echo "Enter The Stack Name:"&&
read stackname&&

aws cloudformation create-stack --stack-name ${stackname} --template-body file://./assignment8.json --capabilities CAPABILITY_NAMED_IAM&&
echo done
