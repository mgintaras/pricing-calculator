package com.pricing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingBasket {
    private final Map<String, List<Item>> basket = new HashMap<>();

    public void addItem(Item item) {
        basket.computeIfAbsent(item.getName(), k -> new ArrayList<>())
                .add(item);
    }

    public Receipt checkout() {
        var allItems = basket.values().stream().flatMap(List::stream)
                .collect(Collectors.toList());
        return new Receipt(allItems, new ArrayList<>());
    }
}
