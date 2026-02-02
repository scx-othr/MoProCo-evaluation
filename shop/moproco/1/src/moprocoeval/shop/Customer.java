package moprocoeval.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
  // generated properties
  private String name;
  private String email;
  // end of generated properties

  // generated associations
  private Shop shop;
  private List<Order> orders = new ArrayList<Order>();

  // end of generated associations

  // generated getters and setters
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public Shop getShop() {
    return this.shop;
  }

  public void setShop(Shop newValue) {
    if (this.shop != newValue) {
      Shop oldValue = shop;
      if (oldValue != null) {
        this.shop = null;
        oldValue.removeFromCustomers(this);
      }
      this.shop = newValue;
      if (newValue != null) newValue.addToCustomers(this);
    }
  }

  public List<Order> getOrders() {
    return (List<Order>) Collections.unmodifiableList(this.orders);
  }

  public int sizeOfOrders() {
    return this.orders.size();
  }

  public void addToOrders(Order newValue) {
    if (newValue != null && !this.orders.contains(newValue)) {
      this.orders.add(newValue);
      newValue.setCustomer(this);
    }
  }

  public void removeFromOrders(Order oldValue) {
    if (oldValue != null && this.orders.contains(oldValue)) {
      this.orders.remove(oldValue);
      oldValue.setCustomer(null);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt Create a new Customer with given name and email. If the provided email address is not
   *     valid, raise an exception.
   */
  public Customer(String name, String email) {
    // generated start
this.name = name; this.email = email; if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) { throw new IllegalArgumentException("Invalid email address"); }
// generated end
    // insert your code here

  }

  /**
   * @prompt For every item of the order, check if enough items are in stock. If so, add to orders
   *     and update the items in stock by subtracting the chosen quantity. Return whether order was
   *     added.
   */
  public Boolean placeOrder(Order o) {
    // generated start
    for (OrderItem item : o.getItems()) {
        Article article = item.getArticle();
        if (article == null || article.getItemsInStock() < item.getQuantity()) {
            return false;
        }
        article.setItemsInStock(article.getItemsInStock() - item.getQuantity());
    }
    this.addToOrders(o);
    return true;
// generated end
    // insert your code here

  }
}
