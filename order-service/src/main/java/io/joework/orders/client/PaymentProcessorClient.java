package io.joework.orders.client;

import io.joework.orders.client.dto.PaymentServiceRequest;
import io.joework.orders.client.dto.PaymentServiceResponse;
import io.joework.orders.exception.PaymentProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component("paymentProcessorClient")
@RequiredArgsConstructor
public class PaymentProcessorClient implements PaymentClient {
    public final static String PAYMENT_SUCCESS_MESSAGE = "success";
    private final static String PAYMENT_SERVICE_URL = "http://localhost:8082/payments";
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<PaymentServiceResponse> processPayments(PaymentServiceRequest request) throws PaymentProcessingException {
        try {
            return restTemplate.postForEntity(PAYMENT_SERVICE_URL, request, PaymentServiceResponse.class);
        } catch (RestClientException e) {
            // Handle exceptions like timeouts or connection issues
            throw new PaymentProcessingException("Payment service failed", e);
        }
    }
}
