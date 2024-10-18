package io.joework.orders.repository;

import io.joework.orders.model.Item;
import io.joework.orders.model.Order;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class ApplicationData {
    private static final Random randomGenerator = new Random();
    @Getter
    private static final List<Order> orders = new ArrayList<Order>();

    static{
        initializeData();
    }
    private static void initializeData(){
        for (int i = 0; i < 5; i++) {
            List<Item> items = new ArrayList<Item>();
            for (int j = 0; j < randomGenerator.nextInt(1,5); j++) {
                items.add(new Item(j, "Item " + j, randomGenerator.nextInt(1,10), randomGenerator.nextDouble(100,1000)));
            }
            Order order = new Order(i, 1000 + i, items);
            log.info("Order: {} ", order);
            orders.add(order);
        }
    }

    public static void addOrder(Order order){
        orders.add(order);
    }
}
