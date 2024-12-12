package edu.ntnu.stud;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cookbook containing recipes. Provides methods for adding and retrieving available
 * recipes based on the groceries in a fridge.
 */
public class Cookbook {

  private List<Recipe> recipes = new ArrayList<>();

  /**
   * Adds a new recipe to the cookbook.
   *
   * @param recipe the recipe to add
   * @throws IllegalArgumentException if recipe is null
   */
  public void addRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe cannot be null.");
    }
    recipes.add(recipe);
  }

  /**
   * Retrieves a list of recipes that can be made from the ingredients in the given fridge.
   *
   * @param fridge the fridge containing available groceries
   * @return a list of recipes that can be made
   */
  public List<Recipe> getAvailableRecipes(Fridge fridge) {
    List<Recipe> availableRecipes = new ArrayList<>();
    for (Recipe recipe : recipes) {
      if (recipe.canBeMade(fridge)) {
        availableRecipes.add(recipe);
      }
    }
    return availableRecipes;
  }

  /**
   * Returns all recipes in the cookbook as a formatted string.
   *
   * @return a string containing all recipes
   * @throws IllegalArgumentException if the cookbook is empty
   */
  public String getAllRecipes() {
    StringBuilder sb = new StringBuilder("Recipes in cookbook:\n");
    if (recipes.isEmpty()) {
      throw new IllegalArgumentException("There are no recipes in the cookbook.");
    } else {
      for (Recipe recipe : recipes) {
        sb.append(recipe).append("\n");
      }
    }
    return sb.toString();
  }
}
