package com.pricing;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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

        printReceipt(receipt);
    }

    private static void printReceipt(Receipt receipt) {
        receipt.getItems().forEach(item -> {
            System.out.println(String.format("%s                %s", item.getName(), item.getPrice()));
        });
        System.out.println("-----------------------");
        System.out.println(String.format("Sub-total                %s",  receipt.subTotal()));
        System.out.println();
        System.out.println("Savings");
        receipt.getSavings().forEach(savings -> {
            System.out.println(String.format("%s                -%s", savings.getName(), savings.getAmount()));
        });
        System.out.println("-----------------------");
        System.out.println(String.format("Total savings                -%s",  receipt.totalSavings()));
        System.out.println("-----------------------");
        System.out.println(String.format("Total to Pay                %s",  receipt.totalPay()));
    }
}
