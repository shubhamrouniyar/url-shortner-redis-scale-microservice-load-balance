**Used Jmeter to stress the load and generate 1 Million concurrent requests.**
  jmeter -n -t LoadTestingUrlShortner.jmx -l results.jtl -Jjmeter.save.saveservice.output_format=xml -Jjmeter.save.saveservice.response_data=true

**TECH STACK AND TECH USED**
Note : It's not production ready but can get ready after few modifiction 

*WE have used Jmeter to stress the load to 1 Million requests.
*Backend Microservice is build using spring boot and java 
*Atomic Counter is used to get a unique count at scale to generate shorturl always , this implementation is similar to snowflake , because of complexitiy we didnt add zookeeper as a cordination service to get the count , if you want you can impleemt that too.
* Atomic count have other CAS (compare and swap implementation) to get a unique count wihtout worrying about the threads , locking it works on hardware level robust and resilient.
* Load balancer can we used to scale the microservice URL shorner (Easy but not implementted in this project)
* Redis is used to cache the data in redis cluster (Redis code is added but not integrated with the applicvation).
* Inmememory Database impementation is there to persist URL data for short time.
* ThreadpoolExecutor with aysnc used to run the api in aysnc mdoe with 10 Threads concurrently. (Multithreading)

**JAVA, SPRINGBOOT, MULTITHREADING , REDIS CACHE , ATOMICITY, LOAD BALANCER, Zookeeper ,IN MEMORY DATABASE, MICROSERVICES**

  
