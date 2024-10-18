package io.joework.payment_service.dto;

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

    @Override
    public String toString() {
        return "PaymentServiceRequest{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
