package moprocoeval.shop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShopRegisterCustomerTest {

  @Test
  void registerNewCustomerSucceeds() {
    Shop shop = new Shop();
    Customer c = new Customer("Alice", "alice@test.com");

    assertTrue(shop.registerCustomer(c));
    assertEquals(1, shop.sizeOfCustomers());
  }

  @Test
  void registeringSameEmailTwiceFails() {
    Shop shop = new Shop();
    Customer c1 = new Customer("Alice", "alice@test.com");
    Customer c2 = new Customer("Bob", "alice@test.com");

    assertTrue(shop.registerCustomer(c1));
    assertFalse(shop.registerCustomer(c2));
    assertEquals(1, shop.sizeOfCustomers());
  }
}
