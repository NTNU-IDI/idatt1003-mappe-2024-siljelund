package edu.ntnu.stud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * A utility class for validating user input.
 */
public class InputValidator {

  Scanner in = new Scanner(System.in);


  /**
   * Validates user input for a string. The user is prompted to enter a string until a non-empty
   * string is entered.
   *
   * @return the validated string
   */
  public String inputString() {
    String input;
    while (true) {
      input = in.nextLine();
      if (!input.isEmpty()) {
        return input;
      }
      System.out.println("Invalid input. Please enter a string.");
    }
  }

  /**
   * Validates user input for a double. The user is prompted to enter a double until a positive
   * number is entered.
   *
   * @return the validated double
   */
  public double inputDouble() {
    while (true) {
      try {
        double input = Double.parseDouble(in.nextLine());
        if (input > 0) {
          return input;
        } else {
          System.out.println("Invalid input. Please enter a positive number.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a positive number.");
      }
    }
  }

  /**
   * Validates user input for a unit. The user is prompted to enter a unit until a valid unit (kg, l
   * or pieces) is entered.
   *
   * @return the validated unit
   */
  public String inputUnit() {
    String input;
    while (true) {
      input = in.nextLine();
      if (input.equals("kg") || input.equals("pieces") || input.equals("l")) {
        return input;
      }
      System.out.println("Invalid input. Please enter kg, pieces or l.");
    }
  }

  /**
   * Validates user input for a date. The user is prompted to enter a date until a valid date in the
   * format dd/MM/yyyy is entered.
   *
   * @return the validated date
   */
  public Date inputDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    while (true) {
      try {
        return sdf.parse(in.nextLine());
      } catch (ParseException e) {
        System.out.println("Invalid input. Please enter a date in the format dd/MM/yyyy.");
      }
    }
  }
}
