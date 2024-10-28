package io.joework.orders.exception;

import org.springframework.web.client.RestClientException;

public class PaymentProcessingException extends RuntimeException {
    public PaymentProcessingException(String paymentServiceFailed) {
        super(paymentServiceFailed);
    }
    public PaymentProcessingException(String paymentServiceFailed, RestClientException e) {
        super(paymentServiceFailed, e);
    }
}
