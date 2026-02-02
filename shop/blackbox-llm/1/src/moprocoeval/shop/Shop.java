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