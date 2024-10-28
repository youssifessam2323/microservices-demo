package io.joework.orders.client;

import io.joework.orders.client.dto.PaymentServiceRequest;
import io.joework.orders.client.dto.PaymentServiceResponse;
import io.joework.orders.exception.PaymentProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
class PaymentFeignClientFallback implements PaymentClient {

    @Override
    public ResponseEntity<PaymentServiceResponse> processPayments(PaymentServiceRequest request) throws PaymentProcessingException {
        return ResponseEntity.badRequest().body(new PaymentServiceResponse("400", "0000-0000-0000", "service is unavailable"));
    }
}
