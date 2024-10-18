package io.joework.orders.mapper;

import io.joework.orders.model.Item;
import io.joework.orders.model.dto.ItemDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {
    public static Item mapToItem(ItemDto itemDto) {
        return new Item(itemDto.getId(), itemDto.getName(), itemDto.getQuantity(), itemDto.getPricePerItem());
    }
    public static List<Item> mapToItem(List<ItemDto> itemsDto) {
        return itemsDto.stream().map(ItemMapper::mapToItem)
                .collect(Collectors.toList());
    }
}
