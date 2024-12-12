package edu.ntnu.stud;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a fridge that stores groceries. Provides methods for adding, finding, removing and
 * calculating values of groceries.
 */
public class Fridge {

  private final List<Grocery> groceries;

  /**
   * Creates a new fridge with an empty list of groceries.
   */
  public Fridge() {
    this.groceries = new ArrayList<>();
  }

  /**
   * Adds a grocery to the fridge. If the grocery already exists, the quantity is updated.
   *
   * @param grocery the grocery item to add
   */
  public void addGrocery(Grocery grocery) {
    for (Grocery existingGrocery : groceries) {
      if (existingGrocery.getName().equalsIgnoreCase(grocery.getName())) {
        existingGrocery.setQuantity(existingGrocery.getQuantity() + grocery.getQuantity());
        return;
      }
    }
    this.groceries.add(grocery);
  }

  /**
   * Finds a grocery in the fridge by name.
   *
   * @param name the name of the grocery item to find
   * @return the grocery item with the specified name
   * @throws IllegalArgumentException if the grocery item is not found
   */
  public Grocery findGrocery(String name) {
    return
        groceries.stream()
            .filter(grocery -> grocery.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Grocery not found."));
  }

  /**
   * Removes a specified amount of a grocery item from the fridge. If the quantity becomes zero or
   * less, the grocery is removed from the fridge.
   *
   * @param name   the name of the grocery item to remove
   * @param amount the amount of the grocery item to remover
   * @return the grocery item after removal
   * @throws IllegalArgumentException if the grocery does not have enough quantity or the grocery is
   *                                  not found
   */
  public Grocery removeGrocery(String name, double amount) {
    Grocery grocery = findGrocery(name);
    grocery.reduceQuantity(amount);
    if (grocery.getQuantity() <= 0) {
      groceries.remove(grocery);
    }
    System.out.println("Grocery removed: " + grocery + " with amount: " + amount + "\n");
    return grocery;
  }

  /**
   * Prints all groceries in the fridge.
   */
  public void printAllGroceries() {
    System.out.println("Groceries in fridge: ");
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }

  /**
   * Prints all expired groceries in fridge, and calculates the total value. If no expired groceries
   * is found, a message is printed.
   */
  public void printExpiredGroceries() {
    Date today = new Date();
    List<Grocery> expiredGroceries = groceries.stream()
        .filter(grocery -> grocery.isExpired(today))
        .toList();

    if (expiredGroceries.isEmpty()) {
      System.out.println("No expired groceries");
    } else {
      double totExpValue = 0;
      System.out.println("Total expired groceries: ");
      for (Grocery grocery : expiredGroceries) {
        System.out.println(grocery);
        totExpValue += grocery.getPrice() * grocery.getQuantity();
      }
      System.out.println("Total expired groceries: " + totExpValue);
    }
  }

  /**
   * Calculates the total value of the groceries in the fridge.
   *
   * @return total value of the fridge contents
   */
  public double calcTotValue() {
    return groceries.stream()
        .mapToDouble(grocery -> grocery.getPrice() * grocery.getQuantity())
        .sum();
  }

}
