# Spring Calculator with Jetty

### Configuration
- Download [Jetty](https://www.eclipse.org/jetty/download.html) to your user home folder.

### Build
- Execute the following command at the terminal inside the root project folder:
    ```
    ./gradlew deploy
    ```
### Execution
- Access your jetty root folder through the terminal and execute the following command:
    ```
    bin/jetty.sh start
    ```
  
- Access your calculator: http://localhost:8080/tema4/calculator;
- To calculate you have to pass the values and the operation through **url parameter**;
- Here's an example of the sum operation with 2 and 3:
    ```
     http://localhost:8080/tema4/calculator?value1=2&op=sum&value2=3
    ```
  
- The following operations are supported:
    - sum;
    - sub;
    - mult;
    - div;
    - pow.
    
- You can also see the log of previous operations by using the **log** parameter:
    ```
     http://localhost:8080/tema4/calculator?log
    ```