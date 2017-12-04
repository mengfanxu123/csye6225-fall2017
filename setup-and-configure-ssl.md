# Project Title
Assignment #10
Create and delete CloudFormation stack using the shell scripts.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
Get SSL Certificate
```
https://www.sslforfree.com/
```
Follow the instruction to verificate DNS on Route53 and download the certificate files.
Import certificates in aws with certificate manager.

### Configure Instance

Connect to instance
Create certificates file in instance, add ".crt" and ".key“ file.

Run the following command line to create .p12 file
```
sudo openssl pkcs12 -export -in <crtFilename>.crt -inkey <keyFileName>.key -out test.p12 -name tomcat
Create your <password>
```
```
keytool -importkeystore -deststorepass <password> -destkeypass <password> -destkeystore test.ketstore -srckeystore test.p12 -srcstoretype PKCS12 -srcstorepass <password> -alias tomcat
```
Go to /var/lib/tomcat8/Conf
Edit server.xml file port=8443
Add keystorefile="<file path>", keystorepass="<password>"

Configure loadbalancer, select corresbonding certificate.
Go to Route 53 add Alias, select correct Alias target (name start same as target group used in loadbalancer, may have some delay).


### Break down into end to end tests

Input https://<your domain name>:8443  in browser to check if it is working.

## Authors

Team Leader: ShuangShuang Xu xu.shua@husky.neu.edu Team members: Mengfan Xu xu.mengf@husky.neu.edu Mengfei Zhang zhang.mengf@husky.neu.edu YeHiu Rong rong.ye@husky.neu.edu

