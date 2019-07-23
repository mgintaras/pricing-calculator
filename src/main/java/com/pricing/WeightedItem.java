package com.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WeightedItem implements Item {
    private final String name;
    private final BigDecimal weightInKg;
    private final BigDecimal pricePerKg;

    public WeightedItem(String name, BigDecimal weightInKg, BigDecimal pricePerKg) {
        this.name = name;
        this.weightInKg = weightInKg.setScale(3, RoundingMode.HALF_UP);
        this.pricePerKg = pricePerKg.setScale(2, RoundingMode.HALF_UP);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return this.weightInKg.multiply(this.pricePerKg).setScale(2, RoundingMode.HALF_UP);
    }
}
