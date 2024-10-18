package io.joework.orders.controller;

import io.joework.orders.configuration.OrderConfigurationProperties;
import io.joework.orders.exception.PaymentProcessingException;
import io.joework.orders.model.Order;
import io.joework.orders.model.dto.OrderDto;
import io.joework.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderConfigurationProperties orderConfigurationProperties;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") int id){
        log.info("application version: {} ", orderConfigurationProperties.getVersion());
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) throws PaymentProcessingException {
        log.info("application version: {} ", orderConfigurationProperties.getVersion());
        orderService.createOrder(orderDto);
        return ResponseEntity.ok("created successfully");
    }

}
