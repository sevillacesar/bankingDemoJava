# bankingDemoJava
Banking Demo Java Project

## IMPORT THE PROJECT INTO INTELLIJ

    1. Download the project as zip file into your computer
    2. Unzip the project at your desired location
    3. Open the project into intellij

## CREATE THE FOLDER FOR THE BDD DATA

    mkdir -p shared/mysql_data

## CREATE PROJECT TARGET FOR EACH ONE

    mvn clean install -f account.client/pom.xml

    mvn clean install -f account.mov/pom.xml

## CREATE THE DOCKERFILE BUILD

    docker build -q --rm -t client account.client/.
    docker build -q --rm -t mov account.mov/.

## RUN THE COMPOSE

    sudo docker-compose up -d

## FINISH THE CONTAINERS

    docker-compose down

## Test API in Postman

    load File BankingDemoJava.postman_collection.json

## SWAGGER API

    localhost:5000/swagger-ui/index.html 
    localhost:5001/swagger-ui/index.html 