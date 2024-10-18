package io.joework.orders.mapper;

import io.joework.orders.model.Order;
import io.joework.orders.model.dto.OrderDto;

public class OrderMapper {
    public static Order mapToOrder(OrderDto orderDto){
        return new Order(orderDto.getId(), orderDto.getUserId(), ItemMapper.mapToItem(orderDto.getItems()));
    }
}
