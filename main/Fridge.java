package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Fridge {

  private List<Grocery> groceries = new ArrayList<>();

  public Fridge() {
    this.groceries = new ArrayList<Grocery>();
  }

  public void addGrocery(Grocery grocery) {
    this.groceries.add(grocery);
  }

  public Grocery findGrocery(String name) {
    return
        groceries.stream()
            .filter(grocery -> grocery.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Grocery not found."));
  }

  public Grocery removeGrocery(String name, double amount) {
    Grocery grocery = findGrocery(name);
    grocery.reduceQuantity(amount);
    if (grocery.getQuantity() <= 0) {
      groceries.remove(grocery);
    }
    System.out.println("Grocery removed: " + grocery + " with amount: " + amount + "\n");
    return grocery;
  }

  public void printAllGroceries() {
    System.out.println("Groceries in fridge: ");
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }

  public void printExpiredGroceries() {
    Date today = new Date();
    List<Grocery> expiredGroceries = groceries.stream()
        .filter(grocery -> grocery.isExpired(today))
        .collect(Collectors.toList());

    if (expiredGroceries.isEmpty()) {
      System.out.println("No expired groceries");
      return;
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

  public double calcTotValue() {
    return groceries.stream()
        .mapToDouble(grocery -> grocery.getPrice() * grocery.getQuantity())
        .sum();
  }

}
