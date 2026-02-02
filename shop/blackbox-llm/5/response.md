Here is the Java source code for the online shopping system as described:

```java
package moprocoeval.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Shop {
    private final List<Customer> customers;
    private final List<Article> articles;

    public Shop() {
        this.customers = new ArrayList<>();
        this.articles = new ArrayList<>();
    }

    public boolean registerCustomer(Customer customer) {
        if (findCustomer(customer.getEmail()) != null) {
            return false;
        }
        customers.add(customer);
        return true;
    }

    public Customer findCustomer(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public Article findArticle(String ean) {
        for (Article article : articles) {
            if (article.getEan().equals(ean)) {
                return article;
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }
}

class Customer {
    private final String name;
    private final String email;
    private final List<Order> orders;

    public Customer(String name, String email) throws IllegalArgumentException {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public boolean placeOrder(Order order) {
        for (OrderItem item : order.getOrderItems()) {
            Article article = item.getArticle();
            if (article.getStock() < item.getQuantity()) {
                return false;
            }
        }
        orders.add(order);
        for (OrderItem item : order.getOrderItems()) {
            Article article = item.getArticle();
            article.setStock(article.getStock() - item.getQuantity());
        }
        return true;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}

class Order {
    private final String orderId;
    private final List<OrderItem> orderItems;

    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.orderItems = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        for (OrderItem existingItem : orderItems) {
            if (existingItem.getArticle().equals(item.getArticle())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        orderItems.add(item);
    }

    public double totalPrice() {
        double total = 0.0;
        for (OrderItem item : orderItems) {
            total += item.getArticle().getPrice() * item.getQuantity();
        }
        return total;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }
}

class OrderItem {
    private final Article article;
    private int quantity;

    public OrderItem(Article article, int quantity) {
        this.article = article;
        this.quantity = quantity;
    }

    public Article getArticle() {
        return article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Article {
    private final String ean;
    private final String name;
    private final double price;
    private int stock;
    private final List<OrderItem> orderItems;
    private final Shop shop;

    public Article(String ean, String name, double price, Shop shop) throws IllegalArgumentException {
        if (!isValidEan(ean)) {
            throw new IllegalArgumentException("Invalid EAN code");
        }
        this.ean = ean;
        this.name = name;
        this.price = price;
        this.stock = 0;
        this.orderItems = new ArrayList<>();
        this.shop = shop;
    }

    public String getEan() {
        return ean;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public Shop getShop() {
        return shop;
    }

    private boolean isValidEan(String ean) {
        return ean != null && ean.length() == 13 && ean.matches("\\d+");
    }
}
```

This code follows the structure and behavior described in the problem statement. It includes all the necessary classes (`Shop`, `Customer`, `Order`, `OrderItem`, and `Article`) with their respective fields, getters, setters, constructors, and methods. The behavior for each method is implemented as specified.