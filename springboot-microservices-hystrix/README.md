# Springboot Rest Microservices with Hystrix

### Configuration
- Download [Docker](https://www.digitalocean.com/community/tutorials/como-instalar-e-usar-o-docker-no-ubuntu-18-04-pt) through this tutorial.
- Optional: to avoid typing sudo, execute the following command in your terminal:
    
        sudo usermod -aG docker ${USER}
    
- To apply the changes, do a logout and a logon into your account.

- **IMPORTANT**: The following ports must be available in your PC: **8080**, **8282** and **9090**.
### Deploy
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
        
### Execution
- The totalizer-service becomes available at http://localhost:8080/totalizer/repos/githubUser/tweets/twitterUser;
- Just change the githubUser and twitterUser variables to see its public repositories and tweets.
- You can also access the other services alone:

    - http://localhost:9090/github/githubUser
    - http://localhost:8282/twitter/twitterUser

### Hystrix Dashboard
- You can access each application dashboard:

    - Totalizer service: http://localhost:8080/hystrix 
    - Github service: http://localhost:9090/hystrix
    - Twitter service: http://localhost:8282/hystrix
    
- Type the hystrix.stream url into the hystrix dashboard field to start monitoring:

    - http://localhost:8080/actuator/hystrix.stream