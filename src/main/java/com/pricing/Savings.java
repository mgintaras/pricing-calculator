package com.pricing;

import java.math.BigDecimal;

public class Savings {
    private final String name;
    private final BigDecimal amount;

    public Savings(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
