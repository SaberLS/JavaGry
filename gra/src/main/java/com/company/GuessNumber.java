package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class GuessNumber extends GuessGame<Integer, Integer> {
  public GuessNumber(int nb_chances, int answer, String name, String description) {
    super(nb_chances, answer, name, description);
  }

  public GuessNumber(int nb_chances, int answer) {
    super(nb_chances, answer, "Number Game", "Guess number");

    this.wrongQuesses = new Integer[nb_chances];
    this.correctQuesses = new Integer[1];

    Arrays.fill(this.correctQuesses, null);
    Arrays.fill(this.wrongQuesses, null);
  }

  public void guess(Integer guessed) {
    this.tries++;

    if (guessed == this.getAnswer()) {
      this.state = State.WIN;
      this.correctQuesses[0] = guessed;
    } else {
      int index = 0;
      for (Integer element : this.wrongQuesses) {
        if (element == null)
          break; // Znalazłem element
        index++;
      }

      this.wrongQuesses[index] = guessed;

      if (this.triesLeft() == 0) {
        this.state = State.LOSE;
      }
    }
  }

  public String attemptMessage() {
    String str = "Zgadnij liczbę\nPozostałe próby:\t" + this.triesLeft() + "/" + this.getChances() + "\t\n"
        + "Nie poprawne odpowiedzi " + Arrays.toString(this.wrongQuesses);
    return str;
  }

  public Integer getUserInput(Scanner scr) {
    Integer input = scr.nextInt();
    return input;
  }
}
