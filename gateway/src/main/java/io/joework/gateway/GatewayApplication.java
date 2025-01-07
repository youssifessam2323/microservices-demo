package io.joework.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator applicationRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route ->
                        // ordaratak/orders, ordaratak/orders/
                        route.path("/ordaratak/orders", "/ordaratak/orders/**")
                        .filters(filter ->
                                    filter
                                    .rewritePath("/ordaratak/orders", "/orders")
//                                    .rewritePath("/ordaratak/orders/(?<remaining>.*)", "/orders/${remaining}") //TODO: need to know what is the purpose of this template in the regex
                                    .addRequestHeader("X-Response-Time", new Date().toString())
                        )
                        .uri("lb://orders")

                )
                .build();

    }
}
