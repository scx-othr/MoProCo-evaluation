Here is the Java source code for the online shopping system as described:

```java
package moprocoeval.shop;

import java.math.BigDecimal;
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

public class Customer {
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
        if (order.getOrderItems().stream().anyMatch(item -> item.getQuantity() > item.getArticle().getStock())) {
            return false;
        }
        orders.add(order);
        order.getOrderItems().forEach(item -> {
            Article article = item.getArticle();
            article.setStock(article.getStock() - item.getQuantity());
        });
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

public class Order {
    private final String orderId;
    private final List<OrderItem> orderItems;

    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.orderItems = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        OrderItem existingItem = orderItems.stream()
                .filter(i -> i.getArticle().equals(item.getArticle()))
                .findFirst()
                .orElse(null);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            orderItems.add(item);
        }
    }

    public BigDecimal totalPrice() {
        return orderItems.stream()
                .map(item -> item.getArticle().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }
}

public class OrderItem {
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

public class Article {
    private final String ean;
    private final String name;
    private final BigDecimal price;
    private int stock;
    private final Shop shop;
    private final List<OrderItem> orderItems;

    public Article(String ean, String name, BigDecimal price, Shop shop) {
        if (!isValidEan(ean)) {
            throw new IllegalArgumentException("Invalid EAN code");
        }
        this.ean = ean;
        this.name = name;
        this.price = price;
        this.shop = shop;
        this.stock = 0;
        this.orderItems = new ArrayList<>();
    }

    public String getEan() {
        return ean;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
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

This code follows the structure and behavior described in the problem statement. Each class has private fields, public getters and setters, and additional public constructors and operations as suggested. The package name is `moprocoeval.shop`.