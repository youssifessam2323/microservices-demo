package io.joework.orders.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "orders.application")
@Getter
@Setter
public class OrderConfigurationProperties {
    private String version;
}
