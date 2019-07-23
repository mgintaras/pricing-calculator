# Price calculator

This application creates shopping basket with checkout functionality. 
During the checkout you can list of Discounts to be applied. Checkout generate a receipt.

## Class description

* `ShoppingBasket` - contains shopping items and allows to checkout them.
* `Receipt` - A checkout result containing bought items with applied discounts.
* `Item` - An interface for a generic item that has a name and a price.
* `UnitItem` & `WeightedItem` Implementation of specific item types.
* `Discount` A function that takes map of basket items and calculates savings
* `Savings` Result of applying discount. Contains name and discounted amount
* `PairDiscount` A simple implementation for discounts applied to pairs of items as in example.


## Requirements
* Java 12
* Maven 3.6.x

## Running application

Run command from command line

``mvn exec:java -Dexec.mainClass="com.pricing.Main"
``

This will execute main method and print similar receipt as in coding Assessment example.
