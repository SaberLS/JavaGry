package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class GuessNumber extends GuessGame<Integer, Integer> {
  public GuessNumber(Scanner scanner, int nb_chances, int answer, String name, String description) {
    super(scanner, nb_chances, answer, name, description);
  }

  public GuessNumber(Scanner scanner, int nb_chances, int answer) {
    super(scanner, nb_chances, answer, "Number Game", "Guess number");

    this.wrongQuesses = new Integer[nb_chances];
    this.correctQuesses = new Integer[1];

    Arrays.fill(this.correctQuesses, null);
    Arrays.fill(this.wrongQuesses, null);
  }

  public Integer getUserInput(Scanner scr) {
    Integer input = scr.nextInt();
    return input;
  }
}
