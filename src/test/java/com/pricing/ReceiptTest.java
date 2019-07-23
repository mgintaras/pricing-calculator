package com.pricing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    @Test
    void calculatesSubTotal() {
        var receipt = new Receipt(
                List.of(new UnitItem("Beans", new BigDecimal(12.5)),
                        new UnitItem("Soft Dring", new BigDecimal(3))),
                List.of(new Savings("Random discount", BigDecimal.ONE))
        );

        assertEquals(new BigDecimal(15.5).setScale(2, RoundingMode.HALF_UP), receipt.subTotal());
    }

    @Test
    void calculateSubTotalWithZeroItems() {
        var receipt = new Receipt(new ArrayList<>(), new ArrayList<>());

        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), receipt.subTotal());
    }

    @Test
    void calculatesTotalSavings() {
        var receipt = new Receipt(
                List.of(new UnitItem("Beans", new BigDecimal(12.5)),
                        new UnitItem("Soft Dring", new BigDecimal(3))),
                List.of(new Savings("Random discount", BigDecimal.ONE),
                        new Savings("Another random Discount", BigDecimal.ONE))
        );

        assertEquals(new BigDecimal(2).setScale(2, RoundingMode.HALF_UP), receipt.totalSavings());
    }

    @Test
    void calculatesTotalPay() {
        var receipt = new Receipt(
                List.of(new UnitItem("Beans", new BigDecimal(12.5)),
                        new UnitItem("Soft Dring", new BigDecimal(3))),
                List.of(new Savings("Random discount", BigDecimal.ONE),
                        new Savings("Another random Discount", BigDecimal.ONE))
        );

        assertEquals(new BigDecimal(13.5).setScale(2, RoundingMode.HALF_UP), receipt.totalPay());
    }
}