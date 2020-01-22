# Tema 8 - Cloud Native

### Configuration
- Download Docker through [this](https://www.digitalocean.com/community/tutorials/como-instalar-e-usar-o-docker-no-ubuntu-18-04-pt) tutorial.
- Optional: to avoid typing sudo, execute the following command in your terminal:
    
        sudo usermod -aG docker ${USER}
    
- To apply the changes, do a logout and a logon into your account.

### Deploy
- You can deploy the calculator-service by accessing the project root folder through terminal and typing:
        
        ./gradlew fatJar
        
        ./gradlew dockerBuild
        
        ./gradlew dockerRun
        
### Execution
- The calculator-service becomes available at http://localhost:8080/totalizer/calculator/firstValue/operation/secondValue;
- Just change the firstValue, operation and secondValue variables to the values you want;
- Here's an example of the sum operation with 2 and 3:

        http://localhost:8080/calculator/2/sum/3
  
- The following operations are supported:
    - sum;
    - sub;
    - mult;
    - div;
    - pow.
    
- You can also see the log of previous operations by using the **log** parameter:

        http://localhost:8080/calculator/logs