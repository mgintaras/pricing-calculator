package com.pricing;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingBasket {
    private final Map<String, List<Item>> basket = new HashMap<>();

    public void addItem(Item item) {
        basket.computeIfAbsent(item.getName(), k -> new ArrayList<>())
                .add(item);
    }

    public Receipt checkout(List<Discount> discounts) {
        var savings = discounts.stream()
                .map(discount -> discount.apply(this.basket))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        var allItems = basket.values().stream().flatMap(List::stream)
                .collect(Collectors.toList());

        return new Receipt(allItems, savings);
    }
}
