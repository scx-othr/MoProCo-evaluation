package moprocoeval.shop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NewCustomerTest {

  @Test
  void validEmailCreatesCustomer() {
    Customer c = new Customer("Alice", "alice@test.com");

    assertEquals("Alice", c.getName());
    assertEquals("alice@test.com", c.getEmail());
  }

  @Test
  void invalidEmailThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> new Customer("Alice", "not-an-email"));
  }
}
