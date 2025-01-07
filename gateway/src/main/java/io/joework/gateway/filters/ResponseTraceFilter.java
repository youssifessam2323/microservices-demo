package io.joework.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class ResponseTraceFilter {
    public static final String CORRELATION_ID = "X-Correlation-Id";

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) ->
                // this is a post filter as we call the chain.filter() first and then we do our logic
                chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    var httpHeaders = exchange.getRequest().getHeaders();
                    if(httpHeaders.containsKey(CORRELATION_ID)){
                        String correlationId = httpHeaders.get(CORRELATION_ID).get(0);
                        log.debug("updated correlation id to outbound headers {}", correlationId);
                        exchange.getResponse().getHeaders().add(CORRELATION_ID, correlationId);
                    }
                }));
    }
}
