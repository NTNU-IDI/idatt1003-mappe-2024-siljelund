package edu.ntnu.stud;

import java.util.List;

/**
 * Represents a recipe with a name, description, instructions and ingredients.
 */
public class Recipe {

  private final String name;
  private final String description;
  private final String instructions;
  private List<Grocery> ingredients;

  /**
   * Creates a new recipe.
   *
   * @param name         the name of the recipe
   * @param description  the description of the recipe
   * @param instructions the instructions of how to make the recipe
   * @param ingredients  the ingredients needed to make the recipe
   */

  public Recipe(String name, String description, String instructions, List<Grocery> ingredients) {
    this.name = name;
    this.description = description;
    this.instructions = instructions;
    this.ingredients = ingredients;
  }

  /**
   * Gets the name of the recipe.
   *
   * @return the name of the recipe
   */
  public String getName() {
    return name;
  }

  /**
   * Checks if a recipe can be made with the groceries currently in the fridge.
   *
   * @param fridge the fridge containing the groceries
   * @return true if the fridge contains the required ingredients and the quantity needed, false if
   * not.
   */

  public boolean canBeMade(Fridge fridge) {
    for (Grocery requiredIngredient : ingredients) {
      try {
        Grocery fridgeItem = fridge.findGrocery(requiredIngredient.getName());
        if (fridgeItem.getQuantity() < requiredIngredient.getQuantity()) {
          return false;
        }
      } catch (IllegalArgumentException e) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns a string representation of the recipe, with name, description, instructions and
   * ingredients.
   *
   * @return a formatted string representing the recipe
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Recipe: ").append(name).append("\n")
        .append("Description: ").append(description).append("\n")
        .append("Instructions: ").append(instructions).append("\n")
        .append("Ingredients:\n");
    for (Grocery ingredient : ingredients) {
      builder.append("-").append(ingredient).append("\n");
    }
    return builder.toString();
  }
}
