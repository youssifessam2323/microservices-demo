package io.joework.orders.repository;

import io.joework.orders.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

    @Override
    public List<Order> getOrders() {
        return ApplicationData.getOrders();
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return ApplicationData.getOrders().stream()
                .filter(order -> order.getId() == id)
                .findAny();
    }

    @Override
    public void saveOrder(Order order) {
        ApplicationData.addOrder(order);
    }
}
