package io.joework.orders.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PaymentServiceResponse {
    private String status;
    private String transactionId;
    private String message;
}
