package edu.ntnu.stud;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a grocery item with name, quantity, unit, price and expiration date.
 */

public class Grocery {

  private final String name;
  private double quantity;
  private String unit;
  private double price;
  private Date expdate;

  /**
   * Creates a new Grocery item
   *
   * @param name     the name of the grocery item
   * @param quantity the quantity of the grocery item
   * @param unit     the unit of measurement (kg, l, pieces) for the grocery item
   * @param price    the price per unit for the grocery item
   * @param expdate  the expiration date of the grocery item
   */

  public Grocery(String name, double quantity, String unit, double price, Date expdate) {
    verifyName(name);
    setQuantity(quantity);
    setUnit(unit);
    verifyPrice(price);
    setExpDate(expdate);

    this.name = name;
    this.price = price;

  }

  /**
   * Creates a new Grocery item without price and expiration date.
   *
   * @param name     the name of the grocery item
   * @param quantity the quantity of the grocery item
   * @param unit     the unit of measurement (kg, l, pieces) for the grocery item
   */
  public Grocery(String name, double quantity, String unit) {
    verifyName(name);
    setQuantity(quantity);
    setUnit(unit);

    this.name = name;
  }

  /**
   * Gets the name of the grocery item
   *
   * @return the name of the item
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the quantity of the grocery item
   *
   * @return the quantity of the item
   */
  public double getQuantity() {
    return quantity;
  }

  /**
   * Gets the unit of measurement for the grocery item
   *
   * @return the unit of measurement for the item
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Gets the price per unit for the grocery item
   *
   * @return the price per unit for the item
   */
  public double getPrice() {
    return price;
  }

  /**
   * Gets the expiration date for the grocery item
   *
   * @return the expiration date for the grocery item
   */
  public Date getExpDate() {
    return expdate;
  }

  /**
   * Verifies that the name of the grocery item is not null or empty
   *
   * @param name the name to be verified
   * @throws IllegalArgumentException if the name is null or empty
   */
  public void verifyName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
  }

  /**
   * Verifies that the price of the grocery item is a positive value
   *
   * @param price the price to be verified
   * @throws IllegalArgumentException if the price is less than or equal to zero
   */
  public void verifyPrice(double price) {
    if (price <= 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }
  }

  /**
   * Sets the quantity of the grocery item. The quantity have to be a positive number
   *
   * @param quantity the quantity to be set
   * @throws IllegalArgumentException if the quantity is less than or equal to zero
   */
  public void setQuantity(double quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be a positive number");
    }
    this.quantity = quantity;
  }

  /**
   * Sets the unit of the grocery item.
   *
   * @param unit the unit to be set
   * @throws IllegalArgumentException if the unit is null or empty
   */
  public void setUnit(String unit) {
    if (unit == null || unit.isEmpty()) {
      throw new IllegalArgumentException("Unit cannot be null or empty");
    }
    this.unit = unit;
  }

  /**
   * Sets the expiration date of the grocery item.
   *
   * @param expdate the expiration date to be set
   * @throws IllegalArgumentException if the expiration date is null
   */
  public void setExpDate(Date expdate) {
    if (expdate == null) {
      throw new IllegalArgumentException("Expdate cannot be null");
    }
    this.expdate = expdate;
  }

  /**
   * Reduces the quantity of the grocery item by the specified amount.
   *
   * @param amount the amount to reduce from the current quantity
   * @throws IllegalArgumentException if the amount is greater than the current quantity
   */
  public void reduceQuantity(double amount) {
    if (amount <= quantity) {
      quantity -= amount;
    } else {
      throw new IllegalArgumentException("Not enough quantity");
    }
  }

  /**
   * Checks if the grocery item is expired based on the current date
   *
   * @param today the current date
   * @return true if the grocery item is expired, false if not
   */
  public boolean isExpired(Date today) {
    return expdate.before(today);
  }

  /**
   * Returns a string representation of the grocery item, with name, quantity, unit, price and
   * expiration date.
   *
   * @return a formatted string containing the details of the grocery item
   */
  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return String.format(
        "Ingredient: %s\nQuantity: %.2f %s\nExpiration date: %s:",
        this.getName(), this.getQuantity(), this.getUnit(), sdf.format(expdate)
    );
  }
}
