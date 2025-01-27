package com.company;

import java.util.Scanner;

public class GuessWord extends GuessGame<String> {
  String correctQuesses;
  String wrongQuesses;

  public GuessWord(int nb_chances, String answer, String name, String description) {
    super(nb_chances, answer, name, description);
    this.correctQuesses = "?".repeat(answer.length());
    this.wrongQuesses = "_".repeat(nb_chances);
  }

  public GuessWord(int nb_chances, String answer) {
    super(nb_chances, answer, "Word Game", "Guess Word");
    this.correctQuesses = "?".repeat(answer.length());
    this.wrongQuesses = "_".repeat(nb_chances);
  }

  private void wrongAnswer(char guessed) {
    this.setChances(
        this.getChances() - 1);

    int index = this.wrongQuesses.indexOf('_');
    StringBuilder sb = new StringBuilder(this.wrongQuesses);

    sb.setCharAt(index, guessed);
    this.wrongQuesses = sb.toString();

    if (this.triesLeft() == 0) {
      this.state = State.LOSE;
    }
  }

  private void correctAnswer(int index, char guessed) {
    StringBuilder sb = new StringBuilder(this.correctQuesses);

    while (index != -1) {
      sb.setCharAt(index, guessed);
      index = this.getAnswer().indexOf(guessed, index + 1); // Szuka kolejnego wystąpienia
    }

    this.correctQuesses = sb.toString();

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
      System.out.println("Zgadnij literę: " + new String(this.correctQuesses));
      System.out.println("Pozostałe próby: " + this.triesLeft());
      System.out.println("Nieprawidłowe próby: " + new String(this.wrongQuesses));
      input = scr.next().charAt(0);
      this.guess(input);
    }

    scr.close();
    String message = this.quit();
    System.out.println(message);
  }
}
