package moprocoeval.shop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShopFindCustomerTest {

  @Test
  void findExistingCustomerByEmail() {
    Shop shop = new Shop();
    Customer c = new Customer("Alice", "alice@test.com");
    shop.registerCustomer(c);

    Customer result = shop.findCustomer("alice@test.com");

    assertNotNull(result);
    assertEquals("alice@test.com", result.getEmail());
  }

  @Test
  void findNonExistingCustomerReturnsNull() {
    Shop shop = new Shop();

    assertNull(shop.findCustomer("missing@test.com"));
  }
}
