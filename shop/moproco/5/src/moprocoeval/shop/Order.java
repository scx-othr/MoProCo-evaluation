package moprocoeval.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
  // generated properties
  private String orderID;
  // end of generated properties

  // generated associations
  private Customer customer;
  private List<OrderItem> items = new ArrayList<OrderItem>();

  // end of generated associations

  // generated getters and setters
  public String getOrderID() {
    return this.orderID;
  }

  public void setOrderID(String orderID) {
    this.orderID = orderID;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer newValue) {
    if (this.customer != newValue) {
      Customer oldValue = customer;
      if (oldValue != null) {
        this.customer = null;
        oldValue.removeFromOrders(this);
      }
      this.customer = newValue;
      if (newValue != null) newValue.addToOrders(this);
    }
  }

  public List<OrderItem> getItems() {
    return (List<OrderItem>) Collections.unmodifiableList(this.items);
  }

  public int sizeOfItems() {
    return this.items.size();
  }

  public void addToItems(OrderItem newValue) {
    if (newValue != null && !this.items.contains(newValue)) {
      this.items.add(newValue);
      newValue.setOrder(this);
    }
  }

  public void removeFromItems(OrderItem oldValue) {
    if (oldValue != null && this.items.contains(oldValue)) {
      this.items.remove(oldValue);
      oldValue.setOrder(null);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt Create a new order and generate a random UUID for its ID.
   */
  public Order() {
    // generated start
java.util.UUID randomUUID = java.util.UUID.randomUUID();
    this.orderID = randomUUID.toString();
// generated end
    // insert your code here

  }

  /**
   * @prompt If an item referring to the same article already exists in the order, reuse that one
   *     and increment its quantity accordingly. Otherwise, add the order item to the current order.
   */
  public Boolean addItem(OrderItem item) {
    // generated start
for (OrderItem existingItem : this.items) {
      if (existingItem.getArticle() == item.getArticle()) {
        existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        return true;
      }
    }
    this.addToItems(item);
    return true;
// generated end
    // insert your code here

  }

  /**
   * @prompt Return the sum of the products of prices and quantity of all referenced order items.
   */
  public Double totalPrice() {
    // generated start
Double total = 0.0;
    for (OrderItem item : this.items) {
      total += item.getArticle().getCurrentPrice() * item.getQuantity();
    }
    return total;
// generated end
    // insert your code here

  }
}
