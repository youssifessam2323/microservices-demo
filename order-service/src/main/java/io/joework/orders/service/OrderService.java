package io.joework.orders.service;

import io.joework.orders.client.PaymentProcessorClient;
import io.joework.orders.client.dto.PaymentServiceRequest;
import io.joework.orders.client.dto.PaymentServiceResponse;
import io.joework.orders.exception.PaymentProcessingException;
import io.joework.orders.mapper.OrderMapper;
import io.joework.orders.model.Order;
import io.joework.orders.model.dto.OrderDto;
import io.joework.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentProcessorClient paymentProcessorClient;

    public void createOrder(OrderDto orderDto) throws PaymentProcessingException {
        log.info("Orders before adding the new one, {} ", orderRepository.getOrders());
        Order newOrder = OrderMapper.mapToOrder(orderDto);
        orderRepository.saveOrder(newOrder);
        log.info("processing payment for the order....");
        PaymentServiceResponse paymentServiceResponse =
                paymentProcessorClient
                        .processPayment(
                                new PaymentServiceRequest(orderDto.getId(), orderDto.getUserId(), newOrder.getTotalPrice())
                        );
        log.info("processing payment success with response {}....", paymentServiceResponse);

        if(!paymentServiceResponse.getStatus().equals(PaymentProcessorClient.PAYMENT_SUCCESS_MESSAGE)){
            throw new RuntimeException("process payment failed... {}");
        }

        log.info("orders after adding new order {} ", orderRepository.getOrders());
    }

    public Order getOrderById(int id) {
        return orderRepository.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found..."));
    }
}
