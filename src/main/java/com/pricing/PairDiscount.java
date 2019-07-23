package com.pricing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PairDiscount implements Discount {
    private final String name;
    private final String itemName;
    private final int pairSize;
    private final Function<BigDecimal, BigDecimal> amountCalculator;

    public PairDiscount(String name, String itemName, int pairSize, Function<BigDecimal, BigDecimal> amountCalculator) {
        this.name = name;
        this.itemName = itemName;
        this.pairSize = pairSize;
        this.amountCalculator = amountCalculator;
    }

    @Override
    public List<Savings> apply(Map<String, List<Item>> basket) {
        var items = basket.get(itemName);

        if (hasNotEnoughItems(items, pairSize)) {
            return Collections.emptyList();
        }

        var itemPrice = items.get(0).getPrice();
        int pairCount = items.size() / this.pairSize;

        return applyDiscount(itemPrice, pairCount);
    }

    private boolean hasNotEnoughItems(List<Item> items, int pairSize) {
        return items == null || items.size() < pairSize;
    }

    private List<Savings> applyDiscount(BigDecimal itemPrice, int pairCount) {
        List<Savings> savings = new ArrayList<>();

        for (int i = 0; i < pairCount; i++) {
            savings.add(new Savings(name, this.amountCalculator.apply(itemPrice)));
        }

        return savings;
    }
}
