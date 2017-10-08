# Project Title

 Set up Domain Name System (DNS), Amazon Web Services Identity & Access Management (IAM),GitHub Repository. 
Start work on the web application development.Design & implement APIs using programming language and framework of your choice with MySQL as the persistent backend data store for user account management. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them


### Installing

install software: intelliJ IDEA, JMeter, RESTful 
running environmnet:java8,tomcat8.5,MySQL 

### Download
DownLoad csye6225-fall2017 assignment3 from github 
run in Terminal:
git clone git@github.com:001239511ShuangShuangXu/csye6225-fall2017.git
### Run application
Change application.properties from resources file:
Change lines 28,29,30 according your database information
spring.datasource.url=jdbc:mysql://localhost:3306/assignment3
spring.datasource.username=root
spring.datasource.password=xmf123

Edit project configurations, add local Tomcat server, and ad  Root.war Gradle
Run this Project

## Running the tests
when main page: localhost:8080 show 
"{"message":"you are logged in. current time is Fri Sep 29 16:09:03 EDT 2017"}"
means you are successful start this application 
Click you Restlet Client API and add authorization 
Input request: 
Register:

#method: POST http://localhost:8080/user/register
#body:

{
"email":"xmf123",
"password":"xmf123"
}

History show 200: success

Login:
#method: POST http://localhost:8080/user/login
#body:

{
"email":"xmf123",
"password":"xmf123"
}
History show 200: success

show all information in database
#method: get http://localhost:8080/user/all
in webpage you can see all registered users' information.
if web security let you input user and password
input user: user password: password

#Testing with JMeter
open JMeter 
add file from csye6225-fall application's file: Sample_Spring_Boot_Application.jmx
run 
show all results

##Travis CI 
login in Travis CI
1.Flick the repository swich on
2. Add .travis.yml file to your repository
3.Trigger your first build with a git push

## Authors
Team Leader: ShuangShuang Xu xu.shua@husky.neu.edu
Team members: Mengfan Xu xu.mengf@husky.neu.edu
              Mengfei Zhang zhang.mengf@husky.neu.edu
              YeHiu Rong rong.ye@husky.neu.edu
