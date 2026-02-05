package moprocoeval.shop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ShopFindArticleTest {

  @Test
  void findExistingArticleByEan() {
    Shop shop = new Shop();
    Article a = new Article("1234567890123", "Book", 10.0);
    shop.addToArticles(a);

    Article result = shop.findArticle("1234567890123");

    assertNotNull(result);
    assertEquals("Book", result.getName());
  }

  @Test
  void findUnknownArticleReturnsNull() {
    Shop shop = new Shop();

    assertNull(shop.findArticle("0000000000000"));
  }
}
