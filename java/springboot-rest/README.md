## Springboot Rest POC - Jenkins build/infra/run pipeline with Packer and Ansible

## Summary
- [Introduction](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#introduction)
- [Configuration](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#configuration)
- [Build](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#build)
- [Infra](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#infra)
- [Run](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#run)

## Introduction
It's a simple hello java springboot application with three Jenkins jobs to be deployed.
- Job #1 is the Build job, it downloads the project code from its github repository,
run the tests, build the war file and publish it into a JFROG Artifactory repository;
- Job #2 is the Infra job, it downloads the war file from the Artifactory repository,
runs the packer build command which provision a docker image, copy the downloaded
war file to the docker image, calls for ansible to provision the openjdk8 installation
and publishes the image on docker-hub;
- Job #3 is the Run job, it pulls the published image from docker-hub and run it inside a container.
- You can access the application at http://localhost:8282/hello/{name} <- change the name variable to your name.


## Configuration
- Download [Docker](https://www.digitalocean.com/community/tutorials/como-instalar-e-usar-o-docker-no-ubuntu-18-04-pt);
- Optional: to avoid typing sudo, execute the following command in your terminal:
    
        sudo usermod -aG docker ${USER}
    
- To apply the changes, do a logout and a logon into your account.
- Download [Packer](https://www.packer.io/downloads.html);
- For this project you'll need Jenkins and JFROG Artifactory. Execute the _jenkins_artifactory.sh_ script to download its docker images and run them:

        sudo bash jenkins_artifactory.sh

### Artifactory
- Access Artifactory at http://localhost:8081/artifactory
- Login with the default user and password: admin/password;
- 
### Jenkins
- Access Jenkins at http://localhost:8080;
- Create your user and password and download the recommended plugins;
- Access Manage Jenkins > Manage Plugins > Available, and search for the Artifactory plugin, install it;
- Access Manage Jenkins > Configure System, scroll down to Artifactory:
    - Add an Artifactory server with the server ID _jenkins-artifactory-id_;
    - Add the URL http://localhost:8081/artifactory;
    - Add your Artifactory Default Credentials.
- Apply and save.

- **IMPORTANT**: To fully run the project the following ports must be available in your PC: **8080**, **8181** and **8282**.
## Build
- You can deploy the github and totalizer services by accessing each project root folder by terminal and typing:
        
        ./gradlew build
        
        ./gradlew dockerBuild
        
        ./gradlew dockerRun
        
- To deploy twitter service you need to:
    - Set your keys to twitter api in your environment variables, open a terminal window and set them:
    
            export CONSUMER_KEY=**YOURCONSUMERKEY**
            
            export CONSUMER_SECRET=**YOURCONSUMERSECRET**
            
            export ACCESS_TOKEN=**YOURACCESSTOKEN**
            
            export ACCESS_TOKEN_SECRET=**YOURACCESSTOKENSECRET**
        
    - Then navigate to the twitter service project root folder and build the docker image:
    
            ./gradlew build
            
            docker build --build-arg CONSUMER_KEY=${CONSUMER_KEY} --build-arg CONSUMER_SECRET=${CONSUMER_SECRET} --build-arg ACCESS_TOKEN=${ACCESS_TOKEN} --build-arg ACCESS_TOKEN_SECRET=${ACCESS_TOKEN_SECRET} .
            
    - After the build process the image id will be available, copy it and change in the command bellow to run the container:
    
            docker run -p 8282:8080 -d **IMAGE_ID**
        
## Infra
- The totalizer-service becomes available at http://localhost:8080/totalizer/repos/githubUser/tweets/twitterUser;
- Just change the githubUser and twitterUser variables to see its public repositories and tweets.
- You can also access the other services alone:

    - http://localhost:9090/github/githubUser
    - http://localhost:8282/twitter/twitterUser

## Run
- You can access each application dashboard:

    - Totalizer service: http://localhost:8080/hystrix 
    - Github service: http://localhost:9090/hystrix
    - Twitter service: http://localhost:8282/hystrix
    
- Type the hystrix.stream url into the hystrix dashboard field to start monitoring:

    - http://localhost:8080/actuator/hystrix.stream