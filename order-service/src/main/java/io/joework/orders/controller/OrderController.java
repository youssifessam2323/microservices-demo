package io.joework.orders.controller;

import io.joework.orders.configuration.OrderConfigurationProperties;
import io.joework.orders.exception.PaymentProcessingException;
import io.joework.orders.model.Order;
import io.joework.orders.model.dto.OrderDto;
import io.joework.orders.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderConfigurationProperties orderConfigurationProperties;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(){
        log.info("application version: {} ", orderConfigurationProperties.getVersion());
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") int id, HttpServletRequest request){
        log.info("application version: {} ", orderConfigurationProperties.getVersion());
        log.info("request came from API gateway: {}", request.getHeader("X-Response-Time"));
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) throws PaymentProcessingException {
        log.info("application version: {} ", orderConfigurationProperties.getVersion());
        orderService.createOrder(orderDto);
        return ResponseEntity.ok("created successfully");
    }

    @GetMapping("/app/info")
    public ResponseEntity<?> getOrderAppInfo(){
        return ResponseEntity.ok(orderConfigurationProperties);
    }
}
