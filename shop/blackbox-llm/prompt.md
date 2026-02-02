Generate the Java source code for the business logic of an online shopping system.
Generate private fields, public getters and setters, and additional public constructors and operations as suggested.
Follow exactly the structure and behavior described below, without inventing additional details.
Include all Java files in your response, assuming "moprocoeval.shop" as package name.

## Structure

A shop consists of articles and customers.
It is possible to add existing customers to a shop.
For a given shop, customers can be found by e-mail and articles can be found by EAN.

Each customer belongs to one or more shops and has a name and an email address.
New customers can be created from their name and email address.
A customer may place an order, which may or may not have a positive outcome.
The orders of each customers need to be kept track of.

Each order belongs to one customer and has an order ID.
To orders, order items may be added, which may or may not have a positive outcome.
It is possible to calculate the total price for an order as a decimal value.
Each order consists of order items.

Each order item belongs to one order and specifies an integer quantity.
Each order item refers to one article.

Each article is aware of the order items from which it is referenced and of the shop it is managed by.
Furthermore, each article has an EAN, a name, a current price.
The system also keeps track of the number of items in stock for each article.
Articles may be created from their EAN, their name and their price.

## Behavior: Register customer
If a customer with the same email already exists, do not add and return false.
Otherwise add the customer and return true.

## Behavior: Find customer
Return the customer matching the provided email address.

## Behavior: Find article
Return the article mathing the provided ean.

## Constructor Behavior: Customer
Create a new Customer with given name and email.
If the provided email address is not valid, raise an exception.

## Behavior: Place order
For every item of the order, check if enough items are in stock.
If so, add to orders and update the items in stock by subtracting the chosen quantity.
Return whether the order was added.

## Constructor Behavior: Order
Create a new order and generate a random UUID for its ID

## Behavior: Add item
If an item referring to the same article already exists in the order, reuse that one and increment its quantity accordingly.
Otherwise, add the order item to the current order.

## Behavior: Total price
Return the sum of the products of prices and quantity of all referenced order items.

## Constructor Behavior: Article
Create a new article from the provided EAN, name, and price.
The provided ean must be a fully qualified EAN code according to the official definition.
