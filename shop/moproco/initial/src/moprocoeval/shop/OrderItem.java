package moprocoeval.shop;


public class OrderItem {
  // generated properties
  private Integer quantity;
  // end of generated properties

  // generated associations
  private Order order;
  private Article article;

  // end of generated associations

  // generated getters and setters
  public Integer getQuantity() {
    return this.quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public Order getOrder() {
    return this.order;
  }

  public void setOrder(Order newValue) {
    if (this.order != newValue) {
      Order oldValue = order;
      if (oldValue != null) {
        this.order = null;
        oldValue.removeFromItems(this);
      }
      this.order = newValue;
      if (newValue != null) newValue.addToItems(this);
    }
  }

  public Article getArticle() {
    return this.article;
  }

  public void setArticle(Article newValue) {
    if (this.article != newValue) {
      Article oldValue = article;
      if (oldValue != null) {
        this.article = null;
        oldValue.removeFromItems(this);
      }
      this.article = newValue;
      if (newValue != null) newValue.addToItems(this);
    }
  }
  // end of generated accessors for associations

  // generated operations

}
