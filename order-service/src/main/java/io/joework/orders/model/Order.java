package io.joework.orders.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Order {
    @Setter(AccessLevel.NONE)
    private final int id;
    private int userId;
    @Setter(AccessLevel.NONE)
    private List<Item> items = new ArrayList<>();
    private Double totalPrice;
    private OrderStatus orderStatus = OrderStatus.PENDING;

    public Order(int id, int userId, List<Item> items) {
        this.id = id;
        this.userId = userId;
        this.items = List.copyOf(items);
        calculateTotalPrice(this.items);
    }

    private void calculateTotalPrice(List<Item> items) {
        totalPrice = items.stream().map(item -> item.getPricePerItem() * item.getQuantity())
                .mapToDouble(val -> val)
                .sum();
    }

    public void addItem(Item item){
        items.add(item);
        totalPrice += item.getPricePerItem();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
