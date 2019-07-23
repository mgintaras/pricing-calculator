package com.pricing;

import java.math.BigDecimal;

public class WeightedItem implements Item {
    private final String name;
    private final BigDecimal weightInKg;
    private final BigDecimal pricePerKg;

    public WeightedItem(String name, BigDecimal weightInKg, BigDecimal pricePerKg) {
        this.name = name;
        this.weightInKg = weightInKg;
        this.pricePerKg = pricePerKg;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return this.weightInKg.multiply(this.pricePerKg);
    }
}
