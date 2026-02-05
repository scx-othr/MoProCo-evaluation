package moprocoeval.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop {
  // generated properties

  // end of generated properties

  // generated associations
  private List<Customer> customers = new ArrayList<Customer>();
  private List<Article> articles = new ArrayList<Article>();

  // end of generated associations

  // generated getters and setters

  // end of generated getters and setters

  // generated accessors for associations
  public List<Customer> getCustomers() {
    return (List<Customer>) Collections.unmodifiableList(this.customers);
  }

  public int sizeOfCustomers() {
    return this.customers.size();
  }

  public void addToCustomers(Customer newValue) {
    if (newValue != null && !this.customers.contains(newValue)) {
      this.customers.add(newValue);
      newValue.setShop(this);
    }
  }

  public void removeFromCustomers(Customer oldValue) {
    if (oldValue != null && this.customers.contains(oldValue)) {
      this.customers.remove(oldValue);
      oldValue.setShop(null);
    }
  }

  public List<Article> getArticles() {
    return (List<Article>) Collections.unmodifiableList(this.articles);
  }

  public int sizeOfArticles() {
    return this.articles.size();
  }

  public void addToArticles(Article newValue) {
    if (newValue != null && !this.articles.contains(newValue)) {
      this.articles.add(newValue);
      newValue.setShop(this);
    }
  }

  public void removeFromArticles(Article oldValue) {
    if (oldValue != null && this.articles.contains(oldValue)) {
      this.articles.remove(oldValue);
      oldValue.setShop(null);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt If a customer with the same email already exists, do not add and return false.
   *     Otherwise add the customer and return true.
   */
  public Boolean registerCustomer(Customer customer) {
    // generated start
for (Customer existingCustomer : this.customers) {
      if (existingCustomer.getEmail().equals(customer.getEmail())) {
        return false;
      }
    }
    this.addToCustomers(customer);
    return true;
// generated end
    // insert your code here

  }

  /**
   * @prompt Return the customer matching the provided email address.
   */
  public Customer findCustomer(String email) {
    // generated start
Customer customer = null;
    for (Customer c : customers) {
      if (c.getEmail().equals(email)) {
        customer = c;
        break;
      }
    }
    return customer;
// generated end
    // insert your code here

  }

  /**
   * @prompt Return the article mathing the provided ean.
   */
  public Article findArticle(String ean) {
    // generated start
Article article = null;
    for (Article a : articles) {
      if (a.getEan().equals(ean)) {
        article = a;
        break;
      }
    }
    return article;
// generated end
    // insert your code here

  }
}
