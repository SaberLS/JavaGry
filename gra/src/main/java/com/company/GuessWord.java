package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class GuessWord extends GuessGame<String> {
  char[] correctQuesses;
  char[] wrongQuesses;

  public GuessWord(int nb_chances, String answer, String name, String description) {
    super(nb_chances, answer, name, description);
    this.correctQuesses = new char[answer.length()];
    this.wrongQuesses = new char[nb_chances];

    Arrays.fill(this.correctQuesses, '?');
  }

  public GuessWord(int nb_chances, String answer) {
    super(nb_chances, answer, "Word Game", "Guess Word");
    this.correctQuesses = new char[answer.length()];
    this.wrongQuesses = new char[nb_chances];

    Arrays.fill(this.correctQuesses, '?');
    System.out.println(this.wrongQuesses);
  }

  private void wrongAnswer(char guessed) {
    this.setChances(
        this.getChances() - 1);

    System.out.println(this.wrongQuesses);
    String str = new String(this.wrongQuesses);

    int index = str.indexOf('\0');
    System.out.println(index);
    this.wrongQuesses[index] = guessed;

    if (this.triesLeft() == 0) {
      this.state = State.LOSE;
    }
  }

  private void correctAnswer(int index, char guessed) {
    while (index != -1) {
      this.correctQuesses[index] = guessed;

      index = this.getAnswer().indexOf(guessed, index + 1); // Szuka kolejnego wystąpienia
    }

    if (this.getAnswer().equals(new String(this.correctQuesses))) {
      this.state = State.WIN;
    }
  }

  public void guess(char guessed) {
    int index = this.getAnswer().indexOf(guessed);

    if (index == -1) {
      this.wrongAnswer(guessed);
    } else {
      this.correctAnswer(index, guessed);
    }
  }

  public void play() {
    this.start();

    Scanner scr = new Scanner(System.in);
    char input;

    while (this.state == State.PLAYING) {
      System.out.println("Pozostałe próby :" + this.triesLeft());
      System.out.print("Zgadnij literę");
      input = scr.next().charAt(0);
      this.guess(input);
    }

    scr.close();
    String message = this.quit();
    System.out.println(message);
  }
}
