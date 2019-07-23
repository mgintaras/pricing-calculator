package com.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Receipt {
    private final List<Item> items;
    private final List<Savings> savings;

    public Receipt(List<Item> items, List<Savings> savings) {
        this.items = items;
        this.savings = savings;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Savings> getSavings() {
        return savings;
    }

    public BigDecimal subTotal() {
        return items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal totalSavings() {
        return savings.stream()
                .map(Savings::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal totalPay() {
        return this.subTotal().subtract(this.totalSavings())
                .setScale(2, RoundingMode.HALF_UP);
    }
}
