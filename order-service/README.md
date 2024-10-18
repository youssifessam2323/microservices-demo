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
3. 