package edu.ntnu.stud;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The UserInterface class provides a command-line interface for the user to manage groceries and
 * recipes. It allows the user to add, search for, and remove groceries, as well as manage recipes.
 */
public class UserInterface {

  Scanner in = new Scanner(System.in);
  Fridge fridge = new Fridge();
  Cookbook cookbook = new Cookbook();
  InputValidator validator = new InputValidator();

  /**
   * Displays the main menu and prompts the user to select an option.
   *
   * @return the selected menu option
   */
  private int showMenu() {
    int menuOption = 0;
    System.out.println("\nWelcome to the Grocery Management");
    System.out.println("1) Add new grocery");
    System.out.println("2) Search for grocery");
    System.out.println("3) Remove grocery");
    System.out.println("4) Show all groceries");
    System.out.println("5) Show expired groceries");
    System.out.println("6) Calculate total value");
    System.out.println("7) Add new recipe");
    System.out.println("8) Show all recipes");
    System.out.println("9) Show recipes that can be made");
    System.out.println("0) Exit");

    if (this.in.hasNextInt()) {
      menuOption = this.in.nextInt();
    } else {
      System.out.println("Please enter a number between 0 and 9");
    }
    in.nextLine();

    return menuOption;
  }

  /**
   * Starts the user interface and processes user input.
   */
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
        case 7 -> addRecipeToCookbook();
        case 8 -> cookbook.getAllRecipes();
        case 9 -> printAvailableRecipes();
        case 0 -> done = true;
        default -> System.out.println("Invalid input. Please enter a number between 0 and 9");
      }
    }
  }

  /**
   * Prompts the user to add a new grocery item to the fridge.
   */
  public void addGroceryToFridge() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    this.validator = new InputValidator();
    try {
      System.out.println("Add new grocery");
      System.out.println("Enter name of grocery: ");
      String name = validator.inputString();

      System.out.println("Enter quantity: ");
      double quantity = validator.inputDouble();
      in.nextLine();

      System.out.println("Enter unit: ");
      String unit = validator.inputUnit();

      System.out.println("Enter price per unit: ");
      double price = validator.inputDouble();
      in.nextLine();

      System.out.println("Enter expiration date (dd/MM/yyyy): ");
      Date dateInput = validator.inputDate();
      Date expDate = dateFormat.parse(String.valueOf(dateInput));

      fridge.addGrocery(new Grocery(name, quantity, unit, price, expDate));
      System.out.println("Grocery added successfully");

    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Prompts the user to search for a grocery item in the fridge.
   */
  public void searchForGrocery() {
    System.out.println("Which grocery would you like to search?");
    String groceryName = in.nextLine();
    try {
      System.out.println(fridge.findGrocery(groceryName));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Prompts the user to remove a grocery item from the fridge.
   */
  public void removeGroceryFromFridge() {
    System.out.println("Which grocery would you like to remove?");
    String groceryName = in.nextLine();
    System.out.println("What amount of grocery would you like to remove?");
    double amount = in.nextDouble();
    in.nextLine();
    System.out.println(fridge.removeGrocery(groceryName, amount));
  }

  /**
   * Displays the total value of all groceries in the fridge.
   */
  public void getTotValue() {
    System.out.println("Total value of groceries: " + fridge.calcTotValue());
  }

  /**
   * Prompts the user to add a new recipe to the cookbook.
   */
  public void addRecipeToCookbook() {
    System.out.println("Enter recipe name: ");
    String recipeName = in.nextLine();

    System.out.println("Enter recipe description: ");
    String recipeDescription = in.nextLine();

    System.out.println("Enter recipe instructions:");
    String recipeInstructions = in.nextLine();

    List<Grocery> ingredients = new ArrayList<>();
    while (true) {
      System.out.println("Add an ingredient (enter ´done´ when finished): ");
      String ingredient = in.nextLine();
      if (ingredient.equals("done")) {
        break;
      }

      System.out.println("Enter quantity needed: ");
      double quantity = in.nextDouble();
      in.nextLine();

      System.out.println("Enter unit: ");
      String unit = in.nextLine();

      System.out.println("Enter price per unit: ");
      double price = in.nextDouble();
      in.nextLine();

      ingredients.add(new Grocery(ingredient, quantity, unit, price, new Date()));
    }

    cookbook.addRecipe(new Recipe(recipeName, recipeDescription, recipeInstructions, ingredients));
    System.out.println("Recipe added successfully");
  }

  /**
   * Displays the recipes that can be made with the groceries currently in the fridge.
   */
  public void printAvailableRecipes() {
    List<Recipe> availableRecipes = cookbook.getAvailableRecipes(fridge);
    if (availableRecipes.isEmpty()) {
      System.out.println(
          "There are no available recipes that can be made with the current groceries.");
    } else {
      System.out.println("Recipes that can be made: ");
      for (Recipe recipe : availableRecipes) {
        System.out.println(recipe.getName());
      }
    }
  }
}




