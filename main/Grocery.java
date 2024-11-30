package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Grocery {

  private final String name;
  private double quantity;
  private String unit;
  private final double price;
  private Date expdate;

  // verify-metode på final
  // @param - viser bare hva det gjør

  public Grocery(String name, double quantity, String unit, double price, Date expdate) {
    this.name = name;
    this.quantity = setQuantity(quantity);
    this.unit = setUnit(unit);
    this.price = price;
    this.expdate = setExpDate(expdate);
  }

  public String getName() {
    return name;
  }

  public double getQuantity() {
    return quantity;
  }

  public String getUnit() {
    return unit;
  }

  public double getPrice() {
    return price;
  }

  public Date getExpDate() {
    return expdate;
  }

  public double setQuantity(double quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be a positive number");
    }
    this.quantity = quantity;
    return quantity;
  }

  public String setUnit(String unit) {
    if (unit == null || unit.isEmpty()) {
      throw new IllegalArgumentException("Unit cannot be null or empty");
    }
    this.unit = unit;
    return unit;
  }

  public Date setExpDate(Date expdate) {
    if (expdate == null) {
      throw new IllegalArgumentException("Expdate cannot be null");
    }
    this.expdate = expdate;
    return expdate;
  }

  public void reduceQuantity(double amount) {
    if (amount <= quantity) {
      quantity -= amount;
    } else {
      throw new IllegalArgumentException("Not enough quantity");
    }
  }

  public boolean isExpired(Date today) {
    return expdate.before(today);
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return String.format(
        "Ingredient: %s\nQuantity: %.2f %s\nExpiration date: %s:",
        this.getName(), this.getQuantity(), this.getUnit(), sdf.format(expdate)
    );
  }
}
