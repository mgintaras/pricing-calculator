package com.pricing;

import java.math.BigDecimal;

public class WeightedItem implements Item {
    private final String name;
    private final BigDecimal weightPerKg;
    private final BigDecimal pricePerKg;

    public WeightedItem(String name, BigDecimal weightPerKg, BigDecimal pricePerKg) {
        this.name = name;
        this.weightPerKg = weightPerKg;
        this.pricePerKg = pricePerKg;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return this.weightPerKg.multiply(this.pricePerKg);
    }
}
