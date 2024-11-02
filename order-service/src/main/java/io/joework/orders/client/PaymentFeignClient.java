package io.joework.orders.client;

import io.joework.orders.client.dto.PaymentServiceRequest;
import io.joework.orders.client.dto.PaymentServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "payments", url = "http://localhost:62113")
@FeignClient(value = "payments")
public interface PaymentFeignClient{

    String CONTENT_TYPE = "application/json";

    @PostMapping(value = "/payments", consumes = {CONTENT_TYPE})
    ResponseEntity<PaymentServiceResponse> processPayments(@RequestBody PaymentServiceRequest request);


}
