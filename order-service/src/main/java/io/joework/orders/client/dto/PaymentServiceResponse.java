package io.joework.orders.client.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentServiceResponse {
    private String status;
    private String transactionId;
    private String message;
}
