package moprocoeval.shop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NewArticleTest {

  @Test
  void validEanCreatesArticle() {
    Article article = new Article("1234567890123", "Book", 10.0);

    assertEquals("1234567890123", article.getEan());
    assertEquals("Book", article.getName());
    assertEquals(10.0, article.getCurrentPrice());
    assertEquals(0, article.getItemsInStock());
  }

  @Test
  void invalidEanThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> new Article("123", "Book", 10.0));
  }
}
