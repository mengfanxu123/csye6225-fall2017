#!/bin/bash

echo "Enter The Stack Name:"
read stackname

aws cloudformation delete-stack --stack-name ${stackname}&&
echo done
