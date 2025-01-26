package com.company;

import java.util.Scanner;

public class GuessNumber extends GuessGame<Integer> {

  public GuessNumber(int nb_chances, int answer, String name, String description) {
    super(nb_chances, answer, name, description);
  }

  public GuessNumber(int nb_chances, int answer) {
    super(nb_chances, answer, "Number Game", "Guess number");
  }

  public void play() {
    this.start();

    Scanner scr = new Scanner(System.in);
    int input;

    while (this.state == State.PLAYING) {
      System.out.println("Pozostałe próby :" + this.triesLeft());
      System.out.print("Zgadnij liczbę");
      input = scr.nextInt();
      this.guess(input);
    }

    scr.close();
    String message = this.quit();
    System.out.println(message);
  }
}
