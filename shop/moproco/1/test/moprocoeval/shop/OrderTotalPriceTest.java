package moprocoeval.shop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OrderTotalPriceTest {

  @Test
  void totalPriceOfSingleItem() {
    Order order = new Order();
    Article article = new Article("1234567890123", "Book", 10.0);

    OrderItem item = new OrderItem();
    item.setArticle(article);
    item.setQuantity(3);
    order.addItem(item);

    assertEquals(30.0, order.totalPrice());
  }

  @Test
  void totalPriceOfMultipleItems() {
    Order order = new Order();

    Article a1 = new Article("1234567890123", "Book", 10.0);
    Article a2 = new Article("9999999999999", "Pen", 2.5);

    OrderItem i1 = new OrderItem();
    i1.setArticle(a1);
    i1.setQuantity(2);

    OrderItem i2 = new OrderItem();
    i2.setArticle(a2);
    i2.setQuantity(4);

    order.addItem(i1);
    order.addItem(i2);

    assertEquals(30.0, order.totalPrice());
  }
}
