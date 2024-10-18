package io.joework.payment_service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payments.application")
@Getter
@Setter
public class PaymentConfigurationProperties {
    private String version;
}
