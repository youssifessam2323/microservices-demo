package io.joework.orders.exception;

import org.springframework.web.client.RestClientException;

public class PaymentProcessingException extends Throwable {
    public PaymentProcessingException(String paymentServiceFailed, RestClientException e) {
        super(paymentServiceFailed, e);
    }
}
