# Project Title
Assignment #04
Create and delete CloudFormation stack using the shell scripts.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
Install and setup AWS cammand line interface
```
http://docs.aws.amazon.com/cli/latest/userguide/installing.html
```
```
http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html
```

Install jq on your virtual machine
Command line for Ubuntu:

```
sudo apt-get install jq
```
### Running the tests

1) launch a terminal and cd to the cloudformation folder
2) run create-csye6225-cloudformation.stack.sh
```
sh create-csye6225-cloudformation.stack.sh
```

Check the aws console to see the created stack.

### Break down into end to end tests

To delete the stack, run the delete-csye6225-cloudformation.sh

```
sh delete-csye6225-cloudformation.sh
```
Check the aws console to make sure it is deleted.

## Authors

Team Leader: ShuangShuang Xu xu.shua@husky.neu.edu Team members: Mengfan Xu xu.mengf@husky.neu.edu Mengfei Zhang zhang.mengf@husky.neu.edu YeHiu Rong rong.ye@husky.neu.edu


