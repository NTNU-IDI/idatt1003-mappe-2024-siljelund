package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the Grocery class.
 */
@DisplayName("GroceryTest")
public class GroceryTest {

  String name;
  double quantity;
  String unit;
  double price;
  Date expDate;
  Grocery testGrocery1;

  /**
   * Sets up a new Grocery instance before each test.
   */
  @BeforeEach
  void setUp() {
    name = "Egg";
    quantity = 12;
    unit = "pieces";
    price = 3;
    expDate = new Date();

    testGrocery1 = new Grocery(name, quantity, unit, price, expDate);
  }

  /**
   * Nested test class for the Grocery constructor.
   */
  @Nested
  @DisplayName("Grocery Constructor")
  public class GroceryConstructor {

    /**
     * Test the Grocery constructor with valid parameters.
     */
    @Test
    @DisplayName("Test valid constructor parameters")
    void testValidConstructorParameters() {
      assertDoesNotThrow(() -> new Grocery(name, quantity, unit, price, expDate));
      assertNotNull(testGrocery1, "Grocery instance should not be null");
      assertEquals(name, testGrocery1.getName(), "Name should match");
      assertEquals(quantity, testGrocery1.getQuantity(), "Quantity should match");
      assertEquals(unit, testGrocery1.getUnit(), "Unit should match");
      assertEquals(price, testGrocery1.getPrice(), "Price should match");
      assertEquals(expDate, testGrocery1.getExpDate(), "ExpDate should match");
    }

    /**
     * Test the Grocery constructor with invalid parameters.
     */
    @Test
    @DisplayName("Test invalid constructor parameters")
    void testInvalidConstructorParameters() {
      assertThrows(IllegalArgumentException.class,
          () -> new Grocery(null, quantity, unit, price, expDate),
          "Should throw IllegalArgumentException");
      assertThrows(IllegalArgumentException.class,
          () -> new Grocery(name, -123, unit, price, expDate),
          "Should throw IllegalArgumentException");
      assertThrows(IllegalArgumentException.class,
          () -> new Grocery(name, quantity, null, price, expDate),
          "Should throw IllegalArgumentException");
      assertThrows(IllegalArgumentException.class,
          () -> new Grocery(name, quantity, unit, -123, expDate),
          "Should throw IllegalArgumentException");
      assertThrows(IllegalArgumentException.class,
          () -> new Grocery(name, quantity, unit, price, null),
          "Should throw IllegalArgumentException");


    }

  }
}
