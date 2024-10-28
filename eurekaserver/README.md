## application steps to have a eurkea service discovery
1. @EnableEurekaServer on our main class
2. adding the following configuration
```
eureka:
  instance:
    hostname: localhost
  client:
    fetch-registry: false
    register-with-eureka: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/`
```
1. client.fetch-registry: disable as we don't want to tell eureka to fetch the registry of the instances registered with itself
2. register-with-eureka: as we have a single instances we don't need to register our eureka to itself as this is not useful

**NOTE**: THIS is in the client config of eureka, so the default values are assumming that this is micorservices that are trying to register 
with eureka 

## exposes liveness and Readiness states using actuator
```
management:
  endpoints:
    web:
      exposure:
        include: *
  health:
    readiness-state:
      enabled: true
    liveness-state:
      enabled: true
```
this exposes the endpoints that we can check the health of our service discovery using docker compose or any health check service
