package io.joework.orders.repository;

import io.joework.orders.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getOrders();
    Optional<Order> getOrderById(int id);
    void saveOrder(Order order);

}
