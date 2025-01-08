## how to create new gateway server using spring cloud API gateway
***
1. Adding the following dependencies
   1. spring cloud api gateway
   2. spring cloud config client (optional)
   3. eureka client (optional)
   4. actuator (optional)
2. in the application.yml add the following 
```yaml
spring:
  application:
    name: "gatewayserver" # name of the application for discovery service
  config:
    import: "optional:configserver:http://localhost:8071/" # connect with the config server
  cloud:
    gateway:
      discovery:
        locator:
          enabled: false #disable using eureka default locator (example: http://edgeserver/<serviceId>) for locating microservices
          lowerCaseServiceId: true #allow to use lowercase application name in our http requests.
      httpclient:
        connect-timeout: 1000
        response-timeout: 10s
management:
  endpoints:
    web:
      exposure:
        include: "*" #expose all the actuator endpoints
  endpoint:
    gateway:
      enabled: true #enable the gateway actuator endpoint 
  info:
    env:
      enabled: true
#  metrics: not now
#    tags:
#      application: ${spring.application.name}

info:
  app:
    name: "gatewayserver"
    description: "Eazy Bank Gateway Server Application"
    version: "1.0.0"

```

## Global Filters
The GlobalFilter interface has the same signature as GatewayFilter. These are special filters that are conditionally applied to all routes.


## The Local Response Cache Filter
The LocalResponseCache runs if associated properties are enabled:

spring.cloud.gateway.global-filter.local-response-cache.enabled: Activates the global cache for all routes

spring.cloud.gateway.filter.local-response-cache.enabled: Activates the associated filter to use at route level

This feature enables a local cache using Caffeine for all responses that meet the following criteria:

The request is a bodiless GET.

The response has one of the following status codes: HTTP 200 (OK), HTTP 206 (Partial Content), or HTTP 301 (Moved Permanently).

The HTTP Cache-Control header allows caching (that means it does not have any of the following values: no-store present in the request and no-store or private present in the response).

It accepts two configuration parameters:

spring.cloud.gateway.filter.local-response-cache.size: Sets the maximum size of the cache to evict entries for this route (in KB, MB and GB).

spring.cloud.gateway.filter.local-response-cache.time-to-live Sets the time to expire a cache entry (expressed in s for seconds, m for minutes, and h for hours).

If none of these parameters are configured but the global filter is enabled, by default, it configures 5 minutes of time to live for the cached response.

This filter also implements the automatic calculation of the max-age value in the HTTP Cache-Control header. If max-age is present on the original response, the value is rewritten with the number of seconds set in the timeToLive configuration parameter. In subsequent calls, this value is recalculated with the number of seconds left until the response expires.

Setting spring.cloud.gateway.global-filter.local-response-cache.enabled to false deactivate the local response cache for all routes, the LocalResponseCache filter allows to use this functionality at route level.