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