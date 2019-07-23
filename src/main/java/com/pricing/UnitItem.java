package com.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UnitItem implements Item {
    private final String name;
    private final BigDecimal price;

    public UnitItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
