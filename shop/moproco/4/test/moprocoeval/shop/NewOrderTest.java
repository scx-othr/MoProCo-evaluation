package moprocoeval.shop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NewOrderTest {

  @Test
  void orderIdIsGenerated() {
    Order order = new Order();

    assertNotNull(order.getOrderID());
  }

  @Test
  void orderIdsAreDifferent() {
    Order o1 = new Order();
    Order o2 = new Order();

    assertNotEquals(o1.getOrderID(), o2.getOrderID());
  }
}
