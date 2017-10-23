# Project Title
Assignment #04
Launch and terminate ec2 instance with shell script.

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

1) launch a terminal and cd to the scripts folder
2) run launch-ec2-instance.sh
```
sh lauch-ec2-instance.sh
```

If you do not have a key pair it will create one for you.
If you already have one it will return an error message, skip this step, and continue to the next step.

check the aws console to see the created instances and security group

### Break down into end to end tests

To terminate the instance, run the terminate-ec2-instance.sh

```
sh terminate-ec2-instance.sh
```
Termination requires some time.
Check the aws console to make sure it is terminated.

## Authors

Team Leader: ShuangShuang Xu xu.shua@husky.neu.edu Team members: Mengfan Xu xu.mengf@husky.neu.edu Mengfei Zhang zhang.mengf@husky.neu.edu YeHiu Rong rong.ye@husky.neu.edu


