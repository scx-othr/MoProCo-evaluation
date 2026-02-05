package moprocoeval.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Article {
  // generated properties
  private String ean;
  private String name;
  private Double currentPrice;
  private Integer itemsInStock;
  // end of generated properties

  // generated associations
  private List<OrderItem> items = new ArrayList<OrderItem>();
  private Shop shop;

  // end of generated associations

  // generated getters and setters
  public String getEan() {
    return this.ean;
  }

  public void setEan(String ean) {
    this.ean = ean;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getCurrentPrice() {
    return this.currentPrice;
  }

  public void setCurrentPrice(Double currentPrice) {
    this.currentPrice = currentPrice;
  }

  public Integer getItemsInStock() {
    return this.itemsInStock;
  }

  public void setItemsInStock(Integer itemsInStock) {
    this.itemsInStock = itemsInStock;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public List<OrderItem> getItems() {
    return (List<OrderItem>) Collections.unmodifiableList(this.items);
  }

  public int sizeOfItems() {
    return this.items.size();
  }

  public void addToItems(OrderItem newValue) {
    if (newValue != null && !this.items.contains(newValue)) {
      this.items.add(newValue);
      newValue.setArticle(this);
    }
  }

  public void removeFromItems(OrderItem oldValue) {
    if (oldValue != null && this.items.contains(oldValue)) {
      this.items.remove(oldValue);
      oldValue.setArticle(null);
    }
  }

  public Shop getShop() {
    return this.shop;
  }

  public void setShop(Shop newValue) {
    if (this.shop != newValue) {
      Shop oldValue = shop;
      if (oldValue != null) {
        this.shop = null;
        oldValue.removeFromArticles(this);
      }
      this.shop = newValue;
      if (newValue != null) newValue.addToArticles(this);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt Create a new article from the provided EAN, name, and price. The provided ean must be a
   *     fully qualified EAN code according to the official definition.
   */
  public Article(String ean, String name, Double price) {
    // generated start
    // generated end
    // insert your code here

  }
}
