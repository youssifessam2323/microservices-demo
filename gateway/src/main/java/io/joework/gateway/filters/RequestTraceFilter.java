package io.joework.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Order(0)
@Component
public class RequestTraceFilter implements GlobalFilter {

    public static final String CORRELATION_ID = "X-Correlation-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeaders)) {
            log.debug("X-Correlation-Id found in RequestTraceFilter : {}",
                    requestHeaders.get(CORRELATION_ID).stream().findFirst().get());
        } else {
            String correlationID = generateCorrelationId();
            exchange = exchange
                    .mutate()
                    .request(exchange.getRequest().mutate().header(CORRELATION_ID, correlationID).build())
                    .build();
            log.debug("X-Correlation-Id generated in RequestTraceFilter : {}", correlationID);
        }
        return chain.filter(exchange);
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        return requestHeaders.containsKey("X-Correlation-Id");
    }
}
