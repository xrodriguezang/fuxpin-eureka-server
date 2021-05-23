# fuxpin-eureka-server

Fuxpin ``Spring Cloud Netflix Eureka``

Eureka Server that provides the Black box Modernization with a microservice layer.

# Homepage:

https://xrodrig.dnsnet.info:8446/

## Current version
![](https://img.shields.io/badge/fuxpin%20legacy%20ventas%20rol%20microservice-0.0.1-blue)

## Enviorment variables
For SSL keytore, to configure de keytore password, in ***VM options*** define:
````
-Dkeystore.password=password 
````

## Configuration properties arae provided by ***Fuxpin Cloud Config Server*** 

* Add in buil.gradle:
````
implementation 'org.springframework.cloud:spring-cloud-starter-bootstrap'
````
* Starter Spring Cloud Config Client
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

# More info:
* https://spring.io/projects/spring-cloud-netflix
* https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html
* https://blog.bi-geek.com/arquitecturas-spring-cloud-netflix-eureka/
* https://spring.io/blog/2015/07/14/microservices-with-spring
* https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html

