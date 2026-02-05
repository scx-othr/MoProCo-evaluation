package moprocoeval.shop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OrderAddItemTest {

  @Test
  void addingNewItemIncreasesSize() {
    Order order = new Order();
    Article article = new Article("1234567890123", "Book", 10.0);

    OrderItem item = new OrderItem();
    item.setArticle(article);
    item.setQuantity(1);

    order.addItem(item);

    assertEquals(1, order.sizeOfItems());
  }

  @Test
  void addingSameArticleIncrementsQuantity() {
    Order order = new Order();
    Article article = new Article("1234567890123", "Book", 10.0);

    OrderItem i1 = new OrderItem();
    i1.setArticle(article);
    i1.setQuantity(1);

    OrderItem i2 = new OrderItem();
    i2.setArticle(article);
    i2.setQuantity(2);

    order.addItem(i1);
    order.addItem(i2);

    assertEquals(1, order.sizeOfItems());
    assertEquals(3, order.getItems().get(0).getQuantity());
  }
}
