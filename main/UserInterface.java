package main;


import java.text.ParseException;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;


public class UserInterface {

  Scanner in = new Scanner(System.in);
  Fridge fridge = new Fridge();

  private int showMenu() {
    int menuOption = 0;
    System.out.println("\nWelcome to the Grocery Management");
    System.out.println("1) Add new grocery");
    System.out.println("2) Search for grocery");
    System.out.println("3) Remove grocery");
    System.out.println("4) Show all groceries");
    System.out.println("5) Show expired groceries");
    System.out.println("6) Calculate total value");
    System.out.println("7) Exit");

    if (this.in.hasNextInt()) {
      menuOption = this.in.nextInt();
    } else {
      System.out.println("Please enter a number between 1 and 7");
    }

    in.nextLine();

    return menuOption;
  }

  public void start() {
    boolean done = false;

    while (!done) {
      int menuOption = this.showMenu();
      switch (menuOption) {
        case 1 -> addGroceryToFridge();
        case 2 -> searchForGrocery();
        case 3 -> removeGroceryFromFridge();
        case 4 -> fridge.printAllGroceries();
        case 5 -> fridge.printExpiredGroceries();
        case 6 -> getTotValue();
        case 7 -> done = true;
      }
    }
  }

  public void addGroceryToFridge() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    try {
      System.out.println("Add new grocery");
      System.out.println("Enter name of grocery: ");
      String name = in.nextLine();

      System.out.println("Enter quantity: ");
      double quantity = in.nextDouble();
      in.nextLine();

      System.out.println("Enter unit: ");
      String unit = in.nextLine();

      System.out.println("Enter price per unit: ");
      double price = in.nextDouble();
      in.nextLine();

      System.out.println("Enter expiration date (dd/MM/yyyy): ");
      String dateInput = in.nextLine();
      Date expDate = dateFormat.parse(dateInput);

      fridge.addGrocery(new Grocery(name, quantity, unit, price, expDate));
      System.out.println("Grocery added successfully");

    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }
  }

  public void searchForGrocery() {
    System.out.println("Which grocery would you like to search?");
    String groceryName = in.nextLine();
    System.out.print(fridge.findGrocery(groceryName));
  }

  public void removeGroceryFromFridge() {
    System.out.println("Which grocery would you like to remove?");
    String groceryName = in.nextLine();
    System.out.println("What amount of grocery would you like to remove?");
    double amount = in.nextDouble();
    in.nextLine();
    System.out.println(fridge.removeGrocery(groceryName, amount));
  }

  public void getTotValue() {
    System.out.println("Total value of groceries: " + fridge.calcTotValue());
  }
}




