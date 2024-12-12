package edu.ntnu.stud;

import java.util.Date;

/**
 * The FridgeApp class is the main class of the application and serves as the entry point for this
 * application. It initializes the UserInterface and starts the application.
 */
public class FridgeApp extends Grocery {

  /**
   * Constructs a new FridgeApp with the specified name, quantity, unit, price and expiration date.
   *
   * @param name     the name of the grocery item
   * @param quantity the quantity of the grocery item
   * @param unit     the unit of measurement (kg, l, pieces) for the grocery item
   * @param price    the price per unit for the grocery item
   * @param expdate  the expiration date of the grocery item
   */
  public FridgeApp(String name, double quantity, String unit, double price, Date expdate) {
    super(name, quantity, unit, price, expdate);
  }

  /**
   * The main method serves as the entry point for the application. It initializes the UserInterface
   * and starts the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    UserInterface test = new UserInterface();
    test.start();
  }
}

