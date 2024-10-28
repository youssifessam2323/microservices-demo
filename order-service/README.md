## how to connect to spring cloud config server
1. adding the dependency of spring cloud config client
2. adding the spring cloud version and dependency management section
3. add the actuator dependency
4. add the spring.config.import in the application.yml to point to the config server location
5. to have the /refresh endpoint enabled add management.endpoints.web.exposure.include= refresh


## using Spring cloud bus for automatically refreshing the configuration without using the /refresh on each instance
1. adding the spring-cloud-starter-bus-amqp
2. make sure to have rabbitmq instance up and running (docker cmd docker run -d --hostname my-rabbit --name rabbit-mq -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:3-management)
3. adding the rabbit configuration(spring.rabbitmq.host & spring.rabbitmq.port & spring.rabbitmq.username & spring.rabbitmq.password)
4. enable the busrefresh actuator endpoint

## Adding Spring Cloud monitor to support automatically changing the configuration using github webhooks
1. Adding the spring cloud monitor dependency
2. create new github webhook and adding the callback url that spring cloud monitor will provide (http://localhost:8080/monitor)


## Connecting with Eureka
1. Adding the eureka client dependency
2. adding the eureka configuration in the application.yml
```
eureka:
  instance:
    preferIpAddress: true
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      defaultZone: http://localhost:8070/eureka/
```
- preferIpAddress: this tell eureka client to use the ip instead of host name
3. Adding the following section to add info about the service when it registers with the service discovery
```
info:
   app:
    name: "orders"
    description: "an order microservice"
    version: 1.0.0

```
to enable this we need to add this under the actuator management section
```
management:
    info:
      env:
        enabled: true
```
**Note:** this allows when we click on the instance in eureka dashboard to open /actuator/info to see this details 

## Enable Shutdown actuator to gracefully shut down the service
1. enable the shutdown actuator endpoint
```
management:
  endpoint:
    shutdown:
      enabled: true
```
2. adding this to  enable the shutdown in the service
```
endpoints:
    shutdown:
        enabled: true
```


## How to use Feign Client to communicate with the microservices
1. adding the FeignClient dependency
2. adding @EnableFeignClients on the main application
3. NOTE: to add a qualifier to feign client use the @FeignClient "qualifiers" attributes add the name for your bean
4. 