package io.joework.orders.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentServiceRequest {
    private int orderId;
    private int userId;
    private Double totalPrice;
}
