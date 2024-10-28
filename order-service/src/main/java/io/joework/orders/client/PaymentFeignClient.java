package io.joework.orders.client;

import io.joework.orders.client.dto.PaymentServiceRequest;
import io.joework.orders.client.dto.PaymentServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payments", qualifiers = "paymentFeignClient", fallback = PaymentFeignClientFallback.class)
@Component
public interface PaymentFeignClient extends PaymentClient {

    String CONTENT_TYPE = "application/json";

    @Override
    @PostMapping(value = "/payments", consumes = {CONTENT_TYPE})
    ResponseEntity<PaymentServiceResponse> processPayments(@RequestBody PaymentServiceRequest request);


}
