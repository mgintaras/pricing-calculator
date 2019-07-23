package com.pricing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingBasketTest {

    @Test
    void addsItemsToTheBasket() {
        var basket = new ShoppingBasket();
        var candyBar = new UnitItem("Candy Bar", new BigDecimal(2.0));
        var beanCan = new UnitItem("Bean can", new BigDecimal(5.0));

        basket.addItem(candyBar);
        basket.addItem(beanCan);

        var receipt = basket.checkout();

        assertEquals(2, receipt.getItems().size());
        assertTrue(receipt.getItems().contains(candyBar));
        assertTrue(receipt.getItems().contains(beanCan));
    }
}