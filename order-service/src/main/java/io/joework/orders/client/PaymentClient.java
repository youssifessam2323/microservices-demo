package io.joework.orders.client;

import io.joework.orders.client.dto.PaymentServiceRequest;
import io.joework.orders.client.dto.PaymentServiceResponse;
import io.joework.orders.exception.PaymentProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentClient {
    ResponseEntity<PaymentServiceResponse> processPayments(@RequestBody PaymentServiceRequest request) throws PaymentProcessingException;
}
