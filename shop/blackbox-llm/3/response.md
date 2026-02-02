Here is the Java source code for the online shopping system as described:

```java
package moprocoeval.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class Shop {
    private final List<Article> articles;
    private final List<Customer> customers;

    public Shop() {
        this.articles = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public boolean registerCustomer(Customer customer) {
        for (Customer c : customers) {
            if (c.getEmail().equals(customer.getEmail())) {
                return false;
            }
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

    public List<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
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
        for (OrderItem item : order.getOrderItems()) {
            Article article = item.getArticle();
            if (article.getStock() < item.getQuantity()) {
                return false;
            }
        }

        for (OrderItem item : order.getOrderItems()) {
            Article article = item.getArticle();
            article.setStock(article.getStock() - item.getQuantity());
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
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
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

    public BigDecimal totalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            total = total.add(item.getArticle().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
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
    private final BigDecimal price;
    private int stock;
    private final List<OrderItem> orderItems;

    public Article(String ean, String name, BigDecimal price) {
        if (!isValidEAN(ean)) {
            throw new IllegalArgumentException("Invalid EAN code");
        }
        this.ean = ean;
        this.name = name;
        this.price = price;
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

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    private boolean isValidEAN(String ean) {
        if (ean == null || ean.length() != 13) {
            return false;
        }
        try {
            for (int i = 0; i < ean.length(); i++) {
                if (!Character.isDigit(ean.charAt(i))) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
```