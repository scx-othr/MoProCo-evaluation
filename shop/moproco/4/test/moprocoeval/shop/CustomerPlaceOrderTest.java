package moprocoeval.shop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CustomerPlaceOrderTest {

  @Test
  void orderPlacedWhenStockIsSufficient() {
    Customer customer = new Customer("Alice", "alice@test.com");
    Article article = new Article("1234567890123", "Book", 10.0);
    article.setItemsInStock(5);

    Order order = new Order();
    OrderItem item = new OrderItem();
    item.setArticle(article);
    item.setQuantity(2);
    order.addItem(item);

    assertTrue(customer.placeOrder(order));
    assertEquals(3, article.getItemsInStock());
    assertEquals(1, customer.sizeOfOrders());
  }

  @Test
  void orderRejectedWhenStockIsInsufficient() {
    Customer customer = new Customer("Alice", "alice@test.com");
    Article article = new Article("1234567890123", "Book", 10.0);
    article.setItemsInStock(1);

    Order order = new Order();
    OrderItem item = new OrderItem();
    item.setArticle(article);
    item.setQuantity(2);
    order.addItem(item);

    assertFalse(customer.placeOrder(order));
    assertEquals(1, article.getItemsInStock());
    assertEquals(0, customer.sizeOfOrders());
  }
}
