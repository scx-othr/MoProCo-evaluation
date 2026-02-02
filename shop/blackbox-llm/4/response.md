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

    public Customer(String name, String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public boolean placeOrder(Order order) {
        if (order == null) {
            return false;
        }
        orders.add(order);
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
    private final UUID orderId;
    private final List<OrderItem> orderItems;
    private final Customer customer;

    public Order(Customer customer) {
        this.orderId = UUID.randomUUID();
        this.orderItems = new ArrayList<>();
        this.customer = customer;
    }

    public boolean addItem(OrderItem orderItem) {
        if (orderItem == null) {
            return false;
        }
        for (OrderItem item : orderItems) {
            if (item.getArticle().equals(orderItem.getArticle())) {
                item.setQuantity(item.getQuantity() + orderItem.getQuantity());
                return true;
            }
        }
        orderItems.add(orderItem);
        return true;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (OrderItem item : orderItems) {
            total += item.getArticle().getPrice() * item.getQuantity();
        }
        return total;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public Customer getCustomer() {
        return customer;
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
    private final Shop shop;
    private final List<OrderItem> orderItems;

    public Article(String ean, String name, double price, Shop shop) {
        if (!isValidEan(ean)) {
            throw new IllegalArgumentException("Invalid EAN code");
        }
        this.ean = ean;
        this.name = name;
        this.price = price;
        this.shop = shop;
        this.orderItems = new ArrayList<>();
    }

    public boolean isInStock(int quantity) {
        return stock >= quantity;
    }

    public void updateStock(int quantity) {
        stock -= quantity;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
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

    public Shop getShop() {
        return shop;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    private boolean isValidEan(String ean) {
        return ean != null && ean.matches("^\\d{13}$");
    }
}
```