package com.pricing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingBasketTest {

    @Test
    void addsItemsToTheBasket() {
        var basket = new ShoppingBasket();
        var candyBar = new UnitItem("Candy Bar", new BigDecimal(2.0));
        var beanCan = new UnitItem("Bean can", new BigDecimal(5.0));

        basket.addItem(candyBar);
        basket.addItem(beanCan);

        var receipt = basket.checkout(Collections.emptyList());

        assertEquals(2, receipt.getItems().size());
        assertTrue(receipt.getItems().contains(candyBar));
        assertTrue(receipt.getItems().contains(beanCan));
    }

    @Test
    void appliesDiscountsOnCheckout() {
        var basket = new ShoppingBasket();

        basket.addItem(new UnitItem("Beans", new BigDecimal(0.5)));
        basket.addItem(new UnitItem("Beans", new BigDecimal(0.5)));
        basket.addItem(new UnitItem("Beans", new BigDecimal(0.5)));

        basket.addItem(new UnitItem("Coke", new BigDecimal(0.7)));
        basket.addItem(new UnitItem("Coke", new BigDecimal(0.7)));

        basket.addItem(new WeightedItem("Oranges", new BigDecimal(0.200), new BigDecimal(1.99)));

        Discount beanDiscount = new PairDiscount("Beans 3 for 2", "Beans", 3, price -> price.multiply(new BigDecimal(3)).subtract(price.multiply(new BigDecimal(2))));
        Discount cokeDiscount = new PairDiscount("Coke 2 for £1", "Coke", 2, price -> price.multiply(new BigDecimal(2)).subtract(BigDecimal.ONE));

        var receipt = basket.checkout(List.of(beanDiscount, cokeDiscount));

        assertEquals(new BigDecimal(3.30).setScale(2, RoundingMode.HALF_UP), receipt.subTotal());
        assertEquals(new BigDecimal(0.90).setScale(2, RoundingMode.HALF_UP), receipt.totalSavings());
        assertEquals(new BigDecimal(2.40).setScale(2, RoundingMode.HALF_UP), receipt.totalPay());
    }
}