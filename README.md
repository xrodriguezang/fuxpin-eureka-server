# fuxpin-eureka-server

Fuxpin ``Spring Netflix Eureka Server``

Eureka Server provides the Black box Modernization with a microservice layer.

Configuration provided by ``Fuxpin Spring Cloud Config Server``.

Application secured by ``Spring Security``

# This project Uses
<img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" width="200"></br>

&#8594; Spring Cloud Config Server</br>
&#8594; Secured by Spring Security

# Homepage:

https://pi.intranet.cat:8448/

## Current version
![](https://img.shields.io/badge/fuxpin%20eureka%20server-0.0.1-blue)

Eureka Netflix Server implements a Black Box Modernization using a layer of microservices. 
This Sevice Layer provides any interaction with the old application.

# Replication example

Eureka server shows the same service replicated two times:

<img src="https://raw.githubusercontent.com/xrodriguezang/fuxpin-eureka-server/main/src/main/resources/imageDocs/MicroservicesInAction.png" alt="drawing" width="800"/>

It server send the request to the same microservice in two machines. It acts as Load Balancer: 

<img src="https://github.com/xrodriguezang/fuxpin-eureka-server/blob/main/src/main/resources/imageDocs/MicroservicesLog-Balancer.png" alt="drawing" width="800"/>

## Configure the application to get the properties from ***Fuxpin Cloud Config Server***

* Add in buil.gradle, the bootstrap stater:
````
implementation 'org.springframework.cloud:spring-cloud-starter-bootstrap'
````
* Add in buil.gradle, the starter Spring Cloud Config Client:
````
implementation 'org.springframework.cloud:spring-cloud-starter-config'
````
* Add to bootstrap.yml the properties to connect with the Fuxpin Cloud Config Server. Important fields:
````
spring:
  profiles:
    active: localhost   -> Profile actived. example: fuxpin-eureka-server-localhost.yml
  cloud:
    config:
      name: fuxpin-eureka-server -> Base name file
      label: develop -> Branch: develop, master,...
````

This example, in query:

``https://pi.intranet.cat:8446/fuxpin-config-server/fuxpin-eureka-server/localhost/develop``

## Environment variables
In ***VM options*** define:

Localhost:

````
-Dspring.profiles.active=localhost
-Dkeystore.password=password 
-Drest.user=user 
-Drest.password=password
-Deureka.user=user 
-Deureka.password=password 
````

Production:

````
-Dspring.profiles.active=production
-Dkeystore.password=password 
-Drest.user=user 
-Drest.password=password
-Deureka.user=user 
-Deureka.password=password 
 
````

## Examples *Serverless* execution

* default (localhost):

``C:\Users\amgri\.jdks\jdk-11.0.7\bin\java -jar -Drest.user=user -Drest.password=passwrod -Dkeystore.password=password .\fuxpin-eureka-server-0.0.1.jar``

* production:

``C:\Users\amgri\.jdks\jdk-11.0.7\bin\java -jar -Drest.user=user -Drest.password=passwrod -Dkeystore.password=password -Dspring.profiles.active=production .\fuxpin-eureka-server-0.0.1.jar``

Commands to generate .jar:

````
$ gradew clean assemble
````
jar location
````
${PROJECT_DIRECTORY}/build/libs/
````

## Production launcher

````
java -Xms128m -Xmx256m -jar -Dspring.profiles.active=production -Dkeystore.password=password -Drest.user=user -Drest.password=password -Deureka.user=user -Deureka.password=password fuxpin-eureka-server-0.0.1.jar
````

## Create a Run Java Jar Application with Systemd
* For this configuration *pi* user is used to run the serveless installation. Before proced, create a directory:
````
/opt/java-jar 
````
Give the user ang group ownership permissions for the Fuxpin Systems Jars:
````
sudo chown -R pi:pi /opt/java-jars 
````
* Create Systemd Service
````
sudo vi /etc/systemd/system/fuxpineurekaserver.service
````
with contents:
````editorconfig
[Unit]
Description=Fuxpin Cloud Config Server Java service

[Service]
WorkingDirectory=/opt/java-jars
ExecStart=java -Xms128m -Xmx256m -jar -Dspring.profiles.active=production -Dkeystore.password=password -Drest.user=user -Drest.password=password -Deureka.user=user -Deureka.password=password fuxpin-eureka-server-0.0.1.jar
User=pi
Type=simple
Restart=on-failure

[Install]
WantedBy=multi-user.target
````

* Before start the application, reload systemd so that it knows ot the new service added:

````
sudo systemctl daemon-reload
````

* Once realoaded, the service is avaliable:

````
sudo systemctl start fuxpineurekaserver
````
* Also, verify the status:

````
sudo systemctl status fuxpineurekaserver
````
Result:

````
● fuxpineurekaserver.service - Fuxpin Eureka Server
   Loaded: loaded (/etc/systemd/system/fuxpineurekaserver.service; enabled; vendor preset: enabled)
   Active: active (running) since Wed 2021-05-26 17:54:06 CEST; 4min 38s ago
 Main PID: 4908 (java)
    Tasks: 43 (limit: 4915)
   Memory: 207.1M
   CGroup: /system.slice/fuxpineurekaserver.service
           └─4908 /usr/bin/java -Xms128m -Xmx256m -jar -Dspring.profiles.active=production -Dkeystore.password=password -Drest.user=user -Drest.password=password fuxpin-eureka-server-0.0.1.jar

May 26 17:54:41 raspberrypi java[4908]: 2021-05-26 17:54:41.114  INFO 4908 --- [           main] c.n.eureka.DefaultEurekaServerContext    : Initialized
May 26 17:54:41 raspberrypi java[4908]: 2021-05-26 17:54:41.430  INFO 4908 --- [      Thread-10] c.n.e.r.PeerAwareInstanceRegistryImpl    : Got 1 instances from neighboring DS node
May 26 17:54:41 raspberrypi java[4908]: 2021-05-26 17:54:41.438  INFO 4908 --- [      Thread-10] c.n.e.r.PeerAwareInstanceRegistryImpl    : Renew threshold is: 1
May 26 17:54:41 raspberrypi java[4908]: 2021-05-26 17:54:41.440  INFO 4908 --- [      Thread-10] c.n.e.r.PeerAwareInstanceRegistryImpl    : Changing status to UP
May 26 17:54:42 raspberrypi java[4908]: 2021-05-26 17:54:42.303  INFO 4908 --- [           main] u.t.f.e.EurekaserverApplication          : Started EurekaserverApplication in 32.67 seconds (JVM running for 36.142)
May 26 17:54:51 raspberrypi java[4908]: 2021-05-26 17:54:51.132  INFO 4908 --- [io-8448-exec-10] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
May 26 17:55:41 raspberrypi java[4908]: 2021-05-26 17:55:41.446  INFO 4908 --- [a-EvictionTimer] c.n.e.registry.AbstractInstanceRegistry  : Running the evict task with compensationTime 0ms
May 26 17:56:41 raspberrypi java[4908]: 2021-05-26 17:56:41.446  INFO 4908 --- [a-EvictionTimer] c.n.e.registry.AbstractInstanceRegistry  : Running the evict task with compensationTime 0ms
May 26 17:57:41 raspberrypi java[4908]: 2021-05-26 17:57:41.445  INFO 4908 --- [a-EvictionTimer] c.n.e.registry.AbstractInstanceRegistry  : Running the evict task with compensationTime 0ms
May 26 17:58:41 raspberrypi java[4908]: 2021-05-26 17:58:41.445  INFO 4908 --- [a-EvictionTimer] c.n.e.registry.AbstractInstanceRegistry  : Running the evict task with compensationTime 0ms
````

* To stop the application:

````
sudo systemctl stop fuxpineurekaserver
````

* To restart the application:

````
sudo systemctl restart fuxpineurekaserver.service
````

* To enable the service on startup server boot:
````
sudo systemctl enable fuxpineurekaserver
````
Result:

````
Created symlink /etc/systemd/system/multi-user.target.wants/fuxpineurekaserver.service → /etc/systemd/system/fuxpineurekaserver.service.
````

# More info / References:
* https://spring.io/projects/spring-cloud-netflix
* https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html
* https://blog.bi-geek.com/arquitecturas-spring-cloud-netflix-eureka/
* https://spring.io/blog/2015/07/14/microservices-with-spring

