package io.joework.orders.service;

import io.joework.orders.client.PaymentClient;
import io.joework.orders.client.dto.PaymentServiceRequest;
import io.joework.orders.client.dto.PaymentServiceResponse;
import io.joework.orders.exception.PaymentProcessingException;
import io.joework.orders.mapper.OrderMapper;
import io.joework.orders.model.Order;
import io.joework.orders.model.dto.OrderDto;
import io.joework.orders.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentProcessorClient;

    public OrderService(OrderRepository orderRepository,
                        @Qualifier("paymentFeignClient") PaymentClient paymentProcessorClient){
        this.orderRepository = orderRepository;
        this.paymentProcessorClient = paymentProcessorClient;
    }
    public void createOrder(OrderDto orderDto) throws PaymentProcessingException {
        log.info("Orders before adding the new one, {} ", orderRepository.getOrders());
        Order newOrder = OrderMapper.mapToOrder(orderDto);
        orderRepository.saveOrder(newOrder);
        log.info("processing payment for the order....");
        ResponseEntity<PaymentServiceResponse> paymentServiceResponseResponseEntity =
                paymentProcessorClient
                        .processPayments(
                                new PaymentServiceRequest(orderDto.getId(), orderDto.getUserId(), newOrder.getTotalPrice())
                        );
        log.info("processing payment success with response {}....", paymentServiceResponseResponseEntity);

        if(!paymentServiceResponseResponseEntity.getStatusCode().equals(HttpStatus.OK)){
            throw new PaymentProcessingException("failed in payment");
        }
        PaymentServiceResponse paymentServiceResponse = paymentServiceResponseResponseEntity.getBody();

        if(!paymentServiceResponse.getStatus().equals("success")){
            throw new RuntimeException("process payment failed... {}");
        }

        log.info("orders after adding new order {} ", orderRepository.getOrders());
    }

    public Order getOrderById(int id) {
        return orderRepository.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found..."));
    }
}
