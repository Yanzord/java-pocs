## Springboot Rest POC - Jenkins build/infra/run pipeline with Packer and Ansible

## Summary
- [Introduction](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#introduction)
- [Configuration](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#configuration)
- [Build app](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#build-app)
- [Infra](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#infra)
- [Run](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#run)
- [Execution](https://github.com/Yanzord/yanzord-sandbox/tree/master/java/springboot-rest#execution)

## Introduction
It's a simple hello java springboot application with three Jenkins jobs to be deployed.
- Job #1 is the Build job, it downloads the project code from its github repository,
run the tests, build the war file and uploads it to a JFROG Artifactory repository;
- Job #2 is the Infra job, it downloads the war file from the Artifactory repository,
runs the packer build command which provision a docker image, copy the downloaded
war file to the image then calls for ansible to provision an openjdk8 installation,
after that packer uploads the image to docker-hub;
- Job #3 is the Run job, it pulls the published image from docker-hub and run it inside a container;
- You can access the application at http://localhost:8282/hello/{name} <- change the name variable to your name.  
    
Link to the youtube video where i describe what is happening during the pipelines execution:
  - https://youtu.be/mE0Kb7g9ghg
  
## Configuration
- **IMPORTANT**: 
    - To fully run the project the following ports must be available in your PC: **8080**, **8081** and **8282**;

- Download Docker:
    - Execute the following commands inside your terminal:
    - To update your current package list:
    
            sudo apt update
    
    - Install some prerequisite packages that allow apt to use packages over HTTPS:
            
            sudo apt install apt-transport-https ca-certificates curl software-properties-common
    
    - Add the GPG key to the official Docker repository in your system:
    
            curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
    
    - Add the Docker repository to the apt sources:
    
            sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
    
    - Update your current package list again:
    
            sudo apt update
            
    - Install Docker:
            
            sudo apt install docker-ce
            
- Optional: to avoid typing sudo, execute the following command in your terminal:
    
        sudo usermod -aG docker ${USER}
    
- To apply the changes, do a logout and a logon into your account.
- Download [Packer](https://www.packer.io/downloads.html);
- For this project you'll need Jenkins and JFROG Artifactory. Execute the _jenkins_artifactory.sh_ script to download its docker images and run them:

        sudo bash jenkins_artifactory.sh

### Artifactory
- Access Artifactory at http://localhost:8081/artifactory
- Login with the default user and password: admin/password;
- Set your new admin password, and make the default configurations;
- Go to Welcome, admin > Quick Setup and create a gradle repository.

### Jenkins
- Access Jenkins at http://localhost:8080;
- Create your user and password and download the recommended plugins;


- For Artifactory server config:
    - Access Manage Jenkins > Manage Plugins > Available, and search for the Artifactory plugin, install it;
    - Access Manage Jenkins > Configure System, scroll down to Artifactory:
        - Add an Artifactory server ID of your choice;
        - Add the Artifactory URL (default is http://localhost:8081/artifactory);
        - Add your Artifactory login.
    - Apply and save.
    

- For Artifactory credentials config:
    - Access Jenkins > Credentials > System > Global Credentials (unrestricted);
    - Click on Add Credentials, select "Secret text" Kind;
    - Add your artifactory server ID in "Secret", add "artifactory-server-id" in "ID" and save;
    - Click on Add Credentials, select "Secret text" Kind;
    - Add your artifactory url in "Secret", add "artifactory-server-url" in "ID" and save;
    - Click on Add Credentials, select "Username with password" Kind;
    - Here you'll add your JFROG Artifactory login to be used as variables in the Jenkinsfiles;
    - Add 'jfrog-credentials' in the ID field and save.
    
    
- For docker-hub credentials config    
    - Click on Add Credentials, select "Username with password" Kind;
    - Here you'll add your docker-hub login to be used as variables in the Jenkinsfiles;
    - Add 'docker-hub-credentials' in the ID field and save.

## Build app
To create the #1 job:
- Go to Jenkins;
- Access New Item, insert "build-app" in "Enter an item name" and select "Pipeline", click OK;
- Scroll down to "Pipeline", select "Pipeline script form SCM" in "Definition";
- Select "Git" in "SCM";
- Insert https://github.com/Yanzord/yanzord-sandbox.git in "Repository URL";
- Insert "java/springboot-rest/build-app/Jenkinsfile" in "Script Path";
- Apply and save.
        
## Infra
To create the #2 job:
- Go to Jenkins;
- Access New Item, insert "infra" in "Enter an item name" and select "Pipeline", click OK;
- Scroll down to "Pipeline", select "Pipeline script form SCM" in "Definition";
- Select "Git" in "SCM";
- Insert https://github.com/Yanzord/yanzord-sandbox.git in "Repository URL";
- Insert "java/springboot-rest/infra/Jenkinsfile" in "Script Path";
- Apply and save.

## Run
To create the #3 job:
- Go to Jenkins;
- Access New Item, insert "run" in "Enter an item name" and select "Pipeline", click OK;
- Scroll down to "Pipeline", select "Pipeline script form SCM" in "Definition";
- Select "Git" in "SCM";
- Insert https://github.com/Yanzord/yanzord-sandbox.git in "Repository URL";
- Insert "java/springboot-rest/run/Jenkinsfile" in "Script Path";
- Apply and save.

## Execution
To execute the full deploy pipeline:
- Access build-app job and click on "Build Now";
- After it ends do the same to infra job and run job.
- You should be able to see the application running at http://localhost:8282/hello/